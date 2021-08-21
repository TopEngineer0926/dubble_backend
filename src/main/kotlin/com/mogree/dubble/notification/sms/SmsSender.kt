package com.mogree.dubble.notification.sms

import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.JsonNode
import com.mashape.unirest.http.Unirest
import com.mashape.unirest.http.exceptions.UnirestException
import com.mogree.dubble.config.Config
import com.mogree.dubble.util.extension.asJsonString
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component


@Component("AppSmsSender")
class SmsSender {

    @Value("\${sms.api-url}")
    private lateinit var apiUrl: String

    fun sendSMS(phoneNumber: String, content: String) {
        val phone = this.modifyNumberFormat(phoneNumber)
        try {
            val jsonResponse: HttpResponse<JsonNode> = Unirest.post(apiUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(SmsModel(content, listOf(phone)).asJsonString())
                .asJson()
            val response: String = jsonResponse.body.toString()
            Config.LOGGER.info { "Sent sms to the phone number '${phone}', response: $response" }
        } catch (ex: UnirestException) {
            Config.LOGGER.warn { "Error sending sms to the phone number '${phone}', cause: ${ex.message}" }
        }
    }

    /* Private Methods */

    private fun modifyNumberFormat(phoneNumber: String): String {
        val plus = "+"
        return if (phoneNumber.startsWith(plus)) {
            phoneNumber.replace(plus, "")
        } else {
            phoneNumber
        }
    }

    /* Private Inner Class */

    private inner class SmsModel(
        val messageContent: String,
        val recipientAddressList: List<String>
    )

}
