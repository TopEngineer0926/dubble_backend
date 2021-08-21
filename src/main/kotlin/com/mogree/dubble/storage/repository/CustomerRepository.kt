package com.mogree.dubble.storage.repository

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.CustomerEntity
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
interface CustomerRepository : CrudRepository<CustomerEntity, Long>, JpaSpecificationExecutor<CustomerEntity> {

    companion object {
        const val TABLE = Config.Database.TABLE_CUSTOMER
    }

    @Query
    @Transactional
    fun deleteByIdAndUserId(id: Long, userId: Long)

    @Query
    fun findByIdAndUserId(id: Long, userId: Long): Optional<CustomerEntity>

}
