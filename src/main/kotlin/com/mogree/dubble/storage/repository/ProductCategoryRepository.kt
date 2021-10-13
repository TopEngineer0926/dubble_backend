package com.mogree.dubble.storage.repository

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.ProductEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ProductCategoryRepository : CrudRepository<ProductEntity, Int> {

    companion object {
        const val TABLE = Config.Database.TABLE_PRODUCT
        const val TEMPLATE_FIELD = "template"
        const val USER_ID = "user_id"
    }

    @Query(
            "SELECT * " +
                    "FROM " + TABLE +
                    " WHERE NOT LOWER(" + TEMPLATE_FIELD + ") LIKE LOWER( CONCAT( '%', '|Vorlage|', '%') )" +
                    " AND " + TEMPLATE_FIELD + " LIKE %:filter%" +
                    " AND " + USER_ID + " = :userId" +
                    " LIMIT :offset , :limit", nativeQuery = true
    )
    fun getProductByFilter(offset: Int?, limit: Int?, filter: String, userId: Long): List<ProductEntity>

    @Query(
            "SELECT COUNT(*) " +
                    "FROM " + TABLE +
                    " WHERE NOT LOWER(" + TEMPLATE_FIELD + ") LIKE LOWER( CONCAT( '%', '|Vorlage|', '%') )" +
                    " AND " + TEMPLATE_FIELD + " LIKE %:filter%" +
                    " AND " + USER_ID + " = :userId", nativeQuery = true
    )
    fun getSize(filter: String, userId: Long): Int

    @Transactional
    @Modifying
    @Query(
            "DELETE FROM " + TABLE +
                    " WHERE NOT LOWER(" + TEMPLATE_FIELD + ") LIKE LOWER( CONCAT( '%', '|Vorlage|', '%') )" +
                    " AND " + TEMPLATE_FIELD + " LIKE %:filter%" +
                    " AND " + USER_ID + " = :userId", nativeQuery = true
    )
    fun deleteProductByFilter(filter: String, userId: Long): Int
}