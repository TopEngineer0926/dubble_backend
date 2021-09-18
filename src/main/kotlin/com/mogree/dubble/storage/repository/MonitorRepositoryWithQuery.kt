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
    }

    @Query(
            "SELECT * " +
                    "FROM " + TABLE +
                    " LIMIT :offset , :limit", nativeQuery = true
    )
    fun findAll(offset: Int?, limit: Int?): List<MonitorEntity>

}