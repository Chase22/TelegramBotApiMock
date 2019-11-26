package io.github.chase22.telegram.telegrambotapimock.telegram.endpoints

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.telegram.telegrambotapimock.telegram.data.User
import io.github.chase22.telegram.telegrambotapimock.telegram.response.TelegramResponse
import io.undertow.server.HttpServerExchange

class GetMe(private val bot: User, private val objectMapper: ObjectMapper) : TelegramApiEndpoint<Unit>() {
    override val parameterType: Nothing? = null

    override val path: String
        get() = "/getMe"

    override fun process(exchange: HttpServerExchange) {
        TelegramResponse.response(bot).sendToExchange(exchange, objectMapper)
        exchange.endExchange()
    }
}
