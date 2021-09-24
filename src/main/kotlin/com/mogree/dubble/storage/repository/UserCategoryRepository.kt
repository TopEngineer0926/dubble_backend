package com.mogree.dubble.storage.repository

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.UserEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface UserCategoryRepository : CrudRepository<UserEntity, Int> {

    companion object {
        const val TABLE = Config.Database.TABLE_USER
        const val CATEGORY_FIELD = "category"
    }

    @Query(
            "SELECT " + CATEGORY_FIELD +
                    " FROM " + TABLE +
                    " WHERE id = :currentUserId", nativeQuery = true
    )
    fun getCategory(currentUserId: Long?): String?

    @Transactional
    @Modifying
    @Query(
            "UPDATE " + TABLE +
                    " SET " + CATEGORY_FIELD + " = :newCategory" +
                    " WHERE id = :currentUserId", nativeQuery = true
    )
    fun updateCategory(newCategory: String, currentUserId: Long?): Int
}