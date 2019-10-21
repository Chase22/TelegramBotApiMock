package io.github.chase22.infrastructure.server.handler

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.infrastructure.server.handler.Router.Companion.ENDPOINT_ATTACHMENT_KEY
import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange

class ParameterHandler(private val objectMapper: ObjectMapper) : HttpHandler {

    override fun handleRequest(exchange: HttpServerExchange) {
        val endpoint = exchange.getAttachment(ENDPOINT_ATTACHMENT_KEY)

        endpoint.setParamAttachment(exchange, objectMapper)
        endpoint.handleRequest(exchange)
    }
}
