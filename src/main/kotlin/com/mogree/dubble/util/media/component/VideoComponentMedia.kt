package com.mogree.dubble.util.media.component

import com.mogree.dubble.config.Config
import com.mogree.dubble.util.media.config.MediaConfig
import com.mogree.dubble.util.media.data.MediaData
import com.mogree.dubble.util.vimeo.VimeoUploadHelper
import com.mogree.spring.exception.APIBadRequestException
import com.mogree.spring.exception.APIInternalServerException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * handles video media
 */
@Component
class VideoComponentMedia : BaseComponentMedia() {

    @Autowired
    val vimeoUploadHelper: VimeoUploadHelper? = null

    @Value("\${media-upload.video-path}")
    private lateinit var uploadPath: String

    @Value("\${api.vimeo.path}")
    private lateinit var vimeo_path: String

    override fun validMimeTypes(): Array<String> {
        // if needed to update mime-types - must be updated in the swagger description too
        return arrayOf("video/mp4", "video/quicktime", "video/mpeg", "video/3gpp")
    }

    override fun getPath(): String {
        return uploadPath
    }

    override fun validate(file: MultipartFile): Boolean {
        return true
    }

    override fun getMediaType(): Int {
        return MediaConfig.MediaType.MEDIATYPE_VIDEO
    }

    override fun upload(file: MultipartFile, title: String?): MediaData {

        val newFileName = generateUniqueFileName((getByteArray(file)))

        if (!validate(file)) {
            throw APIBadRequestException(Config.ErrorMessagesGerman.INVALID_FILE)
        }

        try {
//            val path = getPath() + "/" + newFileName
            val videoEndPoint = vimeoUploadHelper?.upload(newFileName, convertMultipartToFile(file), title)
            val newFileName1 = videoEndPoint!!.substringAfterLast("/")
            val path = "$vimeo_path/$newFileName1"
            //update status of media in database
//            mediaHelper.getMediaByFileName(fileName)
//                    ?.let { mediaHelper.updateMedia(it, null, null, Config.FileStatus.FINISHED, newPath, newFileName) }
            return MediaData(newFileName1, path, getMediaType())
        } catch (e: IOException) {
            throw APIInternalServerException(Config.ErrorMessagesGerman.WRITE_FILE(newFileName))
        }
    }

    /**
     * save the uploaded file temporary
     */
    @Throws(IOException::class)
    private fun convertMultipartToFile(file: MultipartFile): File {
        val convFile = File(getPath() + "/" + System.currentTimeMillis().toString() + file.name)
        convFile.createNewFile()
        val fos = FileOutputStream(convFile)
        fos.write(file.bytes)
        fos.close()
        return convFile
    }
}
