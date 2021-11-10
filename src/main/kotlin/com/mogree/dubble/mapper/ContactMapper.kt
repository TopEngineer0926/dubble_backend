package com.mogree.dubble.mapper

import com.mogree.dubble.entity.db.ContactEntity
import com.mogree.dubble.entity.db.UserEntity
import com.mogree.server.gen.model.ContactModel

/**
 * Map ContactModel to ContactEntity
 */
fun ContactModel.createEntity(user: UserEntity): ContactEntity {
    val entity = ContactEntity()

    entity.email = this.email
    entity.lastName = this.lastname
    entity.firstName = this.firstname
    entity.phoneNumber = this.phoneNumber
    entity.user = user // set user relation
    entity.abbreviation = this.abbreviation

    return entity
}

fun ContactEntity.toModel(): ContactModel {
    val model = ContactModel()

    model.itemid = this.id.toString()
    model.email = this.email
    model.lastname = this.lastName
    model.firstname = this.firstName
    model.phoneNumber = this.phoneNumber
    model.abbreviation = this.abbreviation
    model.inviteStatus = this.inviteStatus

    return model
}

fun ContactModel.toEntity(entity: ContactEntity) {
    entity.email = this.email
    entity.lastName = this.lastname
    entity.firstName = this.firstname
    entity.phoneNumber = this.phoneNumber
    entity.abbreviation = this.abbreviation
    entity.inviteStatus = this.inviteStatus
}

fun List<ContactEntity>.toModels(): List<ContactModel> {
    return this.map { it.toModel() }
}
