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
        const val USER_ID = "user_id"
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
                    " From " + TABLE +
                    " WHERE " + CATEGORY_FIELD + " LIKE %:filter%" +
                    " AND " + USER_ID + " = :userId" +
                    " LIMIT :offset , :limit", nativeQuery = true
    )
    fun getCustomerByFilter(offset: Int?, limit: Int?, filter: String, userId: Long): List<CustomerEntity>

    @Query(
            "SELECT COUNT(*) " +
                    "FROM " + TABLE +
                    " WHERE " + CATEGORY_FIELD + " LIKE %:filter%" +
                    " AND " + USER_ID + " = :userId", nativeQuery = true
    )
    fun getSize(filter: String, userId: Long): Int

    @Transactional
    @Modifying
    @Query(
            "DELETE FROM " + TABLE +
                    " WHERE " + CATEGORY_FIELD + " LIKE %:filter%" +
                    " AND " + USER_ID + " = :userId", nativeQuery = true
    )
    fun deleteCustomerByFilter(filter: String, userId: Long): Int
}