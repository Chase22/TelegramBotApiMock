package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty

data class PhotoSize(
        @field:JsonProperty("file_id") private val fileId: String,
        @field:JsonProperty("width") private val width: Int,
        @field:JsonProperty("height") private val height: Int,
        @field:JsonProperty("file_size") private val fileSize: Int?
)