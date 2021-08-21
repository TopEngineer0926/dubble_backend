package com.mogree.dubble.config.security

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.UserEntity
import com.mogree.dubble.storage.repository.UserRepository
import com.mogree.jwt.api.DecodedToken
import com.mogree.jwt.api.Principal
import com.mogree.jwt.api.TokenService
import com.mogree.jwt.cache.database.DefaultPersistentCache
import com.mogree.kotlin.extensions.guard
import com.mogree.spring.exception.APIUnauthorizedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import java.util.*

@Service
class AppTokenService @Autowired constructor(
    cache: DefaultPersistentCache,
    private val userRepo: UserRepository
) : TokenService(cache) {

    @Value("\${api.security.token.secret}")
    override lateinit var tokenSecret: String

    @Value("\${api.security.token.issuer}")
    override lateinit var tokenIssuer: String

    override fun authorize(decodedToken: DecodedToken): Principal {
        return User(decodedToken, decodedToken.subject.toLong())
    }

    /**
     * Find current user or throw an error.
     */
    fun getCurrentUser(): UserEntity {
        val userId = getCurrentUserId()
        return userRepo.findById(userId)
            .orElseThrow { APIUnauthorizedException(Config.ErrorMessagesGerman.UNAUTHORIZED) }
    }

    /**
     * Find current user or return optional.
     */
    fun findCurrentUser(): Optional<UserEntity> {
        val optionalUserId = findCurrentUserId()

        return if (optionalUserId.isPresent) {
            userRepo.findById(optionalUserId.get())
        } else {
            Optional.empty()
        }
    }

    data class User(val decodedToken: DecodedToken, val userId: Long) : Principal {

        fun getEmail(): String {
            return decodedToken.additionalData[ADDITIONAL_DATA_EMAIL].guard {
                throwTokenInvalid()
            }
        }

        override fun getAuthorities(): List<GrantedAuthority> {
            val role = decodedToken.additionalData[ADDITIONAL_DATA_ROLE]?.toUpperCase().guard {
                throwTokenInvalid()
            }
            // check if roles exist
            if (Config.Roles.values().map { it.name }.contains(role).not()) {
                throwTokenInvalid()
            }
            return mutableListOf<GrantedAuthority>(SimpleGrantedAuthority(role))
        }

        private fun throwTokenInvalid(): Nothing {
            throw APIUnauthorizedException(Config.ErrorMessagesGerman.TOKEN_INVALID)
        }
    }

}
