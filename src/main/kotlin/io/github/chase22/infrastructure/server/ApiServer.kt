package io.github.chase22.infrastructure.server

import io.github.chase22.infrastructure.server.handler.HandlerChain
import io.undertow.Undertow

class ApiServer internal constructor(port: Int, handlerChain: HandlerChain) {

    private val server: Undertow = Undertow
            .builder()
            .addHttpListener(port, "localhost")
            .setHandler(handlerChain.handler)
            .build()

    fun start() {
        server.start()
    }

    fun stop() {
        server.stop()
    }
}
