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
                    " WHERE userId = :currentUserId" +
                    " ORDER BY " + DATE_COLUMN + " DESC" +
                    " LIMIT :offset , :limit", nativeQuery = true
    )
    fun findAll(offset: Int?, limit: Int?, currentUserId: Long?): List<MonitorEntity>

}