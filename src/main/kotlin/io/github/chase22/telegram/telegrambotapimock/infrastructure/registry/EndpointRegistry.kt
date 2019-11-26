package io.github.chase22.telegram.telegrambotapimock.infrastructure.registry

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.telegram.telegrambotapimock.telegram.endpoints.GetMe
import io.github.chase22.telegram.telegrambotapimock.telegram.endpoints.TelegramApiEndpoint
import io.github.chase22.telegram.telegrambotapimock.telegram.data.User
import io.github.chase22.telegram.telegrambotapimock.telegram.endpoints.DeleteWebhook
import org.slf4j.LoggerFactory

import java.util.HashMap

class EndpointRegistry(objectMapper: ObjectMapper, bot: User) {

    private val endpointMap = HashMap<String, TelegramApiEndpoint<*>>()

    init {
        addEndpoint(GetMe(bot, objectMapper))
        addEndpoint(DeleteWebhook(objectMapper))
    }

    fun getForPath(path: String): TelegramApiEndpoint<*>? = endpointMap[path]

    private fun addEndpoint(servlet: TelegramApiEndpoint<*>) {
        LOGGER.info("Registering servlet " + servlet.javaClass.name + " with mapping " + servlet.path)
        endpointMap[servlet.path] = servlet
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(EndpointRegistry::class.java)
    }
}
