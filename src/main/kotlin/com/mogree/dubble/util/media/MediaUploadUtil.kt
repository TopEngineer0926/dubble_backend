package com.mogree.dubble.util.media

import com.mogree.dubble.config.Config
import com.mogree.dubble.util.media.component.BaseComponentMedia
import com.mogree.dubble.util.media.component.MediaComponent
import com.mogree.dubble.util.media.data.MediaData
import com.mogree.spring.exception.APIUnprocessableEntityException
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class MediaUploadUtil(
    private val mediaComponents: List<MediaComponent>,
) {

    /**
     * Uploads a media item and saves it in the implemented path
     */
    fun upload(file: MultipartFile, title: String?): MediaData {

        val fileBytes = BaseComponentMedia.getByteArray(file)

        val uploadImpl = getImpl(fileBytes)
            ?: throw APIUnprocessableEntityException(Config.ErrorMessagesGerman.EXTENSION_NOT_IMPLEMENTED)
        return uploadImpl.upload(file, title)
    }

    /**
     * Duplicate media with another new name
     */
    fun copyMedia(fileName: String): MediaData {
        val imageComponent = mediaComponents.find { uploadComponent -> uploadComponent.validMimeTypes().contains("image/png")}
                ?: throw APIUnprocessableEntityException(Config.ErrorMessagesGerman.EXTENSION_NOT_IMPLEMENTED)

        return imageComponent.copy(fileName)
    }

    /**
     * get the implementation from the file MimeType
     */
    private fun getImpl(fileBytes: ByteArray): MediaComponent? {
        return mediaComponents.find { uploadComponent ->
            uploadComponent.validMimeTypes().contains(BaseComponentMedia.getMimeType(fileBytes))
        }
    }
}