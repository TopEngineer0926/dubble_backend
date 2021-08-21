package com.mogree.dubble.validator.product

import com.mogree.dubble.validator.ValidatorHelper
import com.mogree.kotlin.extensions.guard
import com.mogree.server.gen.param.ParamProductNotification
import com.mogree.spring.validator.IValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

/**
 * Validate if param has required fields.
 */
@Component
class SendProductNotificationValidator @Autowired constructor(
    private val productValidator: ProductValidator
) : IValidator<ParamProductNotification> {

    override fun valid(param: ParamProductNotification?): Boolean {
        param?.guard { return false }

        return productValidator.isValid(param!!)
    }

    override fun errorRes(): ResponseEntity<*> {
        return ValidatorHelper.invalidBody()
    }

}
