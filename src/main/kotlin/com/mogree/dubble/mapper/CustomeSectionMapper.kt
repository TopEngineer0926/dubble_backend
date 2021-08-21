package com.mogree.dubble.mapper

import com.mogree.dubble.entity.db.embeddable.CustomSectionEntity
import com.mogree.server.gen.model.CustomSectionModel

/**
 * Model `CustomSectionModel` to `CustomSectionEntity` entity.
 */
fun CustomSectionModel.toEntity(): CustomSectionEntity {
    val entity = CustomSectionEntity()

    entity.headline = this.headLine
    entity.link = this.link
    entity.buttonText = this.buttonText
    entity.customText = this.customText

    return entity
}

/**
 * Model `CustomSectionModel` to `CustomSectionEntity` entity list mapper.
 */
fun List<CustomSectionModel>.toEntities(): List<CustomSectionEntity> {
    return this.map { it.toEntity() }
}

/**
 * Entity `CustomSectionEntity` to `CustomSectionModel` model entity.
 */
fun CustomSectionEntity.toModel(): CustomSectionModel {
    val model = CustomSectionModel()

    model.headLine = this.headline
    model.link = this.link
    model.buttonText = this.buttonText
    model.customText = this.customText

    return model
}

/**
 * Entity `CustomSectionEntity` to `CustomSectionModel` model list mapper.
 */
fun List<CustomSectionEntity>.toModels(): List<CustomSectionModel> {
    return this.map { it.toModel() }
}
