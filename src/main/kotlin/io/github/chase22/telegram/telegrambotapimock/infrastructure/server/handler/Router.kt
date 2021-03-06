package io.github.chase22.telegram.telegrambotapimock.infrastructure.server.handler

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.telegram.telegrambotapimock.infrastructure.registry.EndpointRegistry
import io.github.chase22.telegram.telegrambotapimock.telegram.endpoints.TelegramApiEndpoint
import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange
import io.undertow.util.AttachmentKey
import io.undertow.util.StatusCodes.NOT_FOUND
import org.slf4j.LoggerFactory

class Router(private val next: HttpHandler,
             private val endpointRegistry: EndpointRegistry,
             private val objectMapper: ObjectMapper) : HttpHandler {

    @Throws(Exception::class)
    override fun handleRequest(exchange: HttpServerExchange) {
        val (botId, path) = exchange.requestPath.substring(1).split(delimiters = *charArrayOf('/'), limit = 2)

        exchange.putAttachment(BOT_ID_ATTACHMENT_KEY, botId)

        LOGGER.debug("Get endpoint for path {}", path)
        val endpoint = endpointRegistry.getForPath("/$path")

        if (endpoint != null) {
            LOGGER.debug("Found endpoint {} for path {}", endpoint::class.java.name, path)
            exchange.putAttachment(ENDPOINT_ATTACHMENT_KEY, endpoint)
            next.handleRequest(exchange)
        } else {
            LOGGER.warn("No Endpoint for path {}", "/$path")
            exchange.statusCode = NOT_FOUND
            exchange.endExchange()
        }
    }

    companion object {
        val ENDPOINT_ATTACHMENT_KEY: AttachmentKey<TelegramApiEndpoint<*>> = AttachmentKey.create(TelegramApiEndpoint::class.java)
        val BOT_ID_ATTACHMENT_KEY: AttachmentKey<String> = AttachmentKey.create(String::class.java)

        private val LOGGER = LoggerFactory.getLogger(Router::class.java)
    }
}
