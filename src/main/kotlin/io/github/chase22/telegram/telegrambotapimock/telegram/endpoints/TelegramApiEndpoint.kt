package io.github.chase22.telegram.telegrambotapimock.telegram.endpoints

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.telegram.telegrambotapimock.infrastructure.server.HttpExchangeWrapper
import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange
import io.undertow.util.AttachmentKey
import io.undertow.util.Headers

import io.undertow.util.StatusCodes.BAD_REQUEST
import java.util.Objects.isNull

abstract class TelegramApiEndpoint<T> : HttpHandler {

    abstract val bodyType: Class<T>?
    abstract val path: String

    val attachmentKey: AttachmentKey<T>
        get() = AttachmentKey.create(bodyType)

    protected abstract fun process(exchange: HttpExchangeWrapper)

    override fun handleRequest(exchange: HttpServerExchange) {
        val contentTypeHeader = exchange.requestHeaders.getFirst(Headers.CONTENT_TYPE)

        if (isNull(contentTypeHeader)) {
            exchange.statusCode = BAD_REQUEST
            return
        }

        process(HttpExchangeWrapper(exchange))
    }

    fun setParamAttachment(exchange: HttpServerExchange, objectMapper: ObjectMapper) {
        bodyType?.let {
            objectMapper.readValue<T>(exchange.inputStream, bodyType).let {
                exchange.putAttachment<T>(attachmentKey, it)
            }
        }
    }

    fun getParamAttachment(exchange: HttpServerExchange): T {
        return exchange.getAttachment(attachmentKey)
    }
}
