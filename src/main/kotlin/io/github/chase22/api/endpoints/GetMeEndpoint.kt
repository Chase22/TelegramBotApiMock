package io.github.chase22.api.endpoints

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.TelegramBotApiMock
import io.github.chase22.infrastructure.server.TelegramApiEndpoint
import io.undertow.server.HttpServerExchange

import java.io.IOException

class GetMeEndpoint : TelegramApiEndpoint<Nothing>() {
    override val bodyType: Nothing? = null

    override val path: String
        get() = "/getMe"

    override fun process(exchange: HttpServerExchange) {
        try {
            ObjectMapper().writeValue(exchange.outputStream, TelegramBotApiMock.configuration.bot)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
