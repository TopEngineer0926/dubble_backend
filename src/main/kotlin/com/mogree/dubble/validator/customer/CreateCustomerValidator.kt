package com.mogree.dubble.validator.customer

import com.mogree.dubble.config.Config
import com.mogree.kotlin.extensions.guard
import com.mogree.server.gen.param.ParamCustomerModelBody
import com.mogree.spring.exception.APIUnprocessableEntityException
import com.mogree.spring.validator.IValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

/**
 * check if body has required fields
 */
@Component
class CreateCustomerValidator @Autowired constructor(
    private val customerValidator: CustomerValidator
) : IValidator<ParamCustomerModelBody> {

    override fun valid(param: ParamCustomerModelBody?): Boolean {
        param?.guard { return false }
        param?.customerModelBody.guard { return false }

        return customerValidator.isValid(param!!.customerModelBody);
    }

    override fun errorRes(): ResponseEntity<*> {
        throw APIUnprocessableEntityException(Config.ErrorMessagesGerman.CUSTOMERMODEL_FIELD_MISSING)
    }
}
