package io.github.chase22.telegram.telegrambotapimock.telegram.data.shipping

import com.fasterxml.jackson.annotation.JsonProperty

data class OrderInfo(
        @field:JsonProperty("name") val name: String?,
        @field:JsonProperty("phone_number") val phoneNumber: String?,
        @field:JsonProperty("email") val email: String?,
        @field:JsonProperty("shipping_address") val shippingAddress: ShippingAddress?
)
