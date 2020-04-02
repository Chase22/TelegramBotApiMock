package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Location(
        @field:JsonProperty("longitude") val longitude: Float,
        @field:JsonProperty("latitude") val latitude: Float
)
