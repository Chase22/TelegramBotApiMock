package io.github.chase22.telegram.telegrambotapimock.infrastructure.server.handler

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.telegram.telegrambotapimock.telegram.response.TelegramResponse
import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange
import io.undertow.util.StatusCodes
import io.undertow.util.StatusCodes.NOT_FOUND
import org.slf4j.LoggerFactory

import io.undertow.util.PathTemplateMatch.ATTACHMENT_KEY as PATH_TEMPLATE_ATTACHMENT_KEY

class TokenHandler(private val token: String,
                   private val next: HttpHandler,
                   private val objectMapper: ObjectMapper) : HttpHandler {

    override fun handleRequest(exchange: HttpServerExchange) {
        val token: String? = exchange.getAttachment(PATH_TEMPLATE_ATTACHMENT_KEY)
                ?.parameters
                ?.get("id")
                ?.removePrefix("bot")

        if (token == null) {
            LOGGER.warn("Passed token was null")
            exchange.statusCode = NOT_FOUND
            TelegramResponse.errorResponse(NOT_FOUND).sendToExchange(exchange, objectMapper)
            exchange.endExchange()
        } else if (!token.equals(this.token, ignoreCase = true)) {
            LOGGER.warn("Passed token was incorrect. Expected {}, Got: {}", this.token, token)
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
