package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty

data class CallbackQuery(
        @field:JsonProperty("id") val id: String,
        @field:JsonProperty("from") val from: User,
        @field:JsonProperty("message") val message: Message?,
        @field:JsonProperty("inline_message_id") val inlineMessageId: String?,
        @field:JsonProperty("chat_instance") val chatInstance: String,
        @field:JsonProperty("data") val data: String?,
        @field:JsonProperty("game_short_name") val gameShortName: String?
)
