package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Invoice(
        @field:JsonProperty("title") val title: String,
        @field:JsonProperty("description") val description: String,
        @field:JsonProperty("start_parameter") val startParameter: String,
        @field:JsonProperty("currency") val currency: String,
        @field:JsonProperty("total_amount") val totalAmount: Integer
)
