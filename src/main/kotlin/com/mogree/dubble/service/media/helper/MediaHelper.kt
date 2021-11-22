package com.mogree.dubble.service.media.helper

import com.mogree.dubble.entity.db.MediaEntity
import com.mogree.dubble.mapper.toModel
import com.mogree.dubble.mapper.toModels
import com.mogree.dubble.storage.repository.MediaRepository
import com.mogree.dubble.util.media.config.MediaConfig
import com.mogree.server.gen.model.MediaModel
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

/**
 * reads media files from db and returns media models
 */
@Component
class MediaHelper(
    private val mediaRepository: MediaRepository
) {

    @Value("\${web-domain}")
    private lateinit var webDomain: String

    @Value("\${api.vimeo.player}")
    private lateinit var viemeoUrl: String

    fun getMediaList(foreignTable: String?, foreignId: Int?, userId: Long): List<MediaModel> {
        val list = mediaRepository.findAllFromForeignTable(foreignTable, foreignId)
        return list.toModels(webDomain,viemeoUrl)
    }

    fun getMediaList(foreignTable: String?, foreignId: Int?): List<MediaModel> {
        val list = mediaRepository.findAllFromForeignTable(foreignTable, foreignId)
        return list.toModels(webDomain,viemeoUrl)
    }

    fun getMediaByID(itemId: Int?): MediaEntity? {
        return itemId?.let { mediaRepository.findByIdOrNull(it) }
    }

    fun getMediaByFileName(fileName: String): MediaEntity? {
        return mediaRepository.findByFileName(fileName)
    }

    fun updateMedia(
        media: MediaEntity,
        title: String?,
        order: Int?,
        status: String?,
        path: String?,
        fileName: String?
    ): MediaModel {
        order?.let { media.order = it }
        title?.let { media.title = it }
        status?.let { media.status = it }
        path?.let { media.path = it }
        fileName?.let { media.fileName = it }
        mediaRepository.save(media)

        if(media.mediaType==MediaConfig.MediaType.MEDIATYPE_VIDEO){
            return media.toModel(viemeoUrl)
        }
        return media.toModel(webDomain)
    }

}
