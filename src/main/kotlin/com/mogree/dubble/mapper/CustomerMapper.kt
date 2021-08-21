package com.mogree.dubble.mapper

import com.mogree.dubble.entity.db.CustomerEntity
import com.mogree.dubble.entity.db.UserEntity
import com.mogree.server.gen.model.CustomerModel

/**
 * Map CustomerModel to CustomerEntity
 */
fun CustomerModel.createEntity(user: UserEntity): CustomerEntity {
    val entity = CustomerEntity()

    entity.firstName = this.firstname
    entity.lastName = this.lastname
    entity.email = this.email
    entity.phoneNumber = this.phoneNumber
    entity.user = user
    entity.customerNumber = this.customerNumber
    entity.academicDegreePreceding = this.academicDegreePreceding
    entity.academicDegreeSubsequent = this.academicDegreeSubsequent

    return entity
}

fun CustomerModel.toEntity(entity: CustomerEntity) {
    entity.email = this.email
    entity.lastName = this.lastname
    entity.firstName = this.firstname
    entity.phoneNumber = this.phoneNumber
    entity.customerNumber = this.customerNumber
    entity.academicDegreePreceding = this.academicDegreePreceding
    entity.academicDegreeSubsequent = this.academicDegreeSubsequent
}

fun CustomerEntity.toModel(): CustomerModel {
    val model = CustomerModel()

    model.itemid = this.id.toString()
    model.firstname = this.firstName
    model.lastname = this.lastName
    model.email = this.email
    model.phoneNumber = this.phoneNumber
    model.customerNumber = this.customerNumber
    model.academicDegreePreceding = this.academicDegreePreceding
    model.academicDegreeSubsequent = this.academicDegreeSubsequent

    return model
}

fun List<CustomerEntity>.toModels(): List<CustomerModel> {
    return this.map { it.toModel() }
}
