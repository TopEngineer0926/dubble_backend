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
        const val TEMPLATE_FIELD = "template"
        const val ID = "id"
        const val MASTER_ID_FIELD = "master_id"
        const val EMAIL_FIELD = "email"
        const val ACTIVATE_CODE = 1
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

    @Query(
            "SELECT " + TEMPLATE_FIELD +
                    " FROM " + TABLE +
                    " WHERE id = :currentUserId", nativeQuery = true
    )
    fun getTemplate(currentUserId: Long?): String?

    @Transactional
    @Modifying
    @Query(
            "UPDATE " + TABLE +
                    " SET " + TEMPLATE_FIELD + " = :newCategory" +
                    " WHERE id = :currentUserId", nativeQuery = true
    )
    fun updateTemplate(newCategory: String, currentUserId: Long?): Int

    @Query(
            "SELECT " + ID +
                    " FROM " + TABLE +
                    " WHERE email = :masterEmail and is_activated = " + ACTIVATE_CODE, nativeQuery = true
    )
    fun getMasterIdByEmail(masterEmail: String?): Long?

    @Query(
            "SELECT *" +
                    " FROM " + TABLE +
                    " WHERE id = :userId", nativeQuery = true
    )
    fun getMasterAllInfo(userId: Long?): UserEntity?

    @Query(
            "SELECT " + MASTER_ID_FIELD +
                    " FROM " + TABLE +
                    " WHERE id = :currentUserId", nativeQuery = true
    )
    fun getMasterIdField(currentUserId: Long?): Long?

    @Transactional
    @Modifying
    @Query(
            "UPDATE " + TABLE +
                    " SET " + MASTER_ID_FIELD + " = :masterId" +
                    " WHERE id = :currentUserId", nativeQuery = true
    )
    fun updateMaster(masterId: Long, currentUserId: Long?): Int

    @Transactional
    @Modifying
    @Query(
            "UPDATE " + TABLE +
                    " SET " + MASTER_ID_FIELD + " = Null" +
                    " WHERE id = :currentUserId", nativeQuery = true
    )
    fun clearMaster(currentUserId: Long?): Int

    @Query(
            "SELECT file_name " +
                    " FROM media " +
                    " WHERE user_id = :currentUserId and media_type=1 and foreign_table like 'account' and foreign_id=:currentUserId", nativeQuery = true
    )
    fun getMasterLogo(currentUserId: Long?): String?
}