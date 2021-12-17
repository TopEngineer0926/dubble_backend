package com.mogree.dubble.mapper

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.ContactEntity
import com.mogree.dubble.entity.db.CustomerEntity
import com.mogree.dubble.entity.db.ProductEntity
import com.mogree.dubble.entity.db.UserEntity
import com.mogree.server.gen.model.ProductModel

/**
 * Map ProductModel to ProductEntity.
 */
fun ProductModel.createEntity(user: UserEntity, contact: ContactEntity, customer: CustomerEntity?): ProductEntity {
    val entity = ProductEntity()

    entity.headline = this.headline
    entity.greeting = this.greeting
    entity.customText = this.customText
    entity.publicationStatus = Config.ProductPublicationStatuses.valueOf(this.publicationStatus.name)
    entity.reviewSectionName = this.reviewSectionName
    entity.pressInfoSectionName = this.pressInfoSectionName
    entity.internalPageTitle = this.internalPageTitle
    entity.videoSectionHeadline = this.videoSectionHeadline
    entity.template = this.template
    entity.mailHeadline = this.mailHeadline
    entity.mailTextline = this.mailTextline

    entity.user = user // set user relation
    entity.contact = contact // set contact relation
    entity.customer = customer // set customer relation

    entity.reviews = this.reviews.toEntities() // map and set reviews
    entity.pressInfos = this.pressInfos.toEntities() // map and set press infos

    return entity
}

/**
 * Map ProductModel to ProductEntity.
 * The user is not mapped here because user must be not updatable.
 */
fun ProductModel.toEntity(entity: ProductEntity, contact: ContactEntity, customer: CustomerEntity?): ProductEntity {
    entity.headline = this.headline
    entity.greeting = this.greeting
    entity.customText = this.customText
    entity.publicationStatus = Config.ProductPublicationStatuses.valueOf(this.publicationStatus.name)

    entity.reviewSectionName = this.reviewSectionName
    entity.pressInfoSectionName = this.pressInfoSectionName
    entity.internalPageTitle = this.internalPageTitle
    entity.videoSectionHeadline = this.videoSectionHeadline
    entity.template = this.template
    entity.mailHeadline = this.mailHeadline
    entity.mailTextline = this.mailTextline

    entity.contact = contact // set contact relation
    entity.customer = customer // set customer relation

    entity.reviews = this.reviews.toEntities() // map and set reviews
    entity.pressInfos = this.pressInfos.toEntities() // map and set press infos

    return entity
}

fun ProductEntity.toModel(): ProductModel {
    val model = ProductModel()

    model.itemid = this.id.toString()
    model.headline = this.headline
    model.greeting = this.greeting
    model.customText = this.customText
    model.publicationStatus = ProductModel.PublicationStatusEnum.valueOf(this.publicationStatus.name)
    model.reviewSectionName = this.reviewSectionName
    model.pressInfoSectionName = this.pressInfoSectionName
    model.shareCode = this.shareCode
    model.createdAt = this.createdAt
    model.internalPageTitle = this.internalPageTitle
    model.videoSectionHeadline = this.videoSectionHeadline
    model.template = this.template
    model.mailHeadline = this.mailHeadline
    model.mailTextline = this.mailTextline

    model.userId = this.user.id // set user relation
    model.contact = this.contact.toModel() // set contact relation

    if (this.customer != null) {
        model.customer = this.customer!!.toModel() // set customer relation
    }

    model.reviews = this.reviews.toModels() // map and set reviews
    model.pressInfos = this.pressInfos.toModels() // map and set press infos

    return model
}

fun List<ProductEntity>.toModels(): List<ProductModel> {
    return this.map { it.toModel() }
}
