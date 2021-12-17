package com.mogree.dubble.mapper

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.UserEntity
import com.mogree.server.gen.model.RegisterModel
import com.mogree.server.gen.model.UserAuthModel
import com.mogree.server.gen.model.UserModel

/**
 * Map UserModel to UserEntity
 */
fun RegisterModel.createEntity(encodedPassword: String): UserEntity {
    val entity = UserEntity()
    entity.email = this.email
    entity.lastName = this.lastname
    entity.password = encodedPassword // set encoded password
    entity.role = Config.Roles.USER // User can not register with different role so it must be set here statically
    entity.firstName = this.firstname
    entity.companyName = this.companyName
    return entity
}

fun UserEntity.toModel(): UserModel {
    val model = UserModel()
    model.email = this.email
    model.lastname = this.lastName
    model.firstname = this.firstName
    model.itemid = this.id.toString()
    model.companyName = this.companyName
    model.mainColor = this.mainColor
    model.secondaryColor = this.secondaryColor
    model.logoPosition = this.logoPosition?.let { UserModel.LogoPositionEnum.valueOf(it.name.toUpperCase()) }
    model.contactButtonColor = this.contactButtonColor
    model.mailHeadline = this.mailHeadline
    model.mailTextline = this.mailTextline
    return model
}

fun UserEntity.toAuthModel(token: String): UserAuthModel {
    val model = this.toModel()
    val authModel = UserAuthModel()
    authModel.itemid = model.itemid
    authModel.userModel = model
    authModel.token = token
    return authModel
}

fun UserModel.toEntity(entity: UserEntity) {
    entity.email = this.email
    entity.lastName = this.lastname
    entity.firstName = this.firstname
    entity.companyName = this.companyName
    entity.mainColor = this.mainColor
    entity.secondaryColor = this.secondaryColor
    entity.contactButtonColor = this.contactButtonColor
    entity.mailHeadline = this.mailHeadline
    entity.mailTextline = this.mailTextline

    if(this.logoPosition!=null){
        entity.logoPosition = Config.LogoPosition.valueOf(this.logoPosition.name.toLowerCase())
    }
}

fun List<UserEntity>.toModels(): List<UserModel> {
    return this.map { it.toModel() }
}
