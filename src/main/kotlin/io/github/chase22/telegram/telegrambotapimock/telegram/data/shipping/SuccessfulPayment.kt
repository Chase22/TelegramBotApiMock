package io.github.chase22.telegram.telegrambotapimock.telegram.data.shipping

import com.fasterxml.jackson.annotation.JsonProperty

data class SuccessfulPayment(
        @field:JsonProperty("currency") val currency: String,
        @field:JsonProperty("total_amount") val totalAmount: Int,
        @field:JsonProperty("invoice_payload") val invoicePayload: String,
        @field:JsonProperty("shipping_option_id") val shippingOptionId: String,
        @field:JsonProperty("order_info") val orderInfo: OrderInfo,
        @field:JsonProperty("telegram_payment_charge_id") val telegramPaymentChargeId: String,
        @field:JsonProperty("provider_payment_charge_id") val providerPaymentChargeId: String
)
