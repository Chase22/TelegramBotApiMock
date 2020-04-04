package io.github.chase22.telegram.telegrambotapimock.telegram.data.shipping

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.chase22.telegram.telegrambotapimock.telegram.data.User

data class PreCheckoutQuery (
        @field:JsonProperty("id") val id: String,
        @field:JsonProperty("from") val from: User,
        @field:JsonProperty("currency") val currency: String,
        @field:JsonProperty("total_amount") val totalAmount: Int,
        @field:JsonProperty("invoice_payload") val invoicePayload: String,
        @field:JsonProperty("shipping_option_id") val shippingOptionId: String?,
        @field:JsonProperty("order_info") val orderInfo: OrderInfo?
)
