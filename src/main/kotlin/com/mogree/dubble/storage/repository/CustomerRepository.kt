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
        const val USER_ID = "user_id"
    }

    @Query
    @Transactional
    fun deleteByIdAndUserId(id: Long, userId: Long)

    @Query
    fun findByIdAndUserId(id: Long, userId: Long): Optional<CustomerEntity>

    @Query(
            "SELECT * " +
                    " FROM " + TABLE +
                    " WHERE id=:id", nativeQuery = true
    )
    fun getCustomerInfo(id: Long): CustomerEntity

    @Query(
            "SELECT * " +
                    " From " + TABLE +
                    " WHERE " + USER_ID + " = :userId", nativeQuery = true
    )
    fun getCustomers(userId: Long): List<CustomerEntity>

}
