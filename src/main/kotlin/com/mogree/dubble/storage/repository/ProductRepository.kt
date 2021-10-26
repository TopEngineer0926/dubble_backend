package com.mogree.dubble.storage.repository

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.ProductEntity
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
interface ProductRepository : CrudRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    companion object {
        const val TABLE = Config.Database.TABLE_PRODUCT
        const val TEMPLATE_FIELD = "template"
        const val USER_ID = "user_id"
    }

    @Query
    @Transactional
    fun deleteByIdAndUserId(id: Long, userId: Long)

    @Query
    fun findByShareCode(shareCode: String): Optional<ProductEntity>

    @Query
    fun findByIdAndUserId(id: Long, userId: Long): Optional<ProductEntity>

    @Query
    fun existsByShareCode(shareCode: String): Boolean

    @Query
    fun existsByContactId(contactId: Long): Boolean

    @Query
    fun existsByCustomerId(customerId: Long): Boolean

    @Query(
            "SELECT * " +
                    "FROM " + TABLE +
                    " WHERE (NOT LOWER(" + TEMPLATE_FIELD + ") LIKE LOWER( CONCAT( '%', '|Vorlage|', '%') )" +
                    " OR " + TEMPLATE_FIELD + " IS NULL)" +
                    " AND " + USER_ID + " = :userId", nativeQuery = true
    )
    fun getProductAll(userId: Long): List<ProductEntity>

    @Query(
            "SELECT * " +
                    "FROM " + TABLE +
                    " WHERE (NOT LOWER(" + TEMPLATE_FIELD + ") LIKE LOWER( CONCAT( '%', '|Vorlage|', '%') )" +
                    " OR " + TEMPLATE_FIELD + " IS NULL)" +
                    " AND " + USER_ID + " = :userId" +
                    " LIMIT :offset , :limit", nativeQuery = true
    )
    fun getProductLimit(offset: Int?, limit: Int?, userId: Long): List<ProductEntity>

    @Query(
            "SELECT COUNT(*) " +
                    "FROM " + TABLE +
                    " WHERE (NOT LOWER(" + TEMPLATE_FIELD + ") LIKE LOWER( CONCAT( '%', '|Vorlage|', '%') )" +
                    " OR " + TEMPLATE_FIELD + " IS NULL)" +
                    " AND " + USER_ID + " = :userId", nativeQuery = true
    )
    fun getSizeAll(userId: Long): Int
}
