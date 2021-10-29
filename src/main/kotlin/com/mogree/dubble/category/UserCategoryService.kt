package com.mogree.dubble.category

import com.mogree.dubble.storage.repository.UserCategoryRepository
import com.mogree.dubble.config.security.getCurrentUserId
import org.springframework.stereotype.Service


@Service
class UserCategoryService(
        private val userCategoryRepository: UserCategoryRepository
) {

    fun getCategoryById(): String? =
            userCategoryRepository.getCategory(getCurrentUserId())

    fun updateCategoryById(newCategory: String): Int =
            userCategoryRepository.updateCategory(newCategory, getCurrentUserId())

    fun getTemplateById(): String? =
            userCategoryRepository.getTemplate(getCurrentUserId())

    fun updateTemplateById(newCategory: String): Int =
            userCategoryRepository.updateTemplate(newCategory, getCurrentUserId())

    fun updateMaster(masterId: Long): Int =
            userCategoryRepository.updateMaster(masterId, getCurrentUserId())

    fun getMasterIdByEmail(masterEmail: String): Long? =
            userCategoryRepository.getMasterIdByEmail(masterEmail)

    fun getMasterEmail(userId: Long): String? =
            userCategoryRepository.getMasterEmail(userId)

    fun getMasterIdField(): Long? =
            userCategoryRepository.getMasterIdField(getCurrentUserId())

    fun clearMaster(): Int? =
            userCategoryRepository.clearMaster(getCurrentUserId())

}