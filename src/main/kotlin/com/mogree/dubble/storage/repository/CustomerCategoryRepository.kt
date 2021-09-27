package com.mogree.dubble.storage.repository

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.CustomerEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface CustomerCategoryRepository : CrudRepository<CustomerEntity, Int> {

    companion object {
        const val TABLE = Config.Database.TABLE_CUSTOMER
        const val CATEGORY_FIELD = "category"
    }

    @Query(
            "SELECT " + CATEGORY_FIELD +
                    " FROM " + TABLE +
                    " WHERE id = :currentCustomerId", nativeQuery = true
    )
    fun getCategory(currentCustomerId: Long?): String?

    @Transactional
    @Modifying
    @Query(
            "UPDATE " + TABLE +
                    " SET " + CATEGORY_FIELD + " = :newCategory" +
                    " WHERE id = :currentCustomerId", nativeQuery = true
    )
    fun updateCategory(newCategory: String, currentCustomerId: Long?): Int

    @Query(
            "SELECT * " +
                    "FROM " + TABLE +
                    " WHERE " + CATEGORY_FIELD + " LIKE %:filter%" +
                    " LIMIT :offset , :limit", nativeQuery = true
    )
    fun getCustomerByFilter(offset: Int?, limit: Int?, filter: String): List<CustomerEntity>

    @Query(
            "SELECT COUNT(*) " +
                    "FROM " + TABLE +
                    " WHERE " + CATEGORY_FIELD + " LIKE %:filter%", nativeQuery = true
    )
    fun getSize(filter: String): Int
}