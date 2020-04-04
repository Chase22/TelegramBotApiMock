package io.github.chase22.telegram.telegrambotapimock.telegram.data.shipping

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.chase22.telegram.telegrambotapimock.telegram.data.User

data class ShippingQuery (
        @field:JsonProperty("id") val id: String,
        @field:JsonProperty("from") val from: User,
        @field:JsonProperty("invoice_payload") val invoicePayload: String,
        @field:JsonProperty("shipping_address") val shippingAddress: ShippingAddress
)
