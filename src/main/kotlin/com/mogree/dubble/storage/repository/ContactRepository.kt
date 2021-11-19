package com.mogree.dubble.storage.repository

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.ContactEntity
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
interface ContactRepository : CrudRepository<ContactEntity, Long>, JpaSpecificationExecutor<ContactEntity> {

    companion object {
        const val TABLE = Config.Database.TABLE_CONTACT
    }

    @Query
    @Transactional
    fun deleteByIdAndUserId(id: Long, userId: Long)

    @Query
    fun findByIdAndUserId(id: Long, userId: Long): Optional<ContactEntity>

    @Query(
            "SELECT * " +
                    " FROM " + TABLE +
                    " WHERE id = :contactId", nativeQuery = true
    )
    fun getContactInfo(contactId: Int): ContactEntity

}
