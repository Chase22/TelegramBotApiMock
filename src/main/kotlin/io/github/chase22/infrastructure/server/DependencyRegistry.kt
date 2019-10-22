package io.github.chase22.infrastructure.server

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.config.TelegramBotApiConfiguration
import io.github.chase22.infrastructure.server.endpoint.EndpointRegistry
import io.github.chase22.infrastructure.server.handler.HandlerChain

class DependencyRegistry(port: Int, configuration: TelegramBotApiConfiguration) {
    val objectMapper: ObjectMapper = ObjectMapper().apply {
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }

    val endpointRegistry: EndpointRegistry = EndpointRegistry(objectMapper, configuration.bot)

    val handlerChain: HandlerChain = HandlerChain(endpointRegistry, configuration, objectMapper)

    val apiServer: ApiServer = ApiServer(port, handlerChain)

}