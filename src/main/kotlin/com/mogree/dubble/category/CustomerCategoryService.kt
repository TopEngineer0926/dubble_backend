package com.mogree.dubble.category

import com.mogree.dubble.mapper.toModels
import com.mogree.dubble.storage.repository.CustomerCategoryRepository
import com.mogree.server.gen.model.CustomerModel
import com.mogree.dubble.config.security.getCurrentUserId
import org.springframework.stereotype.Service


@Service
class CustomerCategoryService(
        private val customerCategoryRepository: CustomerCategoryRepository
) {

    fun getCategoryById(id: Long): String? =
            customerCategoryRepository.getCategory(id)

    fun updateCategoryById(newCategory: String, id: Long): Int =
            customerCategoryRepository.updateCategory(newCategory, id)

    fun getCustomerByFilter(offset: Int, limit: Int, filter: String): List<CustomerModel> {
        if (limit == 0)
            return customerCategoryRepository.getCustomerByFilter(offset, getSize(filter),filter, getCurrentUserId()).toModels()
        else
            return customerCategoryRepository.getCustomerByFilter(offset, limit, filter, getCurrentUserId()).toModels()
    }

    fun getSize(filter: String): Int =
            customerCategoryRepository.getSize(filter, getCurrentUserId())

    fun deleteCustomerByFilter(filter: String): Int =
            customerCategoryRepository.deleteCustomerByFilter(filter, getCurrentUserId())
}