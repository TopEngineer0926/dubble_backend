package com.mogree.dubble.storage.repository

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.MediaEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MediaRepository : CrudRepository<MediaEntity, Int> {

    companion object {
        const val TABLE = Config.Database.TABLE_MEDIA
    }

    @Query(
        "SELECT * " +
                "FROM " + TABLE +
                " WHERE foreign_table = :foreignTable AND foreign_id = :foreignId", nativeQuery = true
    )
    fun findAllFromForeignTable(foreignTable: String?, foreignId: Int?): List<MediaEntity>

    @Query(
        "SELECT * " +
                "FROM " + TABLE +
                " WHERE foreign_table = :foreignTable AND foreign_id = :foreignId AND user_id = :userid",
        nativeQuery = true
    )
    fun findAllFromForeignTable(foreignTable: String?, foreignId: Int?, userid: Long): List<MediaEntity>

    @Query(
        "SELECT * " +
                "FROM " + TABLE +
                " WHERE file_name = :fileName",
        nativeQuery = true
    )
    fun findByFileName(fileName:String):MediaEntity
}
