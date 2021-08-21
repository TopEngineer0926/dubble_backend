package com.mogree.dubble.service.contact

import com.mogree.dubble.config.Config
import com.mogree.dubble.config.Config.ResponseMessage.INVALID_BODY
import com.mogree.dubble.config.Config.ResponseMessage.ITEM_DELETED
import com.mogree.dubble.config.Config.ResponseMessage.ITEM_NOT_FOUND
import com.mogree.dubble.config.Config.ResponseMessage.MISSING_ID
import com.mogree.dubble.config.security.AppTokenService
import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.entity.db.ContactEntity
import com.mogree.dubble.mapper.createEntity
import com.mogree.dubble.mapper.toEntity
import com.mogree.dubble.mapper.toModel
import com.mogree.dubble.mapper.toModels
import com.mogree.dubble.storage.repository.ContactRepository
import com.mogree.dubble.storage.repository.ProductRepository
import com.mogree.dubble.storage.specification.ContactSpecification
import com.mogree.server.gen.api.ContactApiDelegate
import com.mogree.server.gen.model.ContactModel
import com.mogree.server.gen.param.*
import com.mogree.spring.exception.APIBadRequestException
import com.mogree.spring.exception.APIConflictException
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
class ContactService @Autowired constructor(
    private val tokenService: AppTokenService,
    private val contactRepo: ContactRepository,
    private val productRepo: ProductRepository
) : ContactApiDelegate {

    override fun createContact(
        paramContactModelBody: ParamContactModelBody?,
        paramCreateContact: ParamCreateContact?
    ): Any {
        if (paramContactModelBody != null) {
            val entity = this.createContact(paramContactModelBody.contactModelBody)
            return DetailResponse(entity.toModel())
        }
        throw APIBadRequestException(INVALID_BODY)
    }

    fun createContact(model: ContactModel?): ContactEntity {
        if (model != null) {
            val entity = model.createEntity(tokenService.getCurrentUser())
            return contactRepo.save(entity)
        }
        throw APIBadRequestException(Config.ResponseMessage.invalidModel(Config.EntityName.CONTACT))
    }

    override fun deleteContact(paramDeleteContact: ParamDeleteContact?): Any {
        paramDeleteContact?.contactId?.let {

            if (!productRepo.existsByContactId(it)) {
                contactRepo.deleteByIdAndUserId(it, getCurrentUserId())
                return StatusResponse(HttpStatus.OK, ITEM_DELETED)
            }

            throw APIConflictException(Config.ErrorMessagesGerman.CONTACT_USED_IN_PRODUCT)
        }

        throw APIBadRequestException(MISSING_ID)
    }

    override fun getContactDetail(paramGetContactDetail: ParamGetContactDetail?): Any {
        paramGetContactDetail?.contactId?.let {
            val contact = contactRepo.findByIdAndUserId(it, getCurrentUserId())
                .orElseThrow { APIItemNotFoundException(ITEM_NOT_FOUND) }
            return DetailResponse(contact.toModel())
        }
        throw APIBadRequestException(MISSING_ID)
    }

    override fun getContactList(paramPaging: ParamPaging, paramGetContactList: ParamGetContactList?): Any {
        // find all by specification
        val mappedModels = this.contactRepo.findAll(ContactSpecification(getCurrentUserId(), paramPaging)).toModels()

        // map to response object
        return ListResponse(
            mappedModels, mappedModels.size, paramPaging.offset,
            if (paramPaging.limit != null && paramPaging.limit > 0) paramPaging.limit else null
        )
    }

    override fun updateContact(
        paramContactModelBody: ParamContactModelBody?,
        paramUpdateContact: ParamUpdateContact?
    ): Any {
        if (paramContactModelBody != null) {

            val contactModelBody = paramContactModelBody.contactModelBody

            contactModelBody?.itemid.let {
                if (it != null) {
                    val contact = contactRepo.findByIdAndUserId(it.toLong(), getCurrentUserId())
                        .orElseThrow { APIItemNotFoundException(ITEM_NOT_FOUND) } // get or throw

                    contactModelBody.toEntity(contact) // map fields from model to entity

                    contactRepo.save(contact) // update entity

                    return DetailResponse(contact.toModel()) // return response and return model
                }
            }
            throw APIBadRequestException(MISSING_ID)
        }
        throw APIBadRequestException(INVALID_BODY)
    }

}
