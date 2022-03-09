package com.mogree.dubble.service.template

import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.mapper.toModels
import com.mogree.dubble.storage.repository.TemplateRepository
import com.mogree.server.gen.model.ProductModel
import org.springframework.stereotype.Service


@Service
class TemplateService(
        private val templateRepository: TemplateRepository
) {

    fun getTemplate(offset: Int, limit: Int): List<ProductModel> {
        if (limit == 0)
            return templateRepository.getTemplate(offset, getSize(), getCurrentUserId()).toModels()
        else
            return templateRepository.getTemplate(offset, limit, getCurrentUserId()).toModels()
    }

    fun getTemplateWithMaster(offset: Int, limit: Int, masterId: Long): List<ProductModel> {
        if (limit == 0)
            return templateRepository.getTemplateWithMaster(offset, getTemplateSize(masterId), getCurrentUserId(), masterId).toModels()
        else
            return templateRepository.getTemplateWithMaster(offset, limit, getCurrentUserId(), masterId).toModels()
    }

    fun getSize(): Int =
            templateRepository.getSize(getCurrentUserId())

    fun getTemplateSize(masterId: Long): Int =
            templateRepository.getTemplateSize(getCurrentUserId(), masterId)

    fun getTemplateByFilter(offset: Int, limit: Int, filter: String): List<ProductModel> {
        if (limit == 0)
            return templateRepository.getTemplateByFilter(offset, getSizeByFilter(filter), filter, getCurrentUserId()).toModels()
        else
            return templateRepository.getTemplateByFilter(offset, limit, filter, getCurrentUserId()).toModels()
    }

    fun getSizeByFilter(filter: String): Int =
            templateRepository.getTemplateSizeByFilter(filter, getCurrentUserId())
}