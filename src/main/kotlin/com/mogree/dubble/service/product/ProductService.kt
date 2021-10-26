package com.mogree.dubble.service.product

import com.mogree.dubble.category.payload.ProductListResponse
import com.mogree.dubble.config.Config
import com.mogree.dubble.config.Config.EntityName.PRODUCT
import com.mogree.dubble.config.Config.ResponseMessage
import com.mogree.dubble.config.security.AppTokenService
import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.mapper.createEntity
import com.mogree.dubble.mapper.toEntity
import com.mogree.dubble.mapper.toModel
import com.mogree.dubble.mapper.toModels
import com.mogree.dubble.notification.AsyncNotificationHelper
import com.mogree.dubble.service.product.helper.ProductHelper
import com.mogree.dubble.storage.repository.ProductRepository
import com.mogree.dubble.storage.specification.ProductSpecification
import com.mogree.server.gen.api.ProductApiDelegate
import com.mogree.server.gen.model.ProductModel
import com.mogree.server.gen.param.*
import com.mogree.spring.exception.APIBadRequestException
import com.mogree.spring.exception.APIConflictException
import com.mogree.spring.response.DetailResponse
import com.mogree.spring.response.ListResponse
import com.mogree.spring.response.StatusResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Primary
@Service
class ProductService @Autowired constructor(
    private val helper: ProductHelper,
    private val tokenService: AppTokenService,
    private val productRepo: ProductRepository,
    private val notificationHelper: AsyncNotificationHelper
) : ProductApiDelegate {

    @Transactional
    override fun createProduct(
        paramProductModelBody: ParamProductModelBody?,
        paramCreateProduct: ParamCreateProduct?
    ): Any {
        if (paramProductModelBody != null) {
            val body = paramProductModelBody.productModelBody

            val entity = body.createEntity(
                tokenService.getCurrentUser(),
                helper.getOrCreateContact(body.contact),
                helper.getOrCreateCustomer(body.customer)
            )

            helper.setShareCode(entity) // set shareCode for public access

            productRepo.save(entity) // save entity

            return DetailResponse(entity.toModel())
        }
        throw APIBadRequestException(ResponseMessage.INVALID_BODY)
    }

    override fun deleteProduct(paramDeleteProduct: ParamDeleteProduct?): Any {
        paramDeleteProduct?.productId?.let {
            productRepo.deleteByIdAndUserId(it, getCurrentUserId())
            return StatusResponse(HttpStatus.OK, ResponseMessage.deleted(PRODUCT))
        }

        throw APIBadRequestException(ResponseMessage.missingId(PRODUCT))
    }

    override fun getProductDetail(paramGetProductDetail: ParamGetProductDetail?): Any {
        paramGetProductDetail?.productId?.let {
            val contact = helper.getProductByIdAndUserId(it, getCurrentUserId())
            return DetailResponse(contact.toModel())
        }
        throw APIBadRequestException(ResponseMessage.missingId(PRODUCT))
    }

    override fun getProductList(paramPaging: ParamPaging, paramGetProductList: ParamGetProductList?): Any {
        // find all by specification
        var mappedModels = this.productRepo.getProductAll(getCurrentUserId()).toModels()

        if(paramPaging.limit == 5){ //for dashboard
            mappedModels = this.productRepo.getProductLimit(paramPaging.offset, paramPaging.limit, getCurrentUserId()).toModels()
        }

        // map to response object
        val response = ProductListResponse(paramPaging.offset, mappedModels, paramPaging.limit, this.productRepo.getSizeAll(getCurrentUserId()))
        return ResponseEntity.ok<ProductListResponse>(response)
    }

    override fun updateProduct(
        paramProductModelBody: ParamProductModelBody?,
        paramUpdateProduct: ParamUpdateProduct?
    ): Any {
        if (paramProductModelBody != null) {
            val body = paramProductModelBody.productModelBody

            body?.itemid.let {
                if (it != null) {

                    val product = helper.getProductByIdAndUserId(it.toLong(), getCurrentUserId()) // get or throw

                    val contactEntity = helper.getOrCreateContact(body.contact)
                    val customerEntity = helper.getOrCreateCustomer(body.customer)

                    body.toEntity(product, contactEntity, customerEntity) // map fields from model to entity

                    productRepo.save(product) // update entity

                    return DetailResponse(product.toModel()) // return response and return model

                }
            }
            throw APIBadRequestException(ResponseMessage.missingId(PRODUCT))
        }
        throw APIBadRequestException(ResponseMessage.INVALID_BODY)
    }

    override fun getProductPage(paramGetProductPage: ParamGetProductPage?): Any {
        val product = productRepo.findByShareCode(paramGetProductPage?.productShareCode!!)
            .orElse(null)
        return helper.getProductPage(product)
    }

    override fun sendProductPublishedNotification(
        param: ParamProductNotification,
        emptyParam: ParamSendProductPublishedNotification?
    ): Any {
        val product = helper.getProductByIdAndUserId(param.productId, getCurrentUserId())

        if (product.customer != null) {
            when {
                Config.NotificationType.EMAIL.name.equals(param.notificationType, ignoreCase = true) -> {
                    if (!product.customer?.email.isNullOrBlank()) {
                        this.notificationHelper.sendProductPublishedEmail(param.productId, getCurrentUserId())
                    } else {
                        throw APIConflictException(Config.ErrorMessagesGerman.CUSTOMER_EMAIL)
                    }
                }
                Config.NotificationType.SMS.name.equals(param.notificationType, ignoreCase = true) -> {
                    if (!product.customer?.phoneNumber.isNullOrBlank()) {
                        this.notificationHelper.sendProductPublishedSms(param.productId, getCurrentUserId())
                    } else {
                        throw APIConflictException(Config.ErrorMessagesGerman.CUSTOMER_PHONE)
                    }
                }
                else -> { // throw an error if notification type is invalid
                    throw APIBadRequestException(Config.ErrorMessagesGerman.INVALID_ATTRIBUTE("Notification Type"))
                }
            }
            return StatusResponse() // return empty status response
        } else {
            throw APIConflictException(Config.ErrorMessagesGerman.NOTIFICATION_NOT_POSSIBLE)
        }

    }

}
