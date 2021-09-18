package com.mogree.dubble.schedule.controller

import com.mogree.dubble.config.Config
import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.entity.db.ProductEntity
import com.mogree.dubble.schedule.job.EmailJob
import com.mogree.dubble.schedule.job.SmsJob
import com.mogree.dubble.schedule.payload.*
import com.mogree.dubble.service.media.helper.MediaHelper
import com.mogree.dubble.storage.repository.ProductRepository
import com.mogree.dubble.storage.repository.UserRepository
import com.mogree.spring.exception.APIItemNotFoundException
import org.quartz.*
import org.quartz.impl.StdSchedulerFactory
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine
import java.time.ZonedDateTime
import java.util.*
import kotlin.jvm.Throws


@RestController
@RequestMapping("schedule")
class JobSchedulerController (
        private val scheduler: Scheduler = StdSchedulerFactory.getDefaultScheduler(),
        private val productRepo: ProductRepository,
        private val userRepo: UserRepository,
        private val templateEngine: SpringTemplateEngine,
        private val mediaHelper: MediaHelper
) {

    @Value("\${web-domain}")
    private lateinit var webDomain: String

    @PostMapping("/scheduleEmail")
    fun scheduleEmail(@RequestBody scheduleEmailRequest: ScheduleEmailRequest): ResponseEntity<ScheduleEmailResponse> {
        return try {
            val dateTime = ZonedDateTime.of(scheduleEmailRequest.dateTime, scheduleEmailRequest.timeZone)
            if (dateTime.isBefore(ZonedDateTime.now())) {
                val scheduleEmailResponse = ScheduleEmailResponse(false,
                        "dateTime must be after current time")
                return ResponseEntity.badRequest().body<ScheduleEmailResponse>(scheduleEmailResponse)
            }
            val jobDetail: JobDetail = buildEmailJobDetail(scheduleEmailRequest)
            val trigger: Trigger = buildEmailJobTrigger(jobDetail, dateTime)
            scheduler.scheduleJob(jobDetail, trigger)
            val scheduleEmailResponse = ScheduleEmailResponse(true,
                    jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Email Scheduled Successfully!")
            ResponseEntity.ok<ScheduleEmailResponse>(scheduleEmailResponse)
        } catch (ex: SchedulerException) {
            logger.error("Error scheduling email", ex)
            val scheduleEmailResponse = ScheduleEmailResponse(false,
                    "Error scheduling email. Please try later!")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body<ScheduleEmailResponse>(scheduleEmailResponse)
        }
    }

    @PostMapping("/scheduleSms")
    fun scheduleSms(@RequestBody scheduleSmsRequest: ScheduleSmsRequest): ResponseEntity<ScheduleSmsResponse> {
        return try {
            val dateTime = ZonedDateTime.of(scheduleSmsRequest.dateTime, scheduleSmsRequest.timeZone)
            if (dateTime.isBefore(ZonedDateTime.now())) {
                val scheduleSmsResponse = ScheduleSmsResponse(false,
                        "dateTime must be after current time")
                return ResponseEntity.badRequest().body<ScheduleSmsResponse>(scheduleSmsResponse)
            }
            val jobDetail: JobDetail = buildSmsJobDetail(scheduleSmsRequest)
            val trigger: Trigger = buildSmsJobTrigger(jobDetail, dateTime)
            scheduler.scheduleJob(jobDetail, trigger)
            val scheduleSmsResponse = ScheduleSmsResponse(true,
                    jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Sms Scheduled Successfully!")
            ResponseEntity.ok<ScheduleSmsResponse>(scheduleSmsResponse)
        } catch (ex: SchedulerException) {
            logger.error("Error scheduling sms", ex)
            val scheduleSmsResponse = ScheduleSmsResponse(false,
                    "Error scheduling sms. Please try later!")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body<ScheduleSmsResponse>(scheduleSmsResponse)
        }
    }

    private fun buildEmailJobDetail(scheduleEmailRequest: ScheduleEmailRequest): JobDetail {
        val jobDataMap = JobDataMap()

        val product = this.getProductOrThrow(scheduleEmailRequest.productId!!.toLong(), getCurrentUserId())
        val user = userRepo.findByIdOrNull(getCurrentUserId())
        val media = mediaHelper.getMediaList(Config.Database.TABLE_CONTACT, product.contact.id.toInt(), getCurrentUserId())

        val context = Context() // create email context
        context.setVariable(
                Config.Mail.Variable.PRODUCT_LINK,
                generateProductLink(product)
        ) // set variable `productLink`
        context.setVariable("contact", product.contact)
        context.setVariable("customer", product.customer)
        context.setVariable(Config.Mail.Variable.COMPANY_NAME, if (user?.companyName != null) user.companyName else "Dubble GmbH")

        if (media.isNotEmpty()) {
            context.setVariable("media", media.first())
        }

        val content: String =
                templateEngine.process(Config.Mail.Template.PRODUCT_PUBLISHED, context) // create email content

        val subject = Config.Mail.Subject.PRODUCT_PUBLISHED + " " + user?.companyName

        val senderName = product.contact.firstName + " " + product.contact.lastName + " - " + user?.companyName

        jobDataMap.put("email", product.customer!!.email!!)
        jobDataMap.put("subject", subject)
        jobDataMap.put("replyTo", product.contact!!.email!!)
        jobDataMap.put("senderName", senderName)
        jobDataMap.put("body", content)
        return JobBuilder.newJob(EmailJob::class.java)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build()
    }

    private fun buildEmailJobTrigger(jobDetail: JobDetail, startAt: ZonedDateTime): Trigger {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("Send Email Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build()
    }

    private fun buildSmsJobDetail(scheduleSmsRequest: ScheduleSmsRequest): JobDetail {
        val jobDataMap = JobDataMap()

        val product = this.getProductOrThrow(scheduleSmsRequest.productId!!.toLong(), getCurrentUserId())
        var greeting = product.customer?.firstName + " " + product.customer?.lastName

        if (!product.customer?.academicDegreePreceding.isNullOrEmpty()) {
            greeting = product.customer?.academicDegreePreceding + " " + greeting
        }
        if (!product.customer?.academicDegreePreceding.isNullOrEmpty()) {
            greeting += " " + product.customer?.academicDegreeSubsequent
        }

        val headerText = Config.Sms.FROM_PUBLISHED_TEXT + product.user.companyName + "\n" + Config.Sms.HEADER_PUBLISHED_TEXT + greeting //create the salutation
        val contentText = Config.Sms.PRODUCT_PUBLISHED_TEXT + product.contact.firstName + " " + product.contact.lastName + "\n\n" + generateProductLink(product) // set contact and the product page link

        jobDataMap.put("phoneNumber", product.customer!!.phoneNumber!!)
        jobDataMap.put("content", headerText + contentText)
        return JobBuilder.newJob(SmsJob::class.java)
                .withIdentity(UUID.randomUUID().toString(), "sms-jobs")
                .withDescription("Send Sms Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build()
    }

    private fun buildSmsJobTrigger(jobDetail: JobDetail, startAt: ZonedDateTime): Trigger {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "sms-triggers")
                .withDescription("Send Sms Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build()
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

    companion object {
        private val logger = LoggerFactory.getLogger(JobSchedulerController::class.java)
    }

    @PostMapping("/deleteJob")
    fun deleteJob(@RequestBody deleteJobRequest: DeleteJobRequest): ResponseEntity<DeleteJobResponse> {
        return try {

            val jobKey = JobKey(deleteJobRequest.jobId, deleteJobRequest.jobGroup)
            scheduler.deleteJob(jobKey)
            val response = DeleteJobResponse(true, "Deleted Successfully!")
            ResponseEntity.ok<DeleteJobResponse>(response)
        } catch (ex: SchedulerException) {
            logger.error("Error deleting job", ex)
            val response = DeleteJobResponse(false,
                    "Error deleting job. Please try later!")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body<DeleteJobResponse>(response)
        }
    }
}