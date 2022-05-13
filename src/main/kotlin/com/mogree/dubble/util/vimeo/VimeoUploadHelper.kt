package com.mogree.dubble.util.vimeo

import com.clickntap.vimeo.Vimeo
import com.clickntap.vimeo.VimeoException
import com.mogree.dubble.config.Config
import com.mogree.dubble.config.Config.Companion.LOGGER
import com.mogree.dubble.service.media.helper.MediaHelper
import com.mogree.spring.exception.APIInternalServerException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.io.File


@Component
class VimeoUploadHelper @Autowired constructor(
    private val mediaHelper: MediaHelper
) {

    @Value("\${api.vimeo.accessToken}")
    private lateinit var token: String

    @Value("\${api.vimeo.path}")
    private lateinit var path: String

    /**
     * uploads videos to vimeo
     */
    fun upload(fileName: String, file: File, title: String?): String {
        try {
            val vimeo = Vimeo(token)
            val upgradeTo1080 = true
            LOGGER.info("start upload of file ${file.name} to Vimeo")
            val videoEndPoint = vimeo.addVideo(file)
            LOGGER.info("uploaded file to vimeo $videoEndPoint")
            val newFileName = videoEndPoint.substringAfterLast("/")
            val newPath = "$path/$newFileName"

            if (!title.isNullOrEmpty()) {
                updateTitle(title, newFileName)
            }

            if(file.exists()){
                file.delete() // convert multipart to file generates file on filesystem
            }

            return videoEndPoint
//            //update status of media in database
//            mediaHelper.getMediaByFileName(fileName)
//                ?.let { mediaHelper.updateMedia(it, null, null, Config.FileStatus.FINISHED, newPath, newFileName) }
        } catch (e: VimeoException) {
            LOGGER.error("Vimeo Upload failed",e)
            throw APIInternalServerException("Vimeo Upload failed")
        }

    }

    /**
     * update video vimeo video title
     */
    fun updateTitle(title: String, id: String) {
        val vimeo = Vimeo(token)
        vimeo.updateVideoMetadata(
            "/videos/$id",
            title,
            Config.Vimeo.DESCRIPTION,
            Config.Vimeo.LICENSE,
            Config.Vimeo.PRIVACY_VIEW,
            Config.Vimeo.PRIVACY_EMBED,
            Config.Vimeo.REVIEW_LINK
        )
    }

}