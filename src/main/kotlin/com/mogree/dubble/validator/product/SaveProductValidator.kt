package com.mogree.dubble.validator.product

import com.mogree.dubble.validator.ValidatorHelper
import com.mogree.kotlin.extensions.guard
import com.mogree.server.gen.param.ParamProductModelBody
import com.mogree.spring.validator.IValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

/**
 * Validate if body has required fields.
 */
@Component
class SaveProductValidator @Autowired constructor(
    private val productValidator: ProductValidator
) : IValidator<ParamProductModelBody> {

    override fun valid(param: ParamProductModelBody?): Boolean {
        param?.guard { return false }
        return productValidator.isValid(param?.productModelBody.guard { return false })
    }

    override fun errorRes(): ResponseEntity<*> {
        return ValidatorHelper.invalidBody()
    }

}
