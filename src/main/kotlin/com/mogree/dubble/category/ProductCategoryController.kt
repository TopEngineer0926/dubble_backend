package com.mogree.dubble.category

import com.mogree.dubble.category.payload.CategoryResponse
import com.mogree.dubble.category.payload.ProductListResponse
import com.mogree.server.gen.model.ProductModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("product")
class ProductCategoryController (
        private val productCategoryService: ProductCategoryService
) {

    @GetMapping("/product_by_filter")
    fun getProductByFilter(
            @RequestParam(value = "offset") offset: Int,
            @RequestParam(value = "limit") limit: Int,
            @RequestParam(value = "filter") filter: String
    ): ResponseEntity<ProductListResponse> {
        val list: List<ProductModel> = productCategoryService.getProductByFilter(offset, limit, filter)
        val response = ProductListResponse(offset, list, limit, productCategoryService.getSize(filter))
        return ResponseEntity.ok<ProductListResponse>(response)
    }

    @DeleteMapping("/product_by_filter")
    fun deleteProductByFilter(
            @RequestParam(value = "filter") filter: String
    ): ResponseEntity<CategoryResponse> {
        val result = productCategoryService.deleteProductByFilter(filter)
        if (result > 0) {
            val response = CategoryResponse("success")
            return ResponseEntity.ok<CategoryResponse>(response)
        } else {
            val response = CategoryResponse("fail")
            return ResponseEntity.ok<CategoryResponse>(response)
        }
    }
}