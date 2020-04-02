package io.github.chase22.telegram.telegrambotapimock.telegram.data.chat

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatPhoto(
        @field:JsonProperty("small_file_id") private val smallFileId: String,
        @field:JsonProperty("big_file_id") private val bigFileId: String
)