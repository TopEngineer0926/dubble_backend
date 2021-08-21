package com.mogree.dubble.entity

import com.mogree.dubble.config.Config
import com.mogree.dubble.config.security.AppTokenService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import java.time.OffsetDateTime
import javax.persistence.*

/**
 * Audition class for entities.
 * Provides info about created record in the table:
 * 1. When the record was created;
 * 2. Who has created the record;
 * 3. When the record was updated;
 * 4. Who has updated the record;
 *
 * Every entity must extend this class.
 */
@MappedSuperclass
abstract class EntityAudition {

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: OffsetDateTime? = null // When the record was created
        private set

    @Column(name = "created_by", nullable = false, updatable = false)
    private var createdBy: String? = null // Who has created a record

    @Column(name = "modified_by", nullable = true)
    private var modifiedBy: String? = null //  Who has updated a record

    @Column(name = "modified_at", nullable = true)
    private var modifiedAt: OffsetDateTime? = null // When the record was updated

    @PrePersist
    private fun prePersist() { // runs before creating (save)
        createdAt = OffsetDateTime.now()
        createdBy = getAuthenticatedUserName()
    }

    @PreUpdate
    private fun preUpdate() { // runs before updating (save)
        modifiedAt = OffsetDateTime.now()
        modifiedBy = getAuthenticatedUserName()
    }

    @Transient
    private fun getAuthenticatedUserName(): String { // get current user name or guest
        val authentication: Authentication? = SecurityContextHolder.getContext().authentication
        return if (authentication == null || authentication.name == Config.StaticUsername.ANONYMOUS) {
            Config.StaticUsername.GUEST // return guest name cause user is not authorized
        } else {
            val user = authentication.principal as AppTokenService.User
            val email: String = try {
                user.getEmail()
            } catch (e: Exception) {
                Config.StaticUsername.UNKNOWN
            }
            email
        }
    }

}
