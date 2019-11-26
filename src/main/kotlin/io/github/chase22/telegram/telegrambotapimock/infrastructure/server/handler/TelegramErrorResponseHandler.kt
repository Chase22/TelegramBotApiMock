package io.github.chase22.telegram.telegrambotapimock.infrastructure.server.handler

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.telegram.telegrambotapimock.telegram.response.TelegramResponse
import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange
import io.undertow.util.AttachmentKey

class TelegramErrorResponseHandler(private val next: HttpHandler, private val objectMapper: ObjectMapper): HttpHandler {
    override fun handleRequest(exchange: HttpServerExchange?) {
        exchange?.addDefaultResponseListener {
            if (!exchange.isResponseChannelAvailable) {
                return@addDefaultResponseListener false
            }
            if (exchange.statusCode != 200) {
                exchange.getAttachment(ATTACHMENT_KEY_ERROR_DESCRIPTION)?.let {
                    TelegramResponse.errorResponse(it, exchange.statusCode).sendToExchange(exchange, objectMapper)
                    return@addDefaultResponseListener true
                }

                TelegramResponse.errorResponse(exchange.statusCode).sendToExchange(exchange, objectMapper)
                return@addDefaultResponseListener true
            }
            return@addDefaultResponseListener false
        }
        next.handleRequest(exchange)
    }

    companion object {
        val ATTACHMENT_KEY_ERROR_DESCRIPTION: AttachmentKey<String> = AttachmentKey.create(String::class.java)
    }
}