package com.mogree.dubble.category

import com.mogree.dubble.category.payload.CategoryResponse
import com.mogree.dubble.category.payload.CategoryUpdateRequest
import com.mogree.dubble.category.payload.MasterResponse
import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.ContactEntity
import com.mogree.dubble.entity.db.MediaEntity
import com.mogree.dubble.entity.db.UserEntity
import com.mogree.server.gen.model.MediaModel
import com.mogree.spring.response.DetailResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.mogree.spring.exception.APIBadRequestException


@RestController
@RequestMapping("account")
class UserCategoryController (
        private val userCategoryService: UserCategoryService
) {

    @GetMapping("/category")
    fun getCategoryById(): ResponseEntity<CategoryResponse> {
        val category = userCategoryService.getCategoryById()
        val response = CategoryResponse(category)
        return ResponseEntity.ok<CategoryResponse>(response)
    }

    @PutMapping("/category")
    fun updateCategoryById(@RequestBody newCategory: CategoryUpdateRequest): ResponseEntity<CategoryResponse> {
        var result = userCategoryService.updateCategoryById(newCategory.category)
        if (result > 0) {
            val response = CategoryResponse("success")
            return ResponseEntity.ok<CategoryResponse>(response)
        } else {
            val response = CategoryResponse("fail")
            return ResponseEntity.ok<CategoryResponse>(response)
        }
    }

    @GetMapping("/template")
    fun getTemplateById(): ResponseEntity<CategoryResponse> {
        val category = userCategoryService.getTemplateById()
        val response = CategoryResponse(category)
        return ResponseEntity.ok<CategoryResponse>(response)
    }

    @PutMapping("/template")
    fun updateTemplateById(@RequestBody newCategory: CategoryUpdateRequest): ResponseEntity<CategoryResponse> {
        var result = userCategoryService.updateTemplateById(newCategory.category)
        if (result > 0) {
            val response = CategoryResponse("success")
            return ResponseEntity.ok<CategoryResponse>(response)
        } else {
            val response = CategoryResponse("fail")
            return ResponseEntity.ok<CategoryResponse>(response)
        }
    }

    @PutMapping("/master")
    fun updateMaster(@RequestBody newMasterEmail: String): ResponseEntity<CategoryResponse> {

        var masterId = userCategoryService.getMasterIdByEmail(newMasterEmail)
        if (masterId != null) {

            var result = userCategoryService.updateMaster(masterId)
            if (result != null) {
                val response = CategoryResponse("success")
                return ResponseEntity.ok<CategoryResponse>(response)
            } else {
                val response = CategoryResponse("fail")
                return ResponseEntity.ok<CategoryResponse>(response)
            }

        } else {
            val response = CategoryResponse("no master")
            return ResponseEntity.ok<CategoryResponse>(response)
        }

    }

    @GetMapping("/master")
    fun getMasterAllInfo(): ResponseEntity<MasterResponse> {

        val masterId = userCategoryService.getMasterIdField()
        if (masterId != null) {
            val masterAllInfo: UserEntity? = userCategoryService.getMasterAllInfo(masterId)
            val response = MasterResponse(true, masterAllInfo)
            return ResponseEntity.ok<MasterResponse>(response)
        } else {
            val response = MasterResponse(false, null)
            return ResponseEntity.ok<MasterResponse>(response)
        }
    }

    @DeleteMapping("/master")
    fun clearMaster(): ResponseEntity<CategoryResponse> {

        val result = userCategoryService.clearMaster()
        val response = CategoryResponse("success")
        return ResponseEntity.ok<CategoryResponse>(response)
    }

    @GetMapping("/masterlogo")
    fun getMasterLogo(): ResponseEntity<CategoryResponse> {

        val masterId = userCategoryService.getMasterIdField()
        if (masterId != null) {
            val masterLogoURL = userCategoryService.getMasterLogo(masterId)
            val response = CategoryResponse(masterLogoURL)
            return ResponseEntity.ok<CategoryResponse>(response)
        } else {
            val response = CategoryResponse("")
            return ResponseEntity.ok<CategoryResponse>(response)
        }
    }

    @PutMapping("/masterlogo")
    fun copyMasterLogo(@RequestBody masterLogoFileName: String): ResponseEntity<MediaEntity> {
        var result = userCategoryService.copyMasterLogo(masterLogoFileName)
        return ResponseEntity.ok<MediaEntity>(result)
    }

    @GetMapping("/invite/{contact_id}")
    fun sendInvite(@PathVariable contact_id: String): ResponseEntity<CategoryResponse> {
        // get contact information
        val contactInfo = userCategoryService.getContactInfo(contact_id)

        // get current user information
        val currentUserInfo = userCategoryService.getCurrentUserInfo()

        // create a new user by referring contact information and current user information
        var newUser = UserEntity()
        try {
            newUser = userCategoryService.createNewInviteUser(
                    contactInfo.email,
                    contactInfo.firstName,
                    contactInfo.lastName,
                    1,
                    currentUserInfo?.companyName,
                    currentUserInfo?.mailHeadline,
                    currentUserInfo?.mailTextline,
                    currentUserInfo?.mainColor,
                    currentUserInfo?.secondaryColor,
                    currentUserInfo?.logoPosition,
                    currentUserInfo.id)
        } catch (e: Exception){
            throw APIBadRequestException(Config.ErrorMessagesGerman.INVITE_USER_EMAIL_EXISTS)
        }

        // copy one contact including meida(photo) information
        val newContact = userCategoryService.createNewContact(contactInfo, newUser)
        val contactMediaInfo = userCategoryService.getContactMedia(contactInfo.id.toInt()) // employee photo
        if(contactMediaInfo != null) {
            userCategoryService.copyIntoNewContactMedia(contactMediaInfo, newUser, newContact)
        }

        // contact db update invite_status field
        contactInfo.inviteStatus = 1
        userCategoryService.updateContactInviteField(contactInfo)

        var responseMessage = "Invite Sent"

        try {
            // copy logo information
            // 1. get master logo file name (current user logo file name)
            val masterLogoFileName = userCategoryService.getMasterLogo(currentUserInfo.id)
            // 2. copy logo into new user logo
            if (masterLogoFileName != "" && masterLogoFileName != null) {
                userCategoryService.copyMasterLogoNew(masterLogoFileName, newUser.id)
            }
        } catch(e: Exception) {
            responseMessage = "No Logo"
        }

        // send an invitation email for resetting password
        userCategoryService.sendInviteEmail(newUser.id)
        val response = CategoryResponse(responseMessage)
        return ResponseEntity.ok<CategoryResponse>(response)
    }
}