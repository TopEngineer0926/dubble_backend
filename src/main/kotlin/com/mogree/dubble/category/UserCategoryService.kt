package com.mogree.dubble.category

import com.mogree.dubble.storage.repository.UserCategoryRepository
import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.entity.db.MediaEntity
import com.mogree.dubble.entity.db.UserEntity
import com.mogree.dubble.storage.repository.MediaRepository
import com.mogree.dubble.service.media.MediaService
import org.springframework.stereotype.Service


@Service
class UserCategoryService(
        private val userCategoryRepository: UserCategoryRepository,
        private val mediaService: MediaService
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

    fun getMasterAllInfo(userId: Long): UserEntity? =
            userCategoryRepository.getMasterAllInfo(userId)

    fun getMasterIdField(): Long? =
            userCategoryRepository.getMasterIdField(getCurrentUserId())

    fun clearMaster(): Int? =
            userCategoryRepository.clearMaster(getCurrentUserId())

    fun getMasterLogo(userId: Long): String? =
            userCategoryRepository.getMasterLogo(userId)

    fun copyMasterLogo(masterLogoFileName: String): MediaEntity? {
        return mediaService.copyMedia(masterLogoFileName)
    }

}