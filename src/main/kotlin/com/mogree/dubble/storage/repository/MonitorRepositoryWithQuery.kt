package com.mogree.dubble.storage.repository

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.MonitorEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MonitorRepositoryWithQuery : CrudRepository<MonitorEntity, Int> {

    companion object {
        const val TABLE = Config.Database.TABLE_MONITOR
        const val DATE_COLUMN = "sendingDate"
    }

    @Query(
            "SELECT * " +
                    "FROM " + TABLE +
                    " WHERE userId=ANY(select id from user WHERE id=:currentUserId or master_id=:currentUserId)" +
                    " ORDER BY " + DATE_COLUMN + " DESC" +
                    " LIMIT :offset , :limit", nativeQuery = true
    )
    fun findAll(offset: Int?, limit: Int?, currentUserId: Long?): List<MonitorEntity>

    @Query(
            "SELECT COUNT(*) " +
                    "FROM " + TABLE +
                    " WHERE userId=ANY(select id from user WHERE id=:currentUserId or master_id=:currentUserId)", nativeQuery = true
    )
    fun getSize(currentUserId: Long?): Int

    @Query(
            "SELECT id " +
                    "FROM user" +
                    " WHERE master_id = :currentUserId", nativeQuery = true
    )
    fun getAllSubAccounts(currentUserId: Long?): ArrayList<Int>
}