package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Contact(
        @field:JsonProperty("phone_number") val phoneNumber: String,
        @field:JsonProperty("first_name") val firstName: String,
        @field:JsonProperty("last_name") val lastName: String?,
        @field:JsonProperty("user_id") val userId: Int?,
        @field:JsonProperty("vcard") val vcard: String?
)
