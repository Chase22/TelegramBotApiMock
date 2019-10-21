package io.github.chase22.infrastructure.server.endpoint

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.api.endpoints.GetMeEndpoint
import io.github.chase22.infrastructure.server.TelegramApiEndpoint
import io.github.chase22.telegram.telegrambotapimock.api.data.User
import org.slf4j.LoggerFactory

import java.util.HashMap

class EndpointRegistry(objectMapper: ObjectMapper, bot: User) {

    private val endpointMap = HashMap<String, TelegramApiEndpoint<*>>()

    init {
        addEndpoint(GetMeEndpoint(bot, objectMapper))
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
