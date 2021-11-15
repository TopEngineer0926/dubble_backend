package com.mogree.dubble.storage.repository

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.UserEntity
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    companion object {
        const val TABLE = Config.Database.TABLE_USER
    }

    /* ***** Find one ***** */

    @Query
    fun findByEmailIgnoreCase(email: String): Optional<UserEntity>

    @Query
    fun findByActivationCode(activationCode: String): Optional<UserEntity>

    @Query
    fun findByPasswordResetCode(passwordResetCode: String): Optional<UserEntity>

    /* ***** Exists ***** */

    @Query
    fun existsByEmailIgnoreCase(email: String): Boolean

    @Query
    fun existsByActivationCode(activationCode: String): Boolean

    @Query
    fun existsByPasswordResetCode(passwordResetCode: String): Boolean

    @Query
    fun existsByIdIsNotAndEmailIgnoreCase(userId: Long, email: String): Boolean

    @Query(
            "SELECT * " +
                    " FROM " + TABLE +
                    " WHERE id = :userId", nativeQuery = true
    )
    fun getCurrentUserInfo(userId: Long): UserEntity
}
