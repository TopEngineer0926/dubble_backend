package com.mogree.dubble.service.product.helper

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.ProductEntity
import com.mogree.dubble.notification.mail.MailSender
import com.mogree.dubble.notification.sms.SmsSender
import com.mogree.dubble.service.media.helper.MediaHelper
import com.mogree.dubble.storage.repository.ProductRepository
import com.mogree.dubble.storage.repository.UserRepository
import com.mogree.dubble.util.media.config.MediaConfig
import com.mogree.spring.exception.APIItemNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine

@Component
class ProductNotificationHelper @Autowired constructor(
        private val smsSender: SmsSender,
        private val mailSender: MailSender,
        private val productRepo: ProductRepository,
        private val userRepo: UserRepository,
        private val templateEngine: SpringTemplateEngine,
        private val mediaHelper: MediaHelper
) {

    @Value("\${web-domain}")
    private lateinit var webDomain: String

    @Transactional(readOnly = true)
    fun sendProductPublishedEmail(productId: Long, userId: Long) {
        val product = this.getProductOrThrow(productId, userId)
        val user = userRepo.findByIdOrNull(userId)
        val media = mediaHelper.getMediaList(Config.Database.TABLE_CONTACT, product.contact.id.toInt(), userId)
        val imgCompanyLogo = mediaHelper.getMediaList(Config.MediaModule.ACCOUNT.toLowerCase(), product.user.id.toInt(), userId)
        val imgProduct = mediaHelper.getMediaList(Config.Database.TABLE_PRODUCT, product.id.toInt(), userId)

        val def_mail_headline = "Ihre persönliche Informationsseite"
        val def_mail_textline = "Ich habe für Sie einige aktuelle und interessante Informationen übersichtlich auf einer Seite zusammengestellt."

        val context = Context() // create email context
        context.setVariable(
                Config.Mail.Variable.PRODUCT_LINK,
                generateProductLink(product)
        ) // set variable `productLink`
        context.setVariable("contact", product.contact)
        context.setVariable("customer", product.customer)
        context.setVariable(Config.Mail.Variable.COMPANY_NAME, if (user?.companyName != null) user.companyName else "Dubble GmbH")
        context.setVariable("mailHeadline", if (product.mailHeadline!= null) product.mailHeadline else def_mail_headline)
        context.setVariable("mailTextline", if (product.mailTextline!= null) product.mailTextline else def_mail_textline)
        context.setVariable("imgPlayBtn", "$webDomain/img/play_button.png")

        if (media.isNotEmpty()) {
            context.setVariable("media", media.first())
        }

        if (imgCompanyLogo.isNotEmpty()) {
            context.setVariable("imgCompanyLogo", imgCompanyLogo.filter { it.mediaType == MediaConfig.MediaType.MEDIATYPE_IMAGE }.first())
        }

        if (imgProduct.isNotEmpty()) {
            context.setVariable("imgProduct", imgProduct.filter { it.mediaType == MediaConfig.MediaType.MEDIATYPE_IMAGE }.first())
        }

        val content: String =
                templateEngine.process(Config.Mail.Template.PRODUCT_PUBLISHED, context) // create email content
        Config.LOGGER.info { "===== content: " + content }
        val subject = Config.Mail.Subject.PRODUCT_PUBLISHED + " " + user?.companyName

        val senderName = product.contact.firstName + " " + product.contact.lastName + " - " + user?.companyName
        mailSender.sendEmailWithHtmlContentInProduct(
                subject, content, listOf(product.customer!!.email!!), product.contact!!.email!!, senderName
        ) // send email

        Config.LOGGER.info { "Sent email about published product to `${product.customer!!.email}`" } // log into info level
    }

    @Transactional(readOnly = true)
    fun sendProductPublishedSms(productId: Long, userId: Long) {
        val product = this.getProductOrThrow(productId, userId)
        var greeting = product.customer?.firstname + " " + product.customer?.lastname

        if (!product.customer?.academicDegreePreceding.isNullOrEmpty()) {
            greeting = product.customer?.academicDegreePreceding + " " + greeting
        }
        if (!product.customer?.academicDegreePreceding.isNullOrEmpty()) {
            greeting += " " + product.customer?.academicDegreeSubsequent
        }
        val def_mail_textline = "Ich habe für Sie einige aktuelle und interessante Informationen übersichtlich auf einer Seite zusammengestellt."

        val headerText = Config.Sms.FROM_PUBLISHED_TEXT + product.user.companyName + "\n" + Config.Sms.HEADER_PUBLISHED_TEXT + greeting //create the salutation
        val contentText = ",\n\n\n" + (if (product.mailTextline != null) product.mailTextline else def_mail_textline) + "\n" +
                "Schöne Grüße \n" + product.contact.firstName + " " + product.contact.lastName + "\n\n" + generateProductLink(product) // set contact and the product page link

        smsSender.sendSMS(product.customer!!.phoneNumber!!, headerText + contentText) // send sms text to the customer

        Config.LOGGER.info { "Sent sms about published product to `${product.customer!!.phoneNumber}`" } // log into info level
    }

    /* ***** Private Methods ***** */

    @Throws(APIItemNotFoundException::class)
    private fun getProductOrThrow(productId: Long, userId: Long): ProductEntity {
        return productRepo.findByIdAndUserId(productId, userId)
                .orElseThrow { APIItemNotFoundException(Config.ResponseMessage.notFound(Config.EntityName.PRODUCT)) } // get or throw
    }

    private fun generateProductLink(product: ProductEntity): String {
        return "$webDomain/${product.shareCode}"
    }

}
