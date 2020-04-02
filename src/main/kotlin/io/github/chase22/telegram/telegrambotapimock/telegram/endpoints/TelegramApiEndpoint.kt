package io.github.chase22.telegram.telegrambotapimock.telegram.endpoints

import com.fasterxml.jackson.databind.ObjectMapper
import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange
import io.undertow.util.AttachmentKey
import io.undertow.util.Headers

import io.undertow.util.StatusCodes.BAD_REQUEST
import java.util.Objects.isNull

abstract class TelegramApiEndpoint<Parameter> : HttpHandler {

    abstract val parameterType: Class<Parameter>?
    abstract val path: String

    private val attachmentKey: AttachmentKey<Parameter>
        get() = AttachmentKey.create(parameterType)

    protected abstract fun process(exchange: HttpServerExchange)

    override fun handleRequest(exchange: HttpServerExchange) {
        val contentTypeHeader = exchange.requestHeaders.getFirst(Headers.CONTENT_TYPE)

        if (isNull(contentTypeHeader)) {
            exchange.statusCode = BAD_REQUEST
            return
        }

        process(exchange)
    }

    fun setParamAttachment(exchange: HttpServerExchange, objectMapper: ObjectMapper) {
        parameterType?.let {
            try {
                objectMapper.readValue<Parameter>(exchange.inputStream, parameterType).let {
                    exchange.putAttachment<Parameter>(attachmentKey, it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getParamAttachment(exchange: HttpServerExchange): Parameter {
        return exchange.getAttachment(attachmentKey)
    }
}
