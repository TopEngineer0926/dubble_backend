package com.mogree.dubble.service.product.helper

import com.mogree.dubble.config.Config
import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.entity.db.ContactEntity
import com.mogree.dubble.entity.db.CustomerEntity
import com.mogree.dubble.entity.db.ProductEntity
import com.mogree.dubble.service.contact.ContactService
import com.mogree.dubble.service.customer.CustomerService
import com.mogree.dubble.service.media.MediaService
import com.mogree.dubble.storage.repository.ContactRepository
import com.mogree.dubble.storage.repository.CustomerRepository
import com.mogree.dubble.storage.repository.ProductRepository
import com.mogree.dubble.util.HashUtil
import com.mogree.dubble.util.media.config.MediaConfig
import com.mogree.server.gen.model.ContactModel
import com.mogree.server.gen.model.CustomerModel
import com.mogree.server.gen.model.MediaModel
import com.mogree.spring.exception.APIBadRequestException
import com.mogree.spring.exception.APIItemNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine
import java.time.OffsetDateTime

@Component
class ProductHelper @Autowired constructor(
    private val mediaService: MediaService,
    private val productRepo: ProductRepository,
    private val contactRepo: ContactRepository,
    private val contactService: ContactService,
    private val customerService: CustomerService,
    private val customerRepo: CustomerRepository,
    private val templateEngine: SpringTemplateEngine
) {

    @Value("\${web-domain}")
    private lateinit var webDomain: String

    @Value("\${api.vimeo.player}")
    private lateinit var player: String

    fun getProductByIdAndUserId(productId: Long, userId: Long): ProductEntity {
        return productRepo.getProductById(productId)
//            .orElseThrow { APIItemNotFoundException(Config.ResponseMessage.notFound(Config.EntityName.PRODUCT)) } // get or throw
    }

    fun getOrCreateContact(contactModel: ContactModel?): ContactEntity {
        if (contactModel != null) {

            return if (!contactModel.itemid.isNullOrBlank()) {
//                this.contactRepo.findByIdAndUserId(contactModel.itemid.toLong(), getCurrentUserId())
//                    .orElseThrow { APIBadRequestException(Config.ResponseMessage.notFound(Config.EntityName.CONTACT)) }
                this.contactRepo.getContactInfo(contactModel.itemid.toInt())
//                        .orElseThrow { APIBadRequestException(Config.ResponseMessage.notFound(Config.EntityName.CONTACT)) }
            } else {
                contactService.createContact(contactModel)
            }

        }
        throw APIBadRequestException(Config.ResponseMessage.invalidModel(Config.EntityName.CUSTOMER))
    }

    fun getOrCreateCustomer(customerModel: CustomerModel?): CustomerEntity? {
        if (customerModel != null) {

            return if (!customerModel.itemid.isNullOrBlank()) {
//                this.customerRepo.findByIdAndUserId(customerModel.itemid.toLong(), getCurrentUserId())
//                        .orElseThrow { APIBadRequestException(Config.ResponseMessage.notFound(Config.EntityName.CUSTOMER)) }
                this.customerRepo.getCustomerInfo(customerModel.itemid.toLong())
            } else {
                customerService.createCustomer(customerModel)
            }

        }
        return null
    }

    fun setShareCode(product: ProductEntity) {
        val shareCode = HashUtil.md5(
            product.user.email + product.headline + OffsetDateTime.now().toString()
        )
        if (this.productRepo.existsByShareCode(shareCode)) {
            setShareCode(product) // use recursion if product with shareCode already exists
        } else {
            product.shareCode = shareCode // set generated activation code
        }
    }

    fun getProductPage(product: ProductEntity?): String {
        return if (product != null) {
            val context = buildProductDetailContext(product)
            templateEngine.process(Config.Template.PRODUCT_DETAIL, context) // create page content
        } else {
            templateEngine.process(Config.Template.NOT_FOUND, Context()) // create not found content
        }
    }

    fun buildProductDetailContext(product: ProductEntity): Context {
        val context = Context() // create email context

        val medias = mediaService.getMedia(Config.MediaModule.PRODUCT.toLowerCase(), product.id.toInt())

        val teaserImage = this.getTeaserImage(medias)
        val videos = this.getVideos(medias.sortedBy{ it.order }) //decending because it takes the last 3
        val contactImage = this.getContactImage(product.contact.id)
        val tabCount = this.getTabCount(product)
        val companyImage = this.getCompanyImage(product.user.id)
        val pdfFiles = this.getPdfs(medias).sortedBy { it.order }

        videos.forEach { it.url = player+it.url.substringAfterLast("/") } // vimeo player link different from vimeo video link

        // set product variables
        context.setVariable(Config.EntityName.PRODUCT.toLowerCase(), product)
        context.setVariable(Config.Mail.Variable.TEASER_IMAGE, teaserImage)
        context.setVariable(Config.Mail.Variable.VIDEOS, videos)
        context.setVariable(Config.Mail.Variable.CONTACT_IMAGE, contactImage)
        context.setVariable(Config.Mail.Variable.TAB_COUNT, tabCount)
        context.setVariable(Config.Mail.Variable.PDF_FILES, pdfFiles)

        // set user variables
        context.setVariable(Config.EntityName.USER.toLowerCase(), product.user)
        context.setVariable(Config.Mail.Variable.COMPANY_IMAGE, companyImage)

        // set domain
        context.setVariable(Config.Template.WEB_DOMAIN, webDomain)

        return context
    }

    /* ***** Private Methods ***** */

    private fun getTeaserImage(medias: List<MediaModel>): MediaModel? {
        return medias
            .filter { it.mediaType == MediaConfig.MediaType.MEDIATYPE_IMAGE }
            .maxByOrNull { it.itemid.toLong() }
    }

    private fun getCompanyImage(userId: Long): MediaModel? {
        return mediaService.getMedia(Config.MediaModule.ACCOUNT.toLowerCase(), userId.toInt())
            .filter { it.mediaType == MediaConfig.MediaType.MEDIATYPE_IMAGE }
            .maxByOrNull { it.itemid.toLong() }
    }

    private fun getVideos(medias: List<MediaModel>): List<MediaModel> {
        return medias
            .filter { it.mediaType == MediaConfig.MediaType.MEDIATYPE_VIDEO }
            .take(3)
    }

    private fun getPdfs(medias: List<MediaModel>): List<MediaModel> {
        return medias
            .filter { it.mediaType == MediaConfig.MediaType.MEDIATYPE_PDF }
    }

    private fun getContactImage(contactId: Long): MediaModel? {
        return mediaService.getMedia(Config.MediaModule.CONTACT.toLowerCase(), contactId.toInt())
            .filter { it.mediaType == MediaConfig.MediaType.MEDIATYPE_IMAGE }
            .maxByOrNull { it.itemid.toLong() }
    }

    private fun getTabCount(product: ProductEntity): Int {
        var tabCount = 0
        val hasReviews = !product.reviews.isEmpty()
        val hasPressInfos = !product.pressInfos.isEmpty()
        if (hasReviews) {
            tabCount += 1
        }
        if (hasPressInfos) {
            tabCount += 1
        }
        return tabCount;
    }

}
