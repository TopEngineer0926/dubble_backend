package com.mogree.dubble.validator.account

import com.mogree.dubble.config.Config
import com.mogree.dubble.validator.ValidatorHelper.Companion.invalidBody
import com.mogree.server.gen.param.ParamPasswordUpdate
import com.mogree.spring.exception.APIBadRequestException
import com.mogree.spring.validator.IValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class PasswordUpdateValidator @Autowired constructor(
    private val helper: AccountValidator
) : IValidator<ParamPasswordUpdate> {

    override fun valid(param: ParamPasswordUpdate?): Boolean {
        // content body must be set
        if (param == null) {
            throw APIBadRequestException(Config.ErrorMessagesGerman.INVALID_REQUEST_PARAM)
        }
        return helper.isValid(param) // validate fields and DB existing
    }

    override fun errorRes(): ResponseEntity<*> {
        return invalidBody()
    }

}
