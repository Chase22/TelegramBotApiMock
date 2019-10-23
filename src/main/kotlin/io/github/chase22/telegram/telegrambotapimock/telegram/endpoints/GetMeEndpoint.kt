package io.github.chase22.telegram.telegrambotapimock.telegram.endpoints

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.telegram.telegrambotapimock.infrastructure.server.HttpExchangeWrapper
import io.github.chase22.telegram.telegrambotapimock.telegram.data.User
import java.io.IOException
import java.nio.ByteBuffer

class GetMeEndpoint(private val bot: User, private val objectMapper: ObjectMapper) : TelegramApiEndpoint<Nothing>() {
    override val bodyType: Nothing? = null

    override val path: String
        get() = "/getMe"

    override fun process(exchange: HttpExchangeWrapper) {
        try {
            exchange.send(objectMapper.writeValueAsBytes(bot))
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
