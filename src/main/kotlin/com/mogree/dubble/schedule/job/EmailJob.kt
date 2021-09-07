package com.mogree.dubble.schedule.job

import org.quartz.JobDataMap
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import kotlin.jvm.Throws


@Component
class EmailJob : QuartzJobBean() {

    @Autowired
    private val mailSender: JavaMailSender? = null

    @Value("\${spring.mail.sender}")
    private lateinit var senderEmail: String

    @Throws(JobExecutionException::class)
    override fun executeInternal(jobExecutionContext: JobExecutionContext) {
        logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey())
        val jobDataMap: JobDataMap = jobExecutionContext.getMergedJobDataMap()
        val subject: String = jobDataMap.getString("subject")
        val body: String = jobDataMap.getString("body")
        val senderName: String = jobDataMap.getString("senderName")
        val replyTo: String = jobDataMap.getString("replyTo")
        val email: String = jobDataMap.getString("email")

        sendMail(subject, body, email, replyTo, senderName) // send email
    }

    private fun sendMail(subject: String, content: String, to: String, replyTo: String, senderName: String) {
        val mimeMessage = mailSender!!.createMimeMessage()
        val messageHelper = MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_NO, StandardCharsets.UTF_8.name())
        messageHelper.setTo(to)
        messageHelper.setReplyTo(replyTo)
        messageHelper.setSubject(subject)
        messageHelper.setText(content, true)
        messageHelper.setFrom(senderEmail, senderName)
        mailSender!!.send(mimeMessage)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(EmailJob::class.java)
    }
}