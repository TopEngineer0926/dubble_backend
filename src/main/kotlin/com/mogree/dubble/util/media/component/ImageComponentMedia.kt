package com.mogree.dubble.util.media.component

import com.mogree.dubble.util.media.config.MediaConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

/**
 * handle image media items
 */
@Component
class ImageComponentMedia : BaseComponentMedia() {

    @Value("\${media-upload.image-path}")
    private lateinit var uploadPath: String

    override fun validMimeTypes(): Array<String> {
        // if needed to update mime-types - must be updated in the swagger description too
        return arrayOf("image/png", "image/jpeg")
    }

    override fun getPath(): String {
        return uploadPath
    }

    override fun validate(file: MultipartFile): Boolean {
        return true
    }

    override fun getMediaType(): Int {
        return MediaConfig.MediaType.MEDIATYPE_IMAGE
    }
}
