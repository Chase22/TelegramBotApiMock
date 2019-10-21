package io.github.chase22.infrastructure.server.handler

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.infrastructure.server.TelegramApiEndpoint
import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange

import io.github.chase22.infrastructure.server.Router.ENDPOINT_ATTACHMENT_KEY
import java.util.Objects.nonNull

class ParameterHandler(private val objectMapper: ObjectMapper) : HttpHandler {

    override fun handleRequest(exchange: HttpServerExchange) {
        val endpoint = exchange.getAttachment(ENDPOINT_ATTACHMENT_KEY)

        if (nonNull(endpoint.bodyType)) {
            val parameter = objectMapper.readValue(exchange.inputStream, endpoint.bodyType)
            exchange.putAttachment(endpoint.attachmentKey, parameter)
        }

        endpoint.handleRequest(exchange)
    }
}
