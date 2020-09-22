package io.github.chase22.telegram.telegrambotapimock.infrastructure.server.handler

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.telegram.telegrambotapimock.api.config.TelegramBotApiConfiguration
import io.github.chase22.telegram.telegrambotapimock.infrastructure.registry.EndpointRegistry
import io.undertow.server.HttpHandler
import io.undertow.server.handlers.GracefulShutdownHandler
import io.undertow.server.handlers.PathTemplateHandler
import io.undertow.server.handlers.RequestDumpingHandler

class HandlerChain(endpointRegistry: EndpointRegistry,
                   config: TelegramBotApiConfiguration,
                   objectMapper: ObjectMapper
) {
    val handler: HttpHandler

    init {
        val parameterHandler = ParameterHandler(objectMapper)

        val shutdownHandler = GracefulShutdownHandler(parameterHandler)

        val router = Router(shutdownHandler, endpointRegistry, objectMapper)

        val tokenHandler = TokenHandler(config.botToken, router)

        val pathTemplateHandler = PathTemplateHandler()
        pathTemplateHandler.add("/{id}/*", tokenHandler)

        val telegramErrorResponseHandler = TelegramErrorResponseHandler(pathTemplateHandler, objectMapper)

        handler = if (config.dumpRequests) {
            val requestDumpingHandler = RequestDumpingHandler(telegramErrorResponseHandler)
            requestDumpingHandler
        } else {
            telegramErrorResponseHandler
        }

    }
}
