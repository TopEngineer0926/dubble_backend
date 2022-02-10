package com.mogree.dubble.mapper

import com.mogree.dubble.entity.db.CustomerEntity
import com.mogree.dubble.entity.db.UserEntity
import com.mogree.server.gen.model.CustomerModel

/**
 * Map CustomerModel to CustomerEntity
 */
fun CustomerModel.createEntity(user: UserEntity): CustomerEntity {
    val entity = CustomerEntity()

    entity.firstname = this.firstname
    entity.lastname = this.lastname
    entity.email = this.email
    entity.phoneNumber = this.phoneNumber
    entity.user = user
    entity.customerNumber = this.customerNumber
    entity.academicDegreePreceding = this.academicDegreePreceding
    entity.academicDegreeSubsequent = this.academicDegreeSubsequent
    entity.category = this.category
    entity.companyName = this.companyName
    entity.domainName = this.domainName

    return entity
}

fun CustomerModel.toEntity(entity: CustomerEntity) {
    entity.email = this.email
    entity.lastname = this.lastname
    entity.firstname = this.firstname
    entity.phoneNumber = this.phoneNumber
    entity.customerNumber = this.customerNumber
    entity.academicDegreePreceding = this.academicDegreePreceding
    entity.academicDegreeSubsequent = this.academicDegreeSubsequent
    entity.category = this.category
    entity.companyName = this.companyName
    entity.domainName = this.domainName
}

fun CustomerEntity.toModel(): CustomerModel {
    val model = CustomerModel()

    model.itemid = this.id.toString()
    model.firstname = this.firstname
    model.lastname = this.lastname
    model.email = this.email
    model.phoneNumber = this.phoneNumber
    model.customerNumber = this.customerNumber
    model.academicDegreePreceding = this.academicDegreePreceding
    model.academicDegreeSubsequent = this.academicDegreeSubsequent
    model.category = this.category
    model.companyName = this.companyName
    model.domainName = this.domainName

    return model
}

fun List<CustomerEntity>.toModels(): List<CustomerModel> {
    return this.map { it.toModel() }
}
