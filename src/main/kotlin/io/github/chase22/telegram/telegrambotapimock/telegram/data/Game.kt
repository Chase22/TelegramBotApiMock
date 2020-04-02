package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Game(
        @field:JsonProperty("title") val title: String,
        @field:JsonProperty("description") val description: String,
        @field:JsonProperty("photo") val photo: List<PhotoSize>,
        @field:JsonProperty("text") val text: String?,
        @field:JsonProperty("text_entities") val textEntities: List<MessageEntity>?,
        @field:JsonProperty("animation") val animation: Animation?
)
