package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Document(
        @field:JsonProperty("file_id") val fileId: String,
        @field:JsonProperty("thumb") val thumb: PhotoSize?,
        @field:JsonProperty("file_name") val fileName: String?,
        @field:JsonProperty("mime_type") val mimeType: String?,
        @field:JsonProperty("file_size") val fileSize: Int?
)

data class Video(
        @field:JsonProperty("file_id") val fileId: String,
        @field:JsonProperty("width") val width: Int,
        @field:JsonProperty("height") val height: Int,
        @field:JsonProperty("duration") val duration: Int,
        @field:JsonProperty("thumb") val thumb: PhotoSize?,
        @field:JsonProperty("mime_type") val mimeType: String?,
        @field:JsonProperty("file_size") val fileSize: Int?
)

data class Audio(
        @field:JsonProperty("file_id") val fileId: String,
        @field:JsonProperty("duration") val duration: Int,
        @field:JsonProperty("performer") val performer: String?,
        @field:JsonProperty("title") val title: String?,
        @field:JsonProperty("mime_type") val mimeType: String?,
        @field:JsonProperty("file_size") val fileSize: Int?,
        @field:JsonProperty("thumb") val thumb: PhotoSize?
)

data class Animation(
        @field:JsonProperty("file_id") val fileId: String,
        @field:JsonProperty("width") val width: Int,
        @field:JsonProperty("height") val height: Int,
        @field:JsonProperty("duration") val duration: Int,
        @field:JsonProperty("thumb") val thumb: PhotoSize?,
        @field:JsonProperty("file_name") val fileName: String?,
        @field:JsonProperty("mime_type") val mimeType: String?,
        @field:JsonProperty("file_size") val fileSize: Int?
)

data class Voice(
        @field:JsonProperty("file_id") val fileId: String,
        @field:JsonProperty("duration") val duration: Int,
        @field:JsonProperty("mime_type") val mimeType: String?,
        @field:JsonProperty("file_size") val fileSize: Int?
)

data class VideoNote(
        @field:JsonProperty("file_id") val fileId: String,
        @field:JsonProperty("length") val length: Int,
        @field:JsonProperty("duration") val duration: Int,
        @field:JsonProperty("thumb") val thumb: PhotoSize?,
        @field:JsonProperty("file_size") val fileSize: Int?
)