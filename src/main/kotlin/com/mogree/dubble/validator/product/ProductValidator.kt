package com.mogree.dubble.validator.product

import com.mogree.dubble.config.Config
import com.mogree.dubble.config.Config.ResponseMessage.invalidModel
import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.storage.repository.ProductRepository
import com.mogree.dubble.validator.ValidatorHelper
import com.mogree.dubble.validator.contact.ContactValidator
import com.mogree.dubble.validator.customer.CustomerValidator
import com.mogree.server.gen.model.ContactModel
import com.mogree.server.gen.model.CustomSectionModel
import com.mogree.server.gen.model.CustomerModel
import com.mogree.server.gen.model.ProductModel
import com.mogree.server.gen.param.ParamProductNotification
import com.mogree.spring.exception.APIBadRequestException
import com.mogree.spring.exception.APIItemNotFoundException
import com.mogree.spring.exception.APIPreconditionFailedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Validate an incoming product models
 */
@Component
class ProductValidator @Autowired constructor(
    private val productRepo: ProductRepository,
    private val contactValidator: ContactValidator,
    private val customerValidator: CustomerValidator
) {

    fun isValid(model: ProductModel): Boolean {
        this.validateRequiredFields(model) // validate all required fields
        this.validateReviewSection(model) // validate review section
        this.validatePressInfoSection(model) // validate press info section
        this.validateContact(model.contact) // validate contact
        this.validateCustomer(model.customer) // validate customer
        return true
    }

    fun isValid(model: ParamProductNotification): Boolean {
        ValidatorHelper.validateEntityId(model.productId, "Product Id")
        ValidatorHelper.validateNotBlank(model.notificationType, "Notification Type")
        this.validateProductForNotification(model.productId) // validate if exists product and ready for notification
        return true
    }

    /* ***** Private Methods ***** */

    private fun validateRequiredFields(model: ProductModel) {
        ValidatorHelper.validateNotBlank(model.greeting, "Greeting")
        ValidatorHelper.validateNotBlank(model.customText, "Custom Text")
        ValidatorHelper.validateNotBlank(model.publicationStatus?.name, "Publication Status")
        ValidatorHelper.validateNotBlank(model.internalPageTitle, "Internal Page Title")
    }

    private fun validateReviewSection(model: ProductModel) {
        val reviews = model.reviews
        if (!reviews.isNullOrEmpty()) {
            ValidatorHelper.validateSectionHeading(model.reviewSectionName)

            this.validateSectionItems(reviews, "Review") // validate required fields for each review item
        }
    }

    private fun validatePressInfoSection(model: ProductModel) {
        val pressInfos = model.pressInfos
        if (!pressInfos.isNullOrEmpty()) {
            ValidatorHelper.validateSectionHeading(model.pressInfoSectionName)

            this.validateSectionItems(pressInfos, "Press Info") // validate required fields for each press info item
        }
    }

    // validate custom list of CustomSectionModel
    private fun validateSectionItems(sectionItems: List<CustomSectionModel>, sectionName: String) {
        // check required fields for each press info item
        sectionItems.forEach {
            ValidatorHelper.validateHeading(it.headLine)
        }
    }

    private fun validateContact(model: ContactModel?) {
        if (model != null) {
            val contactId = model.itemid

            if (!contactId.isNullOrBlank()) { // if item id is set -> validate if exists in the DB by id
                contactValidator.isValid(contactId.toLong())
            } else { // if item id is null - then validate if creation new customer is possible
                contactValidator.isValid(model)
            }
        } else {
            throw APIBadRequestException(invalidModel(Config.EntityName.CONTACT))
        }
    }

    private fun validateCustomer(model: CustomerModel?) {
        if (model != null) {
            val customerId = model.itemid

            if (!customerId.isNullOrBlank()) { // if item id is set -> validate if exists in the DB by id
                customerValidator.isValid(customerId.toLong())
            } else { // if item id is null - then validate if creation new customer is possible
                customerValidator.isValid(model)
            }
        }
    }

    private fun validateProductForNotification(productId: Long) {
        val product = productRepo.findByIdAndUserId(productId, getCurrentUserId())
            .orElseThrow { APIItemNotFoundException(Config.ResponseMessage.notFound(Config.EntityName.PRODUCT)) } // get or throw

        // if product is already pushed then allow, else -> throw an error
        if (Config.ProductPublicationStatuses.PUBLISHED != product.publicationStatus) {
            throw APIPreconditionFailedException(Config.ErrorMessagesGerman.PRODUCT_NOT_PUBLISHED)
        }
    }

}
