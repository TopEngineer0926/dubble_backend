package com.mogree.dubble.mapper

import com.mogree.dubble.config.Config
import com.mogree.dubble.entity.db.MediaEntity
import com.mogree.dubble.util.media.config.MediaConfig
import com.mogree.server.gen.model.MediaModel
import com.mogree.spring.exception.APINotImplementedException

/**
 * mapper for media model and entity
 */
fun MediaEntity.toModel(webDomain: String): MediaModel {
    val model = MediaModel()
    model.itemid = this.id.toString()
    model.modelName = this.foreignTable
    model.modelId = this.foreignId
    model.type = Config.ItemType.MODEL_MEDIA
    model.mediaType = this.mediaType
    model.title = this.title
    model.order = this.order
    model.status = this.status

    when (this.mediaType) {
        MediaConfig.MediaType.MEDIATYPE_IMAGE -> model.url = webDomain + "/img/" + this.fileName
        MediaConfig.MediaType.MEDIATYPE_VIDEO -> model.url = webDomain + this.fileName
        MediaConfig.MediaType.MEDIATYPE_PDF -> model.url = webDomain + "/pdf/" + this.fileName
        else -> { // Note the block
            throw APINotImplementedException(Config.ErrorMessagesGerman.MEDIA_TYPE_IMPLEMENTATION)
        }
    }

    return model
}

/**
 * entity to model list mapper
 */
fun List<MediaEntity>.toModels(webDomain: String,videoDomain:String): List<MediaModel> {
    return this.map {
        if(it.mediaType==MediaConfig.MediaType.MEDIATYPE_VIDEO){
            it.toModel(videoDomain)
        }else{
            it.toModel(webDomain)
        }
    }
}
