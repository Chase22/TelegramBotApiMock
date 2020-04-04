package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty

data class ChosenInlineResult(
        @field:JsonProperty("result_id") val resultId: String,
        @field:JsonProperty("from") val from: User,
        @field:JsonProperty("location") val location: Location?,
        @field:JsonProperty("inline_message_id") val inlineMessageId: String?,
        @field:JsonProperty("query") val query: String
)
