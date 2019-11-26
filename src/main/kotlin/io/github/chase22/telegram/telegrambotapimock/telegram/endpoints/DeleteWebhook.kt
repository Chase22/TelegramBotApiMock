package io.github.chase22.telegram.telegrambotapimock.telegram.endpoints

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.telegram.telegrambotapimock.telegram.response.TelegramResponse
import io.undertow.server.HttpServerExchange

class DeleteWebhook(val objectMapper: ObjectMapper) : TelegramApiEndpoint<Void>() {
    override val parameterType: Nothing? = null
    override val path: String = "/deleteWebhook"

    override fun process(exchange: HttpServerExchange) {
        TelegramResponse
                .response(true, "Webhook is already deleted")
                .sendToExchange(exchange, objectMapper)
        exchange.endExchange()
    }

}