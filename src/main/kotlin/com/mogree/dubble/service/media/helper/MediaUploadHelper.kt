package com.mogree.dubble.service.media.helper

import com.mogree.dubble.config.Config
import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.entity.db.MediaEntity
import com.mogree.dubble.mapper.toModel
import com.mogree.dubble.storage.repository.MediaRepository
import com.mogree.dubble.util.media.MediaUploadUtil
import com.mogree.dubble.util.media.config.MediaConfig
import com.mogree.dubble.util.media.data.MediaData
import com.mogree.server.gen.model.MediaModel
import com.mogree.server.gen.param.ParamUploadImage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * handles the media upload
 */
@Component
class MediaUploadHelper(
    private val mediaUploadUtil: MediaUploadUtil,
    private val mediaRepository: MediaRepository
) {

    @Value("\${web-domain}")
    private lateinit var webDomain: String

    /**
     * uploads media, saves entity and returns model
     */
    fun upload(paramUploadImage: ParamUploadImage): MediaModel {
        // returns path and media type
        val mediaData = mediaUploadUtil.upload(paramUploadImage.file, paramUploadImage.title)
        var status: String? = null
        if (mediaData.type == MediaConfig.MediaType.MEDIATYPE_VIDEO) { // is video
            status = Config.FileStatus.PROCESSING
        }
        val mediaEntity = saveMedia(
            mediaData,
            paramUploadImage.modelId,
            paramUploadImage.modelName,
            paramUploadImage.title,
            paramUploadImage.order,
            status
        )
        return mediaEntity.toModel(webDomain)
    }

    /*
     * save the uploaded media in the db
     */
    fun saveMedia(
        mediaData: MediaData,
        foreignId: Int,
        foreignTable: String,
        title: String?,
        order: Int?,
        status: String?
    ): MediaEntity {
        val entity = MediaEntity()
        entity.foreignId = foreignId
        entity.foreignTable = foreignTable
        entity.fileName = mediaData.fileName
        entity.path = mediaData.path
        entity.mediaType = mediaData.type
        entity.userid = getCurrentUserId()

        title?.let { entity.title = it }
        status?.let { entity.status = it }
        order?.let { entity.order = it }

        return mediaRepository.save(entity)
    }

    /*
    * duplicate media with another new name
    */
    fun copyMedia(mediaFileName: String): MediaEntity {
        val mediaData = mediaUploadUtil.copyMedia(mediaFileName)
        return saveMedia(
            mediaData,
            getCurrentUserId().toInt(),
            "account",
            null,
            null,
            null
        )
    }

    /*
   * duplicate media with another new name
   */
    fun copyMediaNew(mediaFileName: String, userId: Long): MediaEntity {
        val mediaData = mediaUploadUtil.copyMedia(mediaFileName)
        return saveMedia(
                mediaData,
                userId.toInt(),
                "account",
                null,
                null,
                null
        )
    }
}
