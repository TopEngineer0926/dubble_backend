package com.mogree.dubble.service.media.helper

import com.mogree.dubble.config.Config
import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.storage.repository.MediaRepository
import com.mogree.dubble.util.media.config.MediaConfig
import com.mogree.spring.exception.APIForbiddenException
import com.mogree.spring.exception.APIItemNotFoundException
import org.springframework.stereotype.Component
import java.io.File

/**
 * handles the media upload
 */
@Component
class MediaDeleteHelper(
    private val mediaRepository: MediaRepository
) {
    /**
     * delete a media item in db ond on server
     */
    fun delete(mediaId: Int) {

        val entityOpt = mediaRepository.findById(mediaId)
        if (entityOpt.isPresent) {
            val entity = entityOpt.get()

//            if (getCurrentUserId() != entity.userid) {
//                throw APIForbiddenException(Config.ErrorMessagesGerman.INVALID_ACCESS);
//            }

            val file = File(entity.path)
            mediaRepository.delete(entity)

            if (entity.userid == getCurrentUserId()) {
                if (entity.mediaType != MediaConfig.MediaType.MEDIATYPE_VIDEO && !file.delete()) {
                    throw APIItemNotFoundException(Config.ErrorMessagesGerman.ITEM_EXISTENCE)
                }
            }

        } else {
            throw APIItemNotFoundException(Config.ErrorMessagesGerman.MEDIA_NOTFOUND)
        }
    }
}
