package com.mogree.dubble.category

import com.mogree.dubble.category.payload.CategoryResponse
import com.mogree.dubble.category.payload.CategoryUpdateRequest
import com.mogree.dubble.category.payload.MasterResponse
import com.mogree.dubble.entity.db.MediaEntity
import com.mogree.dubble.entity.db.UserEntity
import com.mogree.server.gen.model.MediaModel
import com.mogree.spring.response.DetailResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


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
    fun copyMasterLogo(@RequestBody masterLogo: String): ResponseEntity<MediaEntity> {
        var result = userCategoryService.copyMasterLogo(masterLogo)
        return ResponseEntity.ok<MediaEntity>(result)
    }
}