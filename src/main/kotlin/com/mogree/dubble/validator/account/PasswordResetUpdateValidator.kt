package com.mogree.dubble.validator.account

import com.mogree.dubble.validator.ValidatorHelper.Companion.invalidBody
import com.mogree.kotlin.extensions.guard
import com.mogree.server.gen.param.ParamPasswordResetUpdateBody
import com.mogree.spring.validator.IValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class PasswordResetUpdateValidator @Autowired constructor(
    private val helper: AccountValidator
) : IValidator<ParamPasswordResetUpdateBody> {

    override fun valid(param: ParamPasswordResetUpdateBody?): Boolean {
        // content body must be set
        val body = param?.passwordResetUpdateBody.guard { return false } // validate not null body
        return helper.isValid(body) // validate fields and DB existing
    }

    override fun errorRes(): ResponseEntity<*> {
        return invalidBody()
    }

}
