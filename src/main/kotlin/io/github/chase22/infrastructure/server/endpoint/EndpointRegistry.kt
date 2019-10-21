package io.github.chase22.infrastructure.server.endpoint

import io.github.chase22.api.endpoints.GetMeEndpoint
import io.github.chase22.infrastructure.server.TelegramApiEndpoint
import org.slf4j.LoggerFactory

import java.util.HashMap

class EndpointRegistry {

    private val endpointMap = HashMap<String, TelegramApiEndpoint<*>>()

    init {
        addEndpoint(GetMeEndpoint())
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
