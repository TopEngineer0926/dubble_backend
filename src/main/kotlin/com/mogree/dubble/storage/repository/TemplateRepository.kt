package com.mogree.dubble.storage.repository

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.ProductEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TemplateRepository : CrudRepository<ProductEntity, Int> {

    companion object {
        const val TABLE = Config.Database.TABLE_PRODUCT
        const val USER_ID = "user_id"
        const val PUBLICATION_STATUS = "publication_status"
        const val TEMPLATE_FIELD = "template"
    }

    @Query(
            "SELECT * " +
                    "FROM " + TABLE +
                    " WHERE " + PUBLICATION_STATUS + " = :status" +
                    " AND " + USER_ID + " = :userId" +
                    " LIMIT :offset , :limit", nativeQuery = true
    )
    fun getTemplateByStatus(offset: Int?, limit: Int?, status: String, userId: Long): List<ProductEntity>

    @Query(
            "SELECT COUNT(*) " +
                    "FROM " + TABLE +
                    " WHERE " + PUBLICATION_STATUS + " = :status" +
                    " AND " + USER_ID + " = :userId", nativeQuery = true
    )
    fun getTemplateSizeByStatus(status: String, userId: Long): Int

    @Query(
            "SELECT * " +
                    "FROM " + TABLE +
                    " WHERE " + TEMPLATE_FIELD + " LIKE %:filter%" +
                    " AND " + PUBLICATION_STATUS + " = :status" +
                    " AND " + USER_ID + " = :userId" +
                    " LIMIT :offset , :limit", nativeQuery = true
    )
    fun getTemplateByFilter(offset: Int?, limit: Int?, status: String, filter: String, userId: Long): List<ProductEntity>

    @Query(
            "SELECT COUNT(*) " +
                    "FROM " + TABLE +
                    " WHERE " + TEMPLATE_FIELD + " LIKE %:filter%" +
                    " AND " + PUBLICATION_STATUS + " = :status" +
                    " AND " + USER_ID + " = :userId", nativeQuery = true
    )
    fun getTemplateSizeByFilter(status: String, filter: String, userId: Long): Int
}