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
        const val TEMPLATE_FIELD = "template"
        const val DATE_COLUMN = "created_at"
    }

    @Query(
            "SELECT * " +
                    "FROM " + TABLE +
                    " WHERE LOWER(" + TEMPLATE_FIELD + ") LIKE LOWER( CONCAT( '%', '|Vorlage|', '%') )" +
                    " AND " + USER_ID + " = :userId" +
                    " ORDER BY " + DATE_COLUMN + " DESC" +
                    " LIMIT :offset , :limit", nativeQuery = true
    )
    fun getTemplate(offset: Int?, limit: Int?, userId: Long): List<ProductEntity>

    @Query(
            "SELECT * " +
                    "FROM " + TABLE +
                    " WHERE LOWER(" + TEMPLATE_FIELD + ") LIKE LOWER( CONCAT( '%', '|Vorlage|', '%') )" +
                    " AND (" + USER_ID + " = :userId" + " OR " + USER_ID + " = :masterId)" +
                    " ORDER BY " + DATE_COLUMN + " DESC" +
                    " LIMIT :offset , :limit", nativeQuery = true
    )
    fun getTemplateWithMaster(offset: Int?, limit: Int?, userId: Long, masterId: Long): List<ProductEntity>

    @Query(
            "SELECT COUNT(*) " +
                    "FROM " + TABLE +
                    " WHERE LOWER(" + TEMPLATE_FIELD + ") LIKE LOWER( CONCAT( '%', '|Vorlage|', '%') )" +
                    " AND " + USER_ID + " = :userId", nativeQuery = true
    )
    fun getSize(userId: Long): Int

    @Query(
            "SELECT COUNT(*) " +
                    "FROM " + TABLE +
                    " WHERE LOWER(" + TEMPLATE_FIELD + ") LIKE LOWER( CONCAT( '%', '|Vorlage|', '%') )" +
                    " AND (" + USER_ID + " = :userId" + " OR " + USER_ID + " = :masterId)", nativeQuery = true
    )
    fun getTemplateSize(userId: Long, masterId: Long): Int

    @Query(
            "SELECT * " +
                    "FROM " + TABLE +
                    " WHERE LOWER(" + TEMPLATE_FIELD + ") LIKE LOWER( CONCAT( '%', '|Vorlage|', '%') )" +
                    " AND " + TEMPLATE_FIELD + " LIKE %:filter%" +
                    " AND " + USER_ID + " = :userId" +
                    " ORDER BY " + DATE_COLUMN + " DESC" +
                    " LIMIT :offset , :limit", nativeQuery = true
    )
    fun getTemplateByFilter(offset: Int?, limit: Int?, filter: String, userId: Long): List<ProductEntity>

    @Query(
            "SELECT COUNT(*) " +
                    "FROM " + TABLE +
                    " WHERE LOWER(" + TEMPLATE_FIELD + ") LIKE LOWER( CONCAT( '%', '|Vorlage|', '%') )" +
                    " AND " + TEMPLATE_FIELD + " LIKE %:filter%" +
                    " AND " + USER_ID + " = :userId", nativeQuery = true
    )
    fun getTemplateSizeByFilter(filter: String, userId: Long): Int
}