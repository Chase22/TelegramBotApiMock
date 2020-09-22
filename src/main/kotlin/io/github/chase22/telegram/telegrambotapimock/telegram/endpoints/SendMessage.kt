package io.github.chase22.telegram.telegrambotapimock.telegram.endpoints

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.telegram.telegrambotapimock.telegram.data.InlineKeyboardMarkup
import io.undertow.server.HttpServerExchange

class SendMessage(private val objectMapper: ObjectMapper) :
        TelegramApiEndpoint<SendMessage.SendMessageParameters>() {
    override val parameterType: Class<SendMessageParameters> = SendMessageParameters::class.java
    override val path: String = "/sendMessage"

    override fun process(exchange: HttpServerExchange) {

    }

    data class SendMessageParameters(
            @field:JsonProperty("chat_id") val chatId: Int,
            @field:JsonProperty("text") val text: String,
            @field:JsonProperty("parse_mode") val parseMode: String,
            @field:JsonProperty("disable_web_page_preview") val disableWebPagePreview: Boolean,
            @field:JsonProperty("disable_notification") val disableNotification: Boolean,
            @field:JsonProperty("reply_to_message_id") val replyToMessageId: Int,
            @field:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup
    )
}
