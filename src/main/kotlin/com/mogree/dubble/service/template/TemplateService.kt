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

    fun getTemplateByStatus(offset: Int, limit: Int): List<ProductModel> {
        if (limit == 0)
            return templateRepository.getTemplateByStatus(offset, getSizeByStatus("DRAFT"), "DRAFT", getCurrentUserId()).toModels()
        else
            return templateRepository.getTemplateByStatus(offset, limit, "DRAFT", getCurrentUserId()).toModels()
    }

    fun getSizeByStatus(status: String): Int =
            templateRepository.getTemplateSizeByStatus(status, getCurrentUserId())

    fun getTemplateByFilter(offset: Int, limit: Int, filter: String): List<ProductModel> {
        if (limit == 0)
            return templateRepository.getTemplateByFilter(offset, getSizeByFilter("DRAFT", filter), "DRAFT", filter, getCurrentUserId()).toModels()
        else
            return templateRepository.getTemplateByFilter(offset, limit, "DRAFT", filter, getCurrentUserId()).toModels()
    }

    fun getSizeByFilter(status: String, filter: String): Int =
            templateRepository.getTemplateSizeByFilter(status, filter, getCurrentUserId())
}