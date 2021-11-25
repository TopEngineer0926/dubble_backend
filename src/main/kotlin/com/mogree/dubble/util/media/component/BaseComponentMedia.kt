package com.mogree.dubble.util.media.component

import com.mogree.dubble.config.Config
import com.mogree.dubble.util.media.data.MediaData
import com.mogree.spring.exception.APIBadRequestException
import com.mogree.spring.exception.APIInternalServerException
import com.mogree.spring.exception.ExLogger
import org.apache.tika.Tika
import org.apache.tika.io.IOUtils
import org.apache.tika.mime.MimeTypes
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.*
import java.math.BigInteger.valueOf
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.time.OffsetDateTime

/**
 * abstract (helper) base class for the upload logic
 */
@Component
abstract class BaseComponentMedia : MediaComponent {

    /**
     * the basic upload logic, can be overwritten by the impl
     */
    override fun upload(file: MultipartFile, title: String?): MediaData {
        val newFileName = generateUniqueFileName((getByteArray(file)))

        if (!validate(file)) {
            throw APIBadRequestException(Config.ErrorMessagesGerman.INVALID_FILE)
        }

        try {
            val path = getPath() + "/" + newFileName

            val mediaFile = File(path)
            if (!mediaFile.createNewFile()) {
                throw APIInternalServerException(Config.ErrorMessagesGerman.CREATE_FILE(newFileName))
            }
            FileOutputStream(mediaFile).use { stream ->
                stream.write(file.bytes)
                stream.flush()
            }

            return MediaData(newFileName, path, getMediaType())
        } catch (e: IOException) {
            throw APIInternalServerException(Config.ErrorMessagesGerman.WRITE_FILE(newFileName))
        }
    }

    /**
     * the basic upload logic, can be overwritten by the impl
     */
    override fun copy(fileName: String): MediaData {

        val fileExtension = fileName.split('.')[fileName.split('.').size - 1]

        val filePath = getPath() + "/" + fileName

        val newFileName = generateUniqueFileNameNew(fileExtension)

        try {
            val path = getPath() + "/" + newFileName

//            val mediaFile = File(path)
//            if (!mediaFile.createNewFile()) {
//                throw APIInternalServerException(Config.ErrorMessagesGerman.CREATE_FILE(newFileName))
//            }
//
//            Files.copy(Paths.get(filePath), Paths.get(path), StandardCopyOption.REPLACE_EXISTING)

//            return MediaData(newFileName, path, getMediaType())
            return MediaData(fileName, filePath, getMediaType())
        } catch (e: IOException) {
            throw APIInternalServerException(Config.ErrorMessagesGerman.WRITE_FILE(newFileName))
        }
    }


    /**
     * static helper methods
     */
    companion object {

        /**
         * get the file extension from filename
         */
        private fun getFileExtension(fileBytes: ByteArray): String {
            val mimeType = Tika().detect(ByteArrayInputStream(fileBytes)).toLowerCase()
            val mimeTypeExtension = MimeTypes.getDefaultMimeTypes().forName(mimeType)
            if (mimeTypeExtension.extensions.contains(Config.FileExtension.QT) || mimeTypeExtension.extensions.contains(
                    Config.FileExtension.GP
                )
            ) {
                return Config.FileExtension.MP4
            }
            return mimeTypeExtension.extension
        }

        /**
         * returns a unique filename with milli seconds
         */
        fun generateUniqueFileName(fileBytes: ByteArray): String {
            var newFileName = ""
            newFileName += valueOf(OffsetDateTime.now().toInstant().toEpochMilli())
            newFileName += getFileExtension(fileBytes)
            return newFileName
        }

        /**
         * returns a unique filename with milli seconds
         */
        fun generateUniqueFileNameNew(fileExtension: String): String {
            var newFileName = ""
            newFileName += valueOf(OffsetDateTime.now().toInstant().toEpochMilli())
            newFileName += '.' + fileExtension
            return newFileName
        }

        /**
         * get the mime type from the file
         */
        fun getMimeType(fileBytes: ByteArray): String {
            try {
                val mediaType = Tika().detect(ByteArrayInputStream(fileBytes)).toLowerCase()
                return mediaType
            } catch (ex: Exception) {
                throw APIBadRequestException(Config.ErrorMessagesGerman.PARSE_EXTENSION, ExLogger.LogLevel.FULL)
            }
        }

        /**
         * multipart to bytearray
         */
        fun getByteArray(file: MultipartFile?): ByteArray {
            return try {
                val baos = ByteArrayOutputStream()
                IOUtils.copy(file?.inputStream, baos)
                baos.toByteArray()
            } catch (e: Exception) {
                throw APIBadRequestException(Config.ErrorMessagesGerman.READ_FILE)
            }
        }
    }
}