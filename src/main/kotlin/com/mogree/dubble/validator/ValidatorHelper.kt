package com.mogree.dubble.validator

import com.mogree.dubble.config.Config
import com.mogree.dubble.util.extension.isNullOrStringBlank
import com.mogree.spring.exception.APIBadRequestException
import com.mogree.spring.exception.APIUnprocessableEntityException
import org.apache.commons.validator.routines.EmailValidator
import org.springframework.http.ResponseEntity

abstract class ValidatorHelper {

    companion object {

        fun isValidEntityId(value: Long?): Boolean {
            return value != null && value > 0
        }

        @Throws(APIBadRequestException::class)
        fun validateEntityId(value: Long?, fieldName: String) {
            if (value == null || value <= 0) {
                throw APIBadRequestException(Config.ErrorMessagesGerman.FIELDNAME_NOT_NULL_EMPTY(fieldName))
            }
        }

        @Throws(APIBadRequestException::class)
        fun validateNotBlank(value: String?, fieldName: String) {
            value.isNullOrStringBlank {
                throw APIBadRequestException(Config.ErrorMessagesGerman.FIELDNAME_NOT_NULL_ZERO(fieldName))
            }
        }

        @Throws(APIBadRequestException::class)
        fun validateHeading(value: String?) {
            value.isNullOrStringBlank {
                throw APIBadRequestException(Config.ErrorMessagesGerman.HEADING_NOT_NULL_EMPTY)
            }
        }

        @Throws(APIBadRequestException::class)
        fun validateSectionHeading(value: String?) {
            value.isNullOrStringBlank {
                throw APIBadRequestException(Config.ErrorMessagesGerman.SECTION_HEADING_NOT_NULL_EMPTY)
            }
        }

        @Throws(APIBadRequestException::class)
        fun validatePattern(regexPattern: Regex, value: String, fieldName: String) {
            if (!regexPattern.matches(value)) {
                throw APIBadRequestException(Config.ErrorMessagesGerman.INVALID_PATTERN(fieldName,value))
            }
        }

        @Throws(APIBadRequestException::class)
        fun validateEmailPattern(email: String?) {
            if (!EmailValidator.getInstance().isValid(email)) {
                throw APIBadRequestException(Config.ErrorMessagesGerman.INVALID_EMAIL_PATTERN(email.toString()))
            }
        }

        @Throws(APIUnprocessableEntityException::class)
        fun invalidBody(message: String? = null): ResponseEntity<*> {
            val errorMessage: String = if (message.isNullOrBlank()) {
                Config.ErrorMessagesGerman.INVALID_BODY
            } else {
                message
            }
            throw APIUnprocessableEntityException(errorMessage)
        }

    }

}
