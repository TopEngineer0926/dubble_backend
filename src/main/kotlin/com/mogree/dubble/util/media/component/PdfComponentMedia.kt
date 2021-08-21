package com.mogree.dubble.util.media.component

import com.mogree.dubble.util.media.config.MediaConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

/**
 * handle pdf media items
 */
@Component
class PdfComponentMedia : BaseComponentMedia() {

    @Value("\${media-upload.pdf-path}")
    private lateinit var uploadPath: String

    override fun validMimeTypes(): Array<String> {
        // if needed to update mime-types - must be updated in the swagger description too
        return arrayOf("application/pdf")
    }

    override fun getPath(): String {
        return uploadPath
    }

    override fun validate(file: MultipartFile): Boolean {
        return true
    }

    override fun getMediaType(): Int {
        return MediaConfig.MediaType.MEDIATYPE_PDF
    }
}
