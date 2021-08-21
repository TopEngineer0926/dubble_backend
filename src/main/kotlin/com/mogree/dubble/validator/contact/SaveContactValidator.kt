package com.mogree.dubble.validator.contact

import com.mogree.dubble.validator.ValidatorHelper
import com.mogree.kotlin.extensions.guard
import com.mogree.server.gen.param.ParamContactModelBody
import com.mogree.spring.validator.IValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

/**
 * Validate if body has required fields.
 */
@Component
class SaveContactValidator @Autowired constructor(
    private val contactValidator: ContactValidator
) : IValidator<ParamContactModelBody> {

    override fun valid(param: ParamContactModelBody?): Boolean {
        param?.guard { return false }
        return contactValidator.isValid(param?.contactModelBody.guard { return false })
    }

    override fun errorRes(): ResponseEntity<*> {
        return ValidatorHelper.invalidBody()
    }

}
