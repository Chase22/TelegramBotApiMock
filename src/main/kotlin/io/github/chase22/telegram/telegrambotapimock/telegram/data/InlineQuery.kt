package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty

data class InlineQuery(
        @field:JsonProperty("id") val id: String,
        @field:JsonProperty("from") val from: User,
        @field:JsonProperty("location") val location: Location?,
        @field:JsonProperty("query") val query: String,
        @field:JsonProperty("offset") val offset: String
)
