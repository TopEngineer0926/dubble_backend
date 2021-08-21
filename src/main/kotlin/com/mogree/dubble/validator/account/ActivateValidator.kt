package com.mogree.dubble.validator.account

import com.mogree.dubble.validator.ValidatorHelper.Companion.invalidBody
import com.mogree.kotlin.extensions.guard
import com.mogree.server.gen.param.ParamActivateBody
import com.mogree.spring.validator.IValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class ActivateValidator @Autowired constructor(
    private val helper: AccountValidator
) : IValidator<ParamActivateBody> {

    override fun valid(param: ParamActivateBody?): Boolean {
        param?.guard { return false } // validate not null body
        return helper.isValid(param!!) // validate fields and DB existing
    }

    override fun errorRes(): ResponseEntity<*> {
        return invalidBody()
    }

}
