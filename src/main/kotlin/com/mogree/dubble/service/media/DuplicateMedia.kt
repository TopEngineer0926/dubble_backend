package com.mogree.dubble.service.media

import com.mogree.dubble.config.security.getCurrentUserId
import com.mogree.dubble.entity.db.MediaEntity
import com.mogree.dubble.storage.repository.MediaRepository
import com.mogree.dubble.util.extension.asJsonString
import org.json.JSONObject
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import springfox.documentation.spring.web.json.Json

@RestController
@RequestMapping("product/media")
class DuplicateMediaController(
        private val mediaRepository: MediaRepository
) {

    /*
 * save the uploaded media in the db
 */
    @PostMapping("/duplicate")
    fun saveMedia(
        @RequestBody file: String
    ): ResponseEntity<MediaEntity> {
        val data = JSONObject(file)
        val entity = MediaEntity()
        entity.foreignId = data.getInt("model_id")
        entity.foreignTable = data.getString("model_name")
        val splitted_file: List<String>  = data.getString("url").split("/")
        entity.fileName = splitted_file[splitted_file.size - 1]
        entity.path = data.getString("url")
        entity.mediaType = data.getInt("media_type")
        entity.userid = getCurrentUserId()

        data.getString("title")?.let { entity.title = it }
        data.getString("status")?.let { entity.status = it }
        print(data.get("order"))
        if (!data.get("order").equals(null))
            entity.order = data.getInt("order")
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mediaRepository.save(entity))
    }

}
