package com.mogree.dubble.config.security

import com.mogree.dubble.config.Config.ResponseMessage.UNAUTHORIZED
import com.mogree.dubble.entity.db.UserEntity
import com.mogree.jwt.api.Principal
import com.mogree.jwt.api.TokenConfig
import com.mogree.spring.exception.APIUnauthorizedException
import java.util.*

const val ADDITIONAL_DATA_ROLE = "role"
const val ADDITIONAL_DATA_EMAIL = "email"

/**
 * Get the id of the current user.
 *
 * @return the id of the currently authorized user.
 */
fun getCurrentUserId(): Long =
    findCurrentUserId().orElseThrow { APIUnauthorizedException(UNAUTHORIZED) }

/**
 * Get the id of the current user (wrapped by Optional).
 *
 * @return the optional id of the currently authorized user.
 */
fun findCurrentUserId(): Optional<Long> {
    val user = Principal.get<AppTokenService.User>()
    return Optional.ofNullable(user.userId)
}

/**
 * Get the JWT of the current use.
 *
 * @return the JWT of the current user.
 */
fun getCurrentUserJWT(): String = findCurrentUserJWT().orElseThrow { APIUnauthorizedException(UNAUTHORIZED) }

/**
 * Get the JWT of the current user (wrapped by Optional).
 *
 * @return the optional JWT of the current user.
 */
fun findCurrentUserJWT(): Optional<String> =
    Optional.ofNullable(Principal.get<AppTokenService.User>().decodedToken.tokenString)

fun buildTokenConfig(user: UserEntity): TokenConfig {
    val data = mutableMapOf<String, String>()
    data[ADDITIONAL_DATA_ROLE] = user.role.name
    data[ADDITIONAL_DATA_EMAIL] = user.email.toLowerCase()
    val tokenConfig = TokenConfig(user.id.toString())
    tokenConfig.additionalData = data
    tokenConfig.allowMultipleTokensPerSubject()
    return tokenConfig
}
