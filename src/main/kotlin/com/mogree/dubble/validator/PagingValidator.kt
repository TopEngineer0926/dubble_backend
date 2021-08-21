package com.mogree.dubble.validator

import com.mogree.dubble.config.Config
import com.mogree.server.gen.param.ParamPaging
import com.mogree.spring.exception.APIUnprocessableEntityException
import com.mogree.spring.validator.IValidator
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class PagingValidator : IValidator<ParamPaging> {

    override fun valid(param: ParamPaging?): Boolean {

        if (param == null || param.offset == null || param.limit == null) {
            return false
        }

        return true
    }

    override fun errorRes(): ResponseEntity<*> {
        throw APIUnprocessableEntityException(Config.ErrorMessagesGerman.PAGING_PARAM_MISSING)
    }
}