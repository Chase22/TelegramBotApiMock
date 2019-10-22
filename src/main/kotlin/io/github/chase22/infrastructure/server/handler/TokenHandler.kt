package io.github.chase22.infrastructure.server.handler

import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange
import io.undertow.util.StatusCodes
import org.slf4j.LoggerFactory

import io.undertow.util.PathTemplateMatch.ATTACHMENT_KEY as PATH_TEMPLATE_ATTACHMENT_KEY

class TokenHandler(private val token: String, private val next: HttpHandler) : HttpHandler {

    override fun handleRequest(exchange: HttpServerExchange) {
        val id: String? = exchange.getAttachment(PATH_TEMPLATE_ATTACHMENT_KEY)?.parameters?.get("id")

        if (id == null) {
            LOGGER.warn("Passed token was null")
            exchange.statusCode = StatusCodes.NOT_FOUND
            exchange.endExchange()
        } else if (!id.equals(token, ignoreCase = true)) {
            LOGGER.warn("Passed token was incorrect. Expected {}, Got: {}", token, id)
            exchange.statusCode = StatusCodes.UNAUTHORIZED
            exchange.endExchange()
        } else {
            next.handleRequest(exchange)
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(TokenHandler::class.java)
    }
}
