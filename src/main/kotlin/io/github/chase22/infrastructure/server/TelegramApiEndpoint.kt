package io.github.chase22.infrastructure.server

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

    protected abstract fun process(exchange: HttpServerExchange)

    override fun handleRequest(exchange: HttpServerExchange) {
        val contentTypeHeader = exchange.requestHeaders.getFirst(Headers.CONTENT_TYPE)

        if (isNull(contentTypeHeader)) {
            exchange.statusCode = BAD_REQUEST
            return
        }

        process(exchange)
    }
}