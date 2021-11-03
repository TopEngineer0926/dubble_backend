package com.mogree.dubble.util.media.component

import com.mogree.dubble.util.media.data.MediaData
import org.springframework.web.multipart.MultipartFile

/**
 * interface for media items
 */
interface MediaComponent {

    /**
     * which extensions is be uploaded by the uploader impl
     */
    fun validMimeTypes(): Array<String>

    /**
     * returns path, filename and mediatype
     */
    fun upload(file: MultipartFile,title:String?): MediaData

    /**
     * returns path, filename and mediatype
     */
    fun copy(fileName: String): MediaData

    /**
     * where the uploaded file should be saved
     */
    fun getPath(): String

    /**
     * implement a custom validation
     */
    fun validate(file: MultipartFile): Boolean

    /**
     * returns the type of the implementation
     */
    fun getMediaType(): Int

}