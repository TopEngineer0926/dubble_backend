package com.mogree.dubble.service.media

import com.mogree.dubble.config.Config
import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.entity.db.MediaEntity
import com.mogree.dubble.service.media.helper.MediaDeleteHelper
import com.mogree.dubble.service.media.helper.MediaHelper
import com.mogree.dubble.service.media.helper.MediaUploadHelper
import com.mogree.dubble.util.media.config.MediaConfig
import com.mogree.dubble.util.vimeo.VimeoUploadHelper
import com.mogree.server.gen.api.MediaApiDelegate
import com.mogree.server.gen.model.MediaModel
import com.mogree.server.gen.param.*
import com.mogree.spring.exception.APIBadRequestException
import com.mogree.spring.response.DetailResponse
import com.mogree.spring.response.ListResponse
import com.mogree.spring.response.StatusResponse
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Primary
@Service
class MediaService(
    private val mediaHelper: MediaHelper,
    private val mediaUploadHelper: MediaUploadHelper,
    private val mediaDeleteHelper: MediaDeleteHelper,
    private val vimeoUploadHelper: VimeoUploadHelper
) : MediaApiDelegate {

    fun copyMedia(mediaFileName: String): MediaEntity {
        return mediaUploadHelper.copyMedia(mediaFileName)
    }

    fun copyMediaNew(mediaFileName: String, userId: Long): MediaEntity {
        return mediaUploadHelper.copyMediaNew(mediaFileName, userId)
    }

    override fun uploadImage(paramUploadImage: ParamUploadImage?): Any {
        val model = paramUploadImage?.let {
            return mediaUploadHelper.upload(it)
        }
        return DetailResponse(model)
    }

    override fun getMedia(paramPaging: ParamPaging?, paramGetMedia: ParamGetMedia?): Any {
        var list = mediaHelper.getMediaList(paramGetMedia?.modelName, paramGetMedia?.modelId, getCurrentUserId())
        if (paramPaging?.sortColumn != null && paramPaging.sortColumn == "order") {
            if (paramPaging.sortOrder == "desc") {
                list = list.sortedByDescending { it.order }
            } else { //asc and default
                list = list.sortedBy { it.order }
            }
        }
        return ListResponse(list, list.size, paramPaging?.offset, paramPaging?.limit)
    }

    override fun updateMedia(paramUpdateMedia: ParamUpdateMedia?): Any {
        val media = mediaHelper.getMediaByID(paramUpdateMedia?.mediaId)
        media?.let {
            if (it.mediaType == MediaConfig.MediaType.MEDIATYPE_VIDEO && !it.title.isNullOrEmpty()) {//is video
                vimeoUploadHelper.updateTitle(it.title, it.fileName)
            }
            return mediaHelper.updateMedia(it, paramUpdateMedia?.title, paramUpdateMedia?.order, null, null, null)
        }
        throw APIBadRequestException(Config.ResponseMessage.ITEM_NOT_FOUND)
    }


    fun getMedia(modelName: String?, modelId: Int?): List<MediaModel> {
        return mediaHelper.getMediaList(modelName, modelId)
    }

    override fun deleteImage(paramDeleteImage: ParamDeleteImage?): Any {
        paramDeleteImage?.mediaId?.let {
            mediaDeleteHelper.delete(it)
        }
        return StatusResponse(HttpStatus.OK)
    }

}
