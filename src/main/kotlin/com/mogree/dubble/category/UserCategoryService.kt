package com.mogree.dubble.category

import com.mogree.dubble.storage.repository.UserCategoryRepository
import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.entity.db.MediaEntity
import com.mogree.dubble.entity.db.UserEntity
import com.mogree.dubble.storage.repository.MediaRepository
import org.springframework.stereotype.Service


@Service
class UserCategoryService(
        private val userCategoryRepository: UserCategoryRepository,
        private val mediaRepository: MediaRepository
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

    fun copyMasterLogo(masterLogo: String): MediaEntity? {
        val entity = MediaEntity()
        entity.foreignId = getCurrentUserId().toInt()
        entity.foreignTable = "account"
        entity.fileName = masterLogo.split('/')[1]
        entity.path = masterLogo
        entity.mediaType = 1
        entity.userid = getCurrentUserId()
        return mediaRepository.save(entity)
    }
}