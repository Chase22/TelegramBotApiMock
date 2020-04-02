package io.github.chase22.telegram.telegrambotapimock.telegram.data.shipping

import com.fasterxml.jackson.annotation.JsonProperty

data class ShippingAddress(
        @field:JsonProperty("country_code") val countryCode: String,
        @field:JsonProperty("state") val state: String,
        @field:JsonProperty("city") val city: String,
        @field:JsonProperty("street_line1") val streetLine1: String,
        @field:JsonProperty("street_line2") val streetLine2: String,
        @field:JsonProperty("post_code") val postCode: String
)
