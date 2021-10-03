package com.mogree.dubble.category

import com.mogree.dubble.category.payload.CategoryResponse
import com.mogree.dubble.category.payload.CategoryUpdateRequest
import com.mogree.dubble.category.payload.CustomerListResponse
import com.mogree.dubble.entity.db.CustomerEntity
import com.mogree.server.gen.model.CustomerModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("customer")
class CustomerCategoryController (
        private val customerCategoryService: CustomerCategoryService
) {

    @GetMapping("/category")
    fun getCategoryById(
            @RequestParam(value = "id") id: Long
    ): ResponseEntity<CategoryResponse> {
        val category = customerCategoryService.getCategoryById(id)
        val response = CategoryResponse(category)
        return ResponseEntity.ok<CategoryResponse>(response)
    }

    @PutMapping("/category")
    fun updateCategoryById(@RequestBody newCategory: CategoryUpdateRequest): ResponseEntity<CategoryResponse> {
        var result = customerCategoryService.updateCategoryById(newCategory.category, newCategory.id)
        if (result > 0) {
            val response = CategoryResponse("success")
            return ResponseEntity.ok<CategoryResponse>(response)
        } else {
            val response = CategoryResponse("fail")
            return ResponseEntity.ok<CategoryResponse>(response)
        }
    }

    @GetMapping("/customer_by_filter")
    fun getCustomerByFilter(
            @RequestParam(value = "offset") offset: Int,
            @RequestParam(value = "limit") limit: Int,
            @RequestParam(value = "filter") filter: String
    ): ResponseEntity<CustomerListResponse> {
        val list: List<CustomerModel> = customerCategoryService.getCustomerByFilter(offset, limit, filter)
        val response = CustomerListResponse(offset, list, limit, customerCategoryService.getSize(filter))
        return ResponseEntity.ok<CustomerListResponse>(response)
    }

    @DeleteMapping("/customer_by_filter")
    fun deleteCustomerByFilter(
            @RequestParam(value = "filter") filter: String
    ): ResponseEntity<CategoryResponse> {
        val result = customerCategoryService.deleteCustomerByFilter(filter)
        if (result > 0) {
            val response = CategoryResponse("success")
            return ResponseEntity.ok<CategoryResponse>(response)
        } else {
            val response = CategoryResponse("fail")
            return ResponseEntity.ok<CategoryResponse>(response)
        }
    }
}