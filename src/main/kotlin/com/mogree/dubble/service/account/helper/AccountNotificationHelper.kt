package com.mogree.dubble.service.account.helper

import com.mogree.dubble.config.Config
import com.mogree.dubble.config.Config.Companion.LOGGER
import com.mogree.dubble.entity.db.UserEntity
import com.mogree.dubble.notification.mail.MailSender
import com.mogree.dubble.storage.repository.UserRepository
import com.mogree.spring.exception.APIItemNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine

@Component
class AccountNotificationHelper @Autowired constructor(
    private val mailSender: MailSender,
    private val userRepo: UserRepository,
    private val templateEngine: SpringTemplateEngine
) {

    @Value("\${cms-domain}")
    private lateinit var cmsDomain: String

    @Value("\${web-domain}")
    private lateinit var webDomain: String

    @Transactional(readOnly = true)
    fun sendActivationEmail(userId: Long) {
        val user = userRepo.findById(userId)
            .orElseThrow { APIItemNotFoundException(Config.ErrorMessagesGerman.NO_USER_FOR_ID(userId.toString())) }

        val context = Context() // create email context
        context.setVariable(
            Config.Mail.Variable.ACTIVATION_LINK,
            generateActivationLink(user)
        ) // set variable `activationLink`
        context.setVariable("user", user)
        context.setVariable(Config.Template.WEB_DOMAIN, webDomain)
        context.setVariable(Config.Mail.Variable.COMPANY_NAME, if (user?.companyName != null) user.companyName else "Dubble GmbH")

        val content: String = templateEngine.process(Config.Mail.Template.ACTIVATION, context) // create email content

        mailSender.sendEmailWithHtmlContent(Config.Mail.Subject.ACTIVATION, content, listOf(user.email)) // send email
        LOGGER.info { "Sent email for account activation to `${user.email}`" } // log into info level
    }

    @Transactional(readOnly = true)
    fun sendPasswordResetEmail(userId: Long) {
        val user = userRepo.findById(userId)
            .orElseThrow { APIItemNotFoundException(Config.ErrorMessagesGerman.NO_USER_FOR_ID(userId.toString())) }

        val context = Context() // create email context
        context.setVariable(
            Config.Mail.Variable.RESET_PASSWORD_LINK,
            generateResetPasswordLink(user)
        ) // set variable `resetPasswordLink`
        context.setVariable("user", user) //set user to use firstname and lastname
        context.setVariable(Config.Template.WEB_DOMAIN, webDomain)
        context.setVariable(Config.Mail.Variable.COMPANY_NAME, if (user?.companyName != null) user.companyName else "Dubble GmbH")

        val content: String = templateEngine.process(Config.Mail.Template.RESET_PASSWORD, context) // create email content

        mailSender.sendEmailWithHtmlContent(
            Config.Mail.Subject.RESET_PASSWORD,
            content,
            listOf(user.email)
        ) // send email
        LOGGER.info { "Sent email with reset password link to `${user.email}`" } // log into info level
    }

    @Transactional(readOnly = true)
    fun sendInviteEmail(userId: Long) {
        val user = userRepo.findById(userId)
                .orElseThrow { APIItemNotFoundException(Config.ErrorMessagesGerman.NO_USER_FOR_ID(userId.toString())) }

        val context = Context() // create email context
        context.setVariable(
                Config.Mail.Variable.RESET_PASSWORD_LINK,
                generateResetPasswordLink(user)
        ) // set variable `resetPasswordLink`
        context.setVariable("user", user) //set user to use firstname and lastname
        context.setVariable(Config.Template.WEB_DOMAIN, webDomain)
        context.setVariable(Config.Mail.Variable.COMPANY_NAME, if (user?.companyName != null) user.companyName else "Dubble GmbH")

        val content: String = templateEngine.process(Config.Mail.Template.INVITE_USER, context) // create email content

        mailSender.sendEmailWithHtmlContent(
                Config.Mail.Subject.INVITE_USER,
                content,
                listOf(user.email)
        ) // send email
        LOGGER.info { "Sent email with reset password link to `${user.email}`" } // log into info level
    }

    /* ***** Private Methods ***** */

    private fun generateActivationLink(user: UserEntity): String {
        return "$cmsDomain${Config.Mail.Uri.ACTIVATION}?activationCode=${user.activationCode}"
    }

    private fun generateResetPasswordLink(user: UserEntity): String {
        return "$cmsDomain${Config.Mail.Uri.RESET_PASSWORD}?passwordResetCode=${user.passwordResetCode}"
    }

}
