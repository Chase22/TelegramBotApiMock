package io.github.chase22.telegram.telegrambotapimock.infrastructure.server.handler

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.telegram.telegrambotapimock.api.config.TelegramBotApiConfiguration
import io.github.chase22.telegram.telegrambotapimock.infrastructure.registry.EndpointRegistry
import io.undertow.server.HttpHandler
import io.undertow.server.handlers.BlockingHandler
import io.undertow.server.handlers.PathTemplateHandler

class HandlerChain(endpointRegistry: EndpointRegistry,
                   config: TelegramBotApiConfiguration,
                   objectMapper: ObjectMapper
) {
    val handler: HttpHandler

    init {
        val parameterHandler = ParameterHandler(objectMapper)

        val router = Router(parameterHandler, endpointRegistry)
        val blockingHandler = BlockingHandler(router)

        val tokenHandler = TokenHandler(config.botToken, blockingHandler)

        val pathTemplateHandler = PathTemplateHandler()
        pathTemplateHandler.add("/{id}/*", tokenHandler)

        handler = pathTemplateHandler
    }
}