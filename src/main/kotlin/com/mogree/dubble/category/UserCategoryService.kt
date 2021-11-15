package com.mogree.dubble.category

import com.mogree.dubble.storage.repository.UserCategoryRepository
import com.mogree.dubble.storage.repository.ContactRepository
import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.entity.db.MediaEntity
import com.mogree.dubble.entity.db.UserEntity
import com.mogree.dubble.entity.db.ContactEntity
import com.mogree.dubble.config.Config
import com.mogree.dubble.notification.AsyncNotificationHelper
import com.mogree.dubble.storage.repository.MediaRepository
import com.mogree.dubble.service.media.MediaService
import com.mogree.dubble.storage.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.security.crypto.password.PasswordEncoder

@Service
class UserCategoryService(
        private val userCategoryRepository: UserCategoryRepository,
        private val contactRepository: ContactRepository,
        private val userRepository: UserRepository,
        private val mediaService: MediaService,
        private val passwordEncoder: PasswordEncoder,
        private val notificationHelper: AsyncNotificationHelper
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

    fun copyMasterLogoNew(masterLogoFileName: String, userId: Long): MediaEntity? {
        return mediaService.copyMediaNew(masterLogoFileName, userId)
    }


    /**
     * for creaing new invite user
     * */
    fun getContactInfo(contactId: String): ContactEntity {
        return contactRepository.getContactInfo(contactId?.toInt())
    }

    fun getCurrentUserInfo(): UserEntity {
        return userRepository.getCurrentUserInfo(getCurrentUserId())
    }

    fun updateContactInviteField(contactInfo: ContactEntity): ContactEntity {
        return contactRepository.save(contactInfo)
    }

    fun createNewInviteUser(email: String,
                            first_name: String,
                            last_name: String,
                            is_activated: Int,
                            company_name: String?,
                            main_color: String?,
                            secondary_color: String?,
                            logo_position: Config.LogoPosition?,
                            master_id: Long): UserEntity {

        val tempPassword = "1234567890"
        val entity = UserEntity()
        entity.email = email
        entity.firstName = first_name
        entity.lastName = last_name
        entity.password = passwordEncoder.encode(tempPassword)
        entity.role = Config.Roles.USER // User can not register with different role so it must be set here statically
        entity.companyName = company_name
        entity.mainColor = main_color
        entity.logoPosition = logo_position
        entity.secondaryColor = secondary_color
        entity.masterId = master_id
        entity.activated = true

        return userRepository.save(entity)
    }

    fun sendResetPasswordEmail(userId: Long) {
        return notificationHelper.sendResetPasswordEmail(userId)
    }
}