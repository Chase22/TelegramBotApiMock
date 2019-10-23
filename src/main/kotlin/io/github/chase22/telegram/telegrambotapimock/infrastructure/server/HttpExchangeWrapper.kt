package io.github.chase22.telegram.telegrambotapimock.infrastructure.server

import io.undertow.server.HttpServerExchange
import java.nio.ByteBuffer

class HttpExchangeWrapper(private val exchange: HttpServerExchange) {

    fun send(buffer: ByteBuffer) {
        exchange.responseSender.send(buffer)
    }

    fun send(buffer: ByteArray) {
        send(ByteBuffer.wrap(buffer))
    }


}