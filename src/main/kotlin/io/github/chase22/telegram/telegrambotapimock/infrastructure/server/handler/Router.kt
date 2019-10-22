package io.github.chase22.telegram.telegrambotapimock.infrastructure.server.handler

import io.github.chase22.telegram.telegrambotapimock.telegram.endpoints.TelegramApiEndpoint
import io.github.chase22.telegram.telegrambotapimock.infrastructure.registry.EndpointRegistry
import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange
import io.undertow.util.AttachmentKey
import io.undertow.util.StatusCodes.NOT_FOUND
import org.slf4j.LoggerFactory
import java.util.Objects.nonNull

class Router(private val next: HttpHandler, private val endpointRegistry: EndpointRegistry) : HttpHandler {

    @Throws(Exception::class)
    override fun handleRequest(exchange: HttpServerExchange) {
        val (botId, path) = exchange.requestPath.substring(1).split(delimiters = *charArrayOf('/'), limit = 2)

        exchange.putAttachment(BOT_ID_ATTACHMENT_KEY, botId)

        val endpoint = endpointRegistry.getForPath("/$path")

        if (nonNull(endpoint)) {
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
