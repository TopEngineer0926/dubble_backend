package com.mogree.dubble.service.account.helper

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.UserEntity
import com.mogree.dubble.storage.repository.UserRepository
import com.mogree.dubble.util.HashUtil
import com.mogree.spring.exception.APIUnauthorizedException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class AccountHelper constructor(
    private val userRepo: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun setActivationCode(user: UserEntity) {
        user.activated = false
        val activationCode = HashUtil.sha512(
            user.email + user.firstName + user.lastName
                    + OffsetDateTime.now().toString()
        )
        if (this.userRepo.existsByActivationCode(activationCode)) {
            setActivationCode(user) // use recursion if user with activationCode already exists
        } else {
            user.activationCode = activationCode // set generated activation code
        }
    }

    fun setPasswordResetCode(user: UserEntity) {
        val passwordResetCode = HashUtil.sha512(
            user.email + user.firstName + user.lastName
                    + OffsetDateTime.now().toString()
        )
        if (this.userRepo.existsByPasswordResetCode(passwordResetCode)) { // check if exists already some user with generated code
            setPasswordResetCode(user) // use recursion if user with password reset code already exists
        } else {
            user.passwordResetCode = passwordResetCode // set generated password reset code
        }
    }

    fun checkLoginConditions(email: String, password: String): UserEntity {
        val user = userRepo.findByEmailIgnoreCase(email).orElseThrow {
            throw APIUnauthorizedException(Config.ErrorMessagesGerman.NO_USER_FOR_EMAIL(email))
        }
        if (!user.activated) {
            throw APIUnauthorizedException(Config.ErrorMessagesGerman.USER_NOT_ACTIVATED)
        }
        if (!passwordEncoder.matches(password, user.password)) {
            throw APIUnauthorizedException(Config.ErrorMessagesGerman.INVALID_PASSWORD)
        }
        return user
    }

}
