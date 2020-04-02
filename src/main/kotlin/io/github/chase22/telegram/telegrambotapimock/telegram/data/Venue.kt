package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Venue(
        @field:JsonProperty("location") val location: Location,
        @field:JsonProperty("title") val title: String,
        @field:JsonProperty("address") val address: String,
        @field:JsonProperty("foursquare_id") val foursquareId: String?,
        @field:JsonProperty("foursquare_type") val foursquareType: String?
)
