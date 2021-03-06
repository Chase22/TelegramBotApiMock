package io.github.chase22.telegram.telegrambotapimock.infrastructure.server.handler

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.telegram.telegrambotapimock.infrastructure.server.handler.Router.Companion.ENDPOINT_ATTACHMENT_KEY
import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange

class ParameterHandler(private val objectMapper: ObjectMapper) : HttpHandler {

    override fun handleRequest(exchange: HttpServerExchange) {
        if (exchange.isInIoThread) {
            exchange.dispatch(this);
            return;
        }
        exchange.requestReceiver.receiveFullString { exchange: HttpServerExchange, message: String ->
            val endpoint = exchange.getAttachment(ENDPOINT_ATTACHMENT_KEY)

            endpoint.setParamAttachment(exchange, message, objectMapper)
            endpoint.handleRequest(exchange)
        }

    }
}
