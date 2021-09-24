package com.mogree.dubble.category

import com.mogree.dubble.category.payload.CategoryResponse
import com.mogree.dubble.category.payload.CategoryUpdateRequest
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

}