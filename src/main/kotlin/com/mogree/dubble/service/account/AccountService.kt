package com.mogree.dubble.service.account

import com.mogree.dubble.config.Config
import com.mogree.dubble.config.Config.Companion.LOGGER
import com.mogree.dubble.config.security.AppTokenService
import com.mogree.dubble.config.security.buildTokenConfig
import com.mogree.dubble.config.security.getCurrentUserJWT
import com.mogree.dubble.entity.db.UserEntity
import com.mogree.dubble.mapper.createEntity
import com.mogree.dubble.mapper.toAuthModel
import com.mogree.dubble.mapper.toEntity
import com.mogree.dubble.mapper.toModel
import com.mogree.dubble.notification.AsyncNotificationHelper
import com.mogree.dubble.storage.repository.UserRepository
import com.mogree.dubble.service.account.helper.AccountHelper
import com.mogree.server.gen.api.AccountApiDelegate
import com.mogree.server.gen.model.UserModel
import com.mogree.server.gen.param.*
import com.mogree.spring.exception.APIBadRequestException
import com.mogree.spring.response.DetailResponse
import com.mogree.spring.response.StatusResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Primary
@Service
class AccountService @Autowired constructor(
    private val helper: AccountHelper,
    private val userRepo: UserRepository,
    private val tokenService: AppTokenService,
    private val passwordEncoder: PasswordEncoder,
    private val notificationHelper: AsyncNotificationHelper
) : AccountApiDelegate {

    @Transactional
    override fun activate(paramActivateBody: ParamActivateBody?, paramActivate: ParamActivate?): Any {
        val user = userRepo.findByActivationCode(paramActivateBody?.activationCode!!).map { user ->
            // activate given user
            user.activated = true
            user.activationCode = null
            LOGGER.debug("Activated user with id: ${user.id}")
            user
            }.orElseThrow { APIBadRequestException(Config.ErrorMessagesGerman.USER_EXISTENCE) }

        userRepo.save(user) // update user

        // return empty status OK
        return StatusResponse()
    }

    override fun getUserData(paramGetUserData: ParamGetUserData?): Any {
        TODO("Not yet implemented")
    }

    override fun login(paramLogin: ParamLogin): Any {
        val user =
            helper.checkLoginConditions(
                paramLogin.mogreeMail!!,
                paramLogin.mogreePassword!!
            ) // check if valid login data
        val tokenConfig = buildTokenConfig(user) // create token
        val token = tokenService.issueToken(tokenConfig) // store token into memory

        return DetailResponse(user.toAuthModel(token)) // return obj with token and user ifo
    }

    override fun logout(paramLogout: ParamLogout?): Any {
        tokenService.logout(getCurrentUserJWT())
        return StatusResponse()
    }

    override fun passwordReset(
        paramResetPasswordBody: ParamPasswordResetBody,
        paramPasswordReset: ParamPasswordReset?
    ): Any {
        val body = paramResetPasswordBody.resetPasswordBody!! // get body from param wrapper

        // get user or throw bad request error
        val user = userRepo.findByEmailIgnoreCase(body.email!!).map { user ->
            helper.setPasswordResetCode(user)
            user
        }.orElseThrow { APIBadRequestException(Config.ErrorMessagesGerman.USER_EXISTENCE) }

        helper.setPasswordResetCode(user) // set reset password code

        userRepo.save(user) // update user in the DB

        notificationHelper.sendResetPasswordEmail(user.id) // async send password reset link to user's email

        // return empty status OK
        return StatusResponse()
    }

    @Transactional
    override fun passwordResetUpdate(
        paramPasswordResetUpdateBody: ParamPasswordResetUpdateBody,
        paramPasswordResetUpdate: ParamPasswordResetUpdate?
    ): Any {
        val body = paramPasswordResetUpdateBody.passwordResetUpdateBody!! // get body from param wrapper

        // get user or throw bad request error
        val user = userRepo.findByPasswordResetCode(body.passwordResetCode!!).map { user ->
            user.passwordResetCode = null
            user.password = passwordEncoder.encode(body.newPassword)
            LOGGER.debug("Set new password for user with id: ${user.id}")
            user
        }.orElseThrow { APIBadRequestException(Config.ErrorMessagesGerman.USER_EXISTENCE) }

        userRepo.save(user) // update user in the DB

        // return empty status OK
        return StatusResponse()
    }

    override fun register(paramRegisterBody: ParamRegisterBody?, paramRegister: ParamRegister?): Any {
        val model = paramRegisterBody?.registerBody!! // get body from the request object wrapper

        val entity = model.createEntity(encodedPassword = passwordEncoder.encode(model.password)) // map model to entity

        helper.setActivationCode(entity) // generate and save activation code

        val savedEntity = userRepo.save(entity) // save entity

        notificationHelper.sendActivationEmail(savedEntity.id) // send activation email to the just registered user

        return DetailResponse(savedEntity.toModel()) // map model to entity and save and map to response model
    }

    override fun update(paramUpdateBody: ParamUpdateBody, paramUpdate: ParamUpdate?): Any {
        val model = paramUpdateBody.updateBody

        val currentUser = tokenService.getCurrentUser() // get currently authorized user

        val wasEmailChanged = this.update(model, currentUser) // update user is transaction

        if (wasEmailChanged) { // if email was changed then activation code must be sent
            notificationHelper.sendActivationEmail(currentUser.id) // send activation email
            this.logout(null) // logout current user
        }

        return DetailResponse(currentUser.toModel())
    }

    @Transactional
    fun update(model: UserModel, updatingUser: UserEntity): Boolean {
        val isEmailChanged: Boolean = updatingUser.email != model.email // check if email was changed

        model.toEntity(updatingUser) // map new fields into entity object

        if (isEmailChanged) { // if email was changed then user should be set to unactivated status
            helper.setActivationCode(updatingUser) // set activation code and activate = false
        }

        userRepo.save(updatingUser)
        return isEmailChanged // return if email was changed
    }

    @Transactional
    override fun updatePassword(
        paramPasswordUpdate: ParamPasswordUpdate,
        paramUpdatePassword: ParamUpdatePassword?
    ): Any {
        val currentUser = tokenService.getCurrentUser() // get current user

        val isOldPasswordValid = passwordEncoder.matches(paramPasswordUpdate.mogreePasswordOld, currentUser.password)

        if (isOldPasswordValid) {
            currentUser.password =
                passwordEncoder.encode(paramPasswordUpdate.mogreePassword) // encode and set new password
            userRepo.save(currentUser) // update user info in the DB
        } else {
            throw APIBadRequestException(Config.ErrorMessagesGerman.INVALID_OLD_PASSWORD)
        }

        // return empty status OK
        return StatusResponse()
    }

}
