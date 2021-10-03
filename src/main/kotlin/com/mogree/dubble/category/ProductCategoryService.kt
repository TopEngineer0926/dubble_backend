package com.mogree.dubble.category

import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.mapper.toModels
import com.mogree.dubble.storage.repository.ProductCategoryRepository
import com.mogree.server.gen.model.ProductModel
import org.springframework.stereotype.Service


@Service
class ProductCategoryService(
        private val productCategoryRepository: ProductCategoryRepository
) {

    fun getProductByFilter(offset: Int, limit: Int, filter: String): List<ProductModel> {
        if (limit == 0)
            return productCategoryRepository.getProductByFilter(offset, getSize(filter),filter, getCurrentUserId()).toModels()
        else
            return productCategoryRepository.getProductByFilter(offset, limit, filter, getCurrentUserId()).toModels()
    }

    fun getSize(filter: String): Int =
            productCategoryRepository.getSize(filter, getCurrentUserId())

    fun deleteProductByFilter(filter: String): Int =
            productCategoryRepository.deleteProductByFilter(filter, getCurrentUserId())
}