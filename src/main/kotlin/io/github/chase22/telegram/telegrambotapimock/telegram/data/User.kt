package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty

class User(
        @field:JsonProperty("id") val id: Long,
        @field:JsonProperty("is_bot") val bot: Boolean,
        @field:JsonProperty("first_name") val firstName: String,
        @field:JsonProperty("last_name") val lastName: String?,
        @field:JsonProperty("username") val username: String?,
        @field:JsonProperty("language_code") val languageCode: String
)
