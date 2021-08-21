package com.mogree.dubble.validator.account

import com.mogree.dubble.config.Config
import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.storage.repository.UserRepository
import com.mogree.dubble.util.enumContains
import com.mogree.dubble.validator.ValidatorHelper
import com.mogree.dubble.validator.ValidatorHelper.Companion.validatePattern
import com.mogree.server.gen.model.PasswordResetModel
import com.mogree.server.gen.model.PasswordUpdateModel
import com.mogree.server.gen.model.RegisterModel
import com.mogree.server.gen.model.UserModel
import com.mogree.server.gen.param.ParamActivateBody
import com.mogree.server.gen.param.ParamPasswordUpdate
import com.mogree.spring.exception.APIBadRequestException
import com.mogree.spring.exception.APIConflictException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Validate an incoming account models.
 */
@Component
class AccountValidator @Autowired constructor(
        private val userRepo: UserRepository
) {

    fun isValid(model: RegisterModel): Boolean {
        // check if all required fields are not null
        this.validateMainUserInfo(
                email = model.email,
                firstName = model.firstname,
                lastName = model.lastname
        ) // validate main user info

        model.email = model.email.toLowerCase()
        this.validateEmailIsNewAndValid(model.email) // validate email
        ValidatorHelper.validateNotBlank(model.password, "Password") // validate password

        return true
    }

    fun isValid(model: ParamActivateBody): Boolean {
        // check if all required fields are not null
        ValidatorHelper.validateNotBlank(
                model.activationCode, "Activation code"
        ) // validate if not empty activation code
        this.validateIfExistOrAlreadyActivated(model.activationCode) // validate if code valid and if user exist and not activated
        return true
    }

    fun isValid(model: PasswordResetModel): Boolean {
        // check if all required fields are not null
        ValidatorHelper.validateNotBlank(model.email, "Email") // validate if not empty email
        this.validateEmailAndUserExists(model.email) // check if email is valid and user with email exists
        return true
    }

    fun isValid(model: PasswordUpdateModel): Boolean {
        // check if all required fields are not null
        ValidatorHelper.validateNotBlank(model.newPassword, "New Password") // validate if not empty new password
        ValidatorHelper.validateNotBlank(
                model.passwordResetCode, "Password Reset Code"
        ) // validate if not empty password reset code
        this.validateIfPasswordResetCodeExist(model.passwordResetCode) // validate if user with given password reset code exists
        return true
    }

    fun isValid(model: ParamPasswordUpdate): Boolean {
        // check if all required fields are not null
        ValidatorHelper.validateNotBlank(
                model.mogreePasswordOld, "Old Password"
        ) // validate if not empty old password
        ValidatorHelper.validateNotBlank(
                model.mogreePassword, "New Password"
        ) // validate if not empty new password
        return true
    }

    fun isValid(model: UserModel): Boolean {
        // check if all required fields are not null
        this.validateMainUserInfo(
                email = model.email,
                firstName = model.firstname,
                lastName = model.lastname
        ) // validate main user info
        model.email = model.email.toLowerCase()
        this.validateEmailIsNewAndValid(model.email, getCurrentUserId()) // validate email

        val colorRegex = Config.RegexPattern.HEX_COLOR.toRegex()

        // validate main color
        val mainColor = model.mainColor
        if (mainColor != null) {
            validatePattern(colorRegex, mainColor, "Main Color")
        }

        // validate secondary color
        val secondaryColor = model.secondaryColor
        if (secondaryColor != null) {
            validatePattern(colorRegex, secondaryColor, "Secondary Color")
        }

        return true
    }

    /* ***** Private Methods ***** */

    private fun validateIfCorrectGender(gender: String?) {
        ValidatorHelper.validateNotBlank(gender, "Gender")
        if (!enumContains<Config.Genders>(gender!!)) {
            throw APIBadRequestException(Config.ErrorMessagesGerman.GENDER_NOT_SUPPORTED(gender))
        }
    }

    private fun validateEmailIsNewAndValid(email: String) {
        ValidatorHelper.validateEmailPattern(email) // throw if invalid

        if (userRepo.existsByEmailIgnoreCase(email)) {
            throw APIConflictException(Config.ErrorMessagesGerman.USER_EMAIL_EXISTS(email))
        }
    }

    private fun validateEmailIsNewAndValid(email: String, avoidUserId: Long) {
        ValidatorHelper.validateEmailPattern(email) // throw if invalid

        if (userRepo.existsByIdIsNotAndEmailIgnoreCase(avoidUserId, email)) {
            throw APIConflictException(Config.ErrorMessagesGerman.USER_EMAIL_EXISTS(email))
        }
    }

    private fun validateIfExistOrAlreadyActivated(activationCode: String) {
        val user = userRepo.findByActivationCode(activationCode)
                .orElseThrow { APIBadRequestException(Config.ErrorMessagesGerman.INVALID_ACTIVATION_CODE) }

        if (user.activated) {
            throw APIConflictException(Config.ErrorMessagesGerman.USER_ALREADY_ACTIVATED)
        }

    }

    private fun validateEmailAndUserExists(email: String) {
        ValidatorHelper.validateEmailPattern(email) // throw if invalid

        if (!userRepo.existsByEmailIgnoreCase(email)) {
            throw APIBadRequestException(Config.ErrorMessagesGerman.NO_USER_FOR_EMAIL(email))
        }
    }

    private fun validateIfPasswordResetCodeExist(passwordResetCode: String) {
        if (!userRepo.existsByPasswordResetCode(passwordResetCode)) {
            throw APIBadRequestException(Config.ErrorMessagesGerman.NO_USER_FOR_RESETCODE(passwordResetCode))
        }
    }

    private fun validateMainUserInfo(email: String?, firstName: String?, lastName: String?) {
        ValidatorHelper.validateNotBlank(email, "Email") // validate if not empty email
        ValidatorHelper.validateNotBlank(firstName, "First name") // validate first name
        ValidatorHelper.validateNotBlank(lastName, "Last name") // validate last name
    }

}
