package com.mogree.dubble.validator.customer

import com.mogree.dubble.config.Config
import com.mogree.dubble.storage.repository.CustomerRepository
import com.mogree.dubble.validator.ValidatorHelper
import com.mogree.server.gen.model.CustomerModel
import com.mogree.spring.exception.APIBadRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CustomerValidator @Autowired constructor(
    private val customerRepo: CustomerRepository
) {

    fun isValid(model: CustomerModel): Boolean {
        val email = model.email
        if (email != null) { // if email was set -> make validation
            ValidatorHelper.validateEmailPattern(email) // throw if invalid
        }
        ValidatorHelper.validateNotBlank(model.firstname, "Customer First name") // validate first name
        ValidatorHelper.validateNotBlank(model.lastname, "Customer Last name") // validate last name
        return true
    }

    fun isValid(customerId: Long?): Boolean {
        ValidatorHelper.validateEntityId(customerId, "Customer ID")

        if (!customerRepo.existsById(customerId!!)) {
            throw APIBadRequestException(Config.ErrorMessagesGerman.NO_CUSTOMER_FOR_ID(customerId.toString()))
        }
        return false
    }

}
