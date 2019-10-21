package io.github.chase22.api.endpoints

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.infrastructure.server.TelegramApiEndpoint
import io.github.chase22.telegram.telegrambotapimock.api.data.User
import io.undertow.server.HttpServerExchange
import java.io.IOException
import java.nio.ByteBuffer

class GetMeEndpoint(private val bot: User, private val objectMapper: ObjectMapper) : TelegramApiEndpoint<Nothing>() {
    override val bodyType: Nothing? = null

    override val path: String
        get() = "/getMe"

    override fun process(exchange: HttpServerExchange) {
        try {
            exchange.responseSender.send(ByteBuffer.wrap(objectMapper.writeValueAsBytes(bot)))
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
