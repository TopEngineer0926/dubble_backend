package com.mogree.dubble.notification.mail

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_NO
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component("AppMailSender")
class MailSender @Autowired(required = false) constructor(
        private var sender: JavaMailSender? = null
) {

    @Value("\${spring.mail.sender}")
    private lateinit var senderEmail: String

    @Value("\${spring.mail.sender-name}")
    private lateinit var senderName: String

    @Throws(MailException::class)
    fun sendEmailWithHtmlContent(subject: String, content: String, receivers: List<String>) {
        receivers.forEach { to ->
            val mimeMessage = sender!!.createMimeMessage()
            val messageHelper = MimeMessageHelper(mimeMessage, MULTIPART_MODE_NO, StandardCharsets.UTF_8.name())
            messageHelper.setTo(to)
            messageHelper.setSubject(subject)
            messageHelper.setText(content, true)
            messageHelper.setFrom(senderEmail, senderName)
            sender!!.send(mimeMessage)
        }
    }

    @Throws(MailException::class)
    fun sendEmailWithHtmlContentInProduct(subject: String, content: String, receivers: List<String>, replyTo: String, senderName: String) {
        receivers.forEach { to ->
            val mimeMessage = sender!!.createMimeMessage()
            val messageHelper = MimeMessageHelper(mimeMessage, MULTIPART_MODE_NO, StandardCharsets.UTF_8.name())
            messageHelper.setTo(to)
            messageHelper.setReplyTo(replyTo)
            messageHelper.setSubject(subject)
            messageHelper.setText(content, true)
            messageHelper.setFrom(senderEmail, senderName)
            sender!!.send(mimeMessage)
        }
    }

}
