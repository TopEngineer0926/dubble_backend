package com.mogree.dubble.validator.account

import com.mogree.dubble.validator.ValidatorHelper.Companion.invalidBody
import com.mogree.kotlin.extensions.guard
import com.mogree.server.gen.param.ParamRegisterBody
import com.mogree.spring.validator.IValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class RegisterValidator @Autowired constructor(
    private val helper: AccountValidator
) : IValidator<ParamRegisterBody> {

    override fun valid(param: ParamRegisterBody?): Boolean {
        // content body must be set
        val body = param?.registerBody.guard { return false } // validate not null body
        return helper.isValid(body) // validate fields and DB existing
    }

    override fun errorRes(): ResponseEntity<*> {
        return invalidBody()
    }

}
