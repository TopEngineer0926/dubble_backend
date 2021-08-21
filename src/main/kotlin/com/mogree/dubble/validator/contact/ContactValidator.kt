package com.mogree.dubble.validator.contact

import com.mogree.dubble.config.Config
import com.mogree.dubble.storage.repository.ContactRepository
import com.mogree.dubble.validator.ValidatorHelper
import com.mogree.server.gen.model.ContactModel
import com.mogree.spring.exception.APIBadRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Validate an incoming contact models.
 */
@Component
class ContactValidator @Autowired constructor(
    private val contactRepo: ContactRepository
) {

    fun isValid(model: ContactModel): Boolean {
        ValidatorHelper.validateEmailPattern(model.email) // throw if invalid
        ValidatorHelper.validateNotBlank(model.firstname, "Contact First name") // validate first name
        ValidatorHelper.validateNotBlank(model.lastname, "Contact Last name") // validate last name
        ValidatorHelper.validateNotBlank(model.phoneNumber, "Contact Phone number") // validate phone number
        return true
    }

    fun isValid(contactId: Long?): Boolean {
        if (contactId == null || contactId < 1) {
            throw APIBadRequestException(Config.ErrorMessagesGerman.FIELDNAME_NOT_NULL_ONE)
        }

        if (!contactRepo.existsById(contactId)) {
            throw APIBadRequestException(Config.ErrorMessagesGerman.CONTACT_EXISTENCE(contactId.toString()))
        }
        return false
    }

}
