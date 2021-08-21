package com.mogree.dubble.service.customer

import com.mogree.dubble.config.Config
import com.mogree.dubble.config.Config.EntityName.CUSTOMER
import com.mogree.dubble.config.security.AppTokenService
import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.entity.db.CustomerEntity
import com.mogree.dubble.mapper.createEntity
import com.mogree.dubble.mapper.toEntity
import com.mogree.dubble.mapper.toModel
import com.mogree.dubble.mapper.toModels
import com.mogree.dubble.storage.repository.CustomerRepository
import com.mogree.dubble.storage.repository.ProductRepository
import com.mogree.dubble.storage.specification.CustomerSpecification
import com.mogree.server.gen.api.CustomerApiDelegate
import com.mogree.server.gen.model.CustomerModel
import com.mogree.server.gen.param.*
import com.mogree.spring.exception.APIBadRequestException
import com.mogree.spring.exception.APIItemNotFoundException
import com.mogree.spring.response.DetailResponse
import com.mogree.spring.response.ListResponse
import com.mogree.spring.response.StatusResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Primary
@Service
class CustomerService @Autowired constructor(
    private val tokenService: AppTokenService,
    private val productRepo: ProductRepository,
    private val customerRepo: CustomerRepository
) : CustomerApiDelegate {

    override fun createCustomer(
        paramCustomerModelBody: ParamCustomerModelBody?,
        paramCreateCustomer: ParamCreateCustomer?
    ): Any {
        if (paramCustomerModelBody != null) {
            val createdCustomerEntity = this.createCustomer(paramCustomerModelBody.customerModelBody)
            return DetailResponse(createdCustomerEntity.toModel())
        }
        throw APIBadRequestException(Config.ResponseMessage.INVALID_BODY)
    }

    fun createCustomer(model: CustomerModel?): CustomerEntity {
        if (model != null) {
            val entity = model.createEntity(tokenService.getCurrentUser())
            return customerRepo.save(entity)
        }
        throw APIBadRequestException(Config.ResponseMessage.invalidModel(CUSTOMER))
    }

    override fun deleteCustomer(paramDeleteCustomer: ParamDeleteCustomer?): Any {
        paramDeleteCustomer?.customerId?.let {
            customerRepo.deleteByIdAndUserId(it, getCurrentUserId())
            return StatusResponse(HttpStatus.OK, Config.ResponseMessage.ITEM_DELETED)
        }

        throw APIBadRequestException(Config.ResponseMessage.MISSING_ID)
    }

    override fun getCustomerDetail(paramGetCustomerDetail: ParamGetCustomerDetail?): Any {
        paramGetCustomerDetail?.customerId?.let {

            val entityOpt = customerRepo.findByIdAndUserId(it, getCurrentUserId())

            if (entityOpt.isPresent) {
                return DetailResponse(entityOpt.get().toModel())
            } else {
                throw APIItemNotFoundException(Config.ResponseMessage.ITEM_NOT_FOUND)
            }

        }

        throw APIBadRequestException(Config.ResponseMessage.MISSING_ID)
    }

    override fun getCustomerList(paramPaging: ParamPaging, paramGetCustomerList: ParamGetCustomerList?): Any {
        // find all by specification
        val mappedModels = this.customerRepo.findAll(CustomerSpecification(getCurrentUserId(), paramPaging)).toModels()

        // map to response object
        return ListResponse(
            mappedModels, mappedModels.size, paramPaging.offset,
            if (paramPaging.limit != null && paramPaging.limit > 0) paramPaging.limit else null
        )
    }

    override fun updateCustomer(
        paramCustomerModelBody: ParamCustomerModelBody?, paramUpdateCustomer: ParamUpdateCustomer?
    ): Any {
        if (paramCustomerModelBody != null) {

            val customerModelBody = paramCustomerModelBody.customerModelBody

            customerModelBody?.itemid.let {
                if (it != null) {
                    val contact = customerRepo.findByIdAndUserId(it.toLong(), getCurrentUserId())
                        .orElseThrow { APIItemNotFoundException(Config.ResponseMessage.ITEM_NOT_FOUND) } // get or throw

                    customerModelBody.toEntity(contact) // map fields from model to entity

                    customerRepo.save(contact) // update entity

                    return DetailResponse(contact.toModel()) // return response and return model
                }
            }
            throw APIBadRequestException(Config.ResponseMessage.MISSING_ID)
        }
        throw APIBadRequestException(Config.ResponseMessage.INVALID_BODY)
    }
}
