package io.github.chase22.infrastructure.server

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.config.TelegramBotApiConfiguration
import io.github.chase22.infrastructure.server.endpoint.EndpointRegistry
import io.github.chase22.infrastructure.server.handler.HandlerChain

class DependencyRegistry(port: Int, configuration: TelegramBotApiConfiguration) {
    val objectMapper: ObjectMapper = ObjectMapper()
    val endpointRegistry: EndpointRegistry = EndpointRegistry()
    val apiServer: ApiServer
    val handlerChain: HandlerChain

    init {
        handlerChain = HandlerChain(endpointRegistry, configuration, objectMapper)

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)

        apiServer = ApiServer(port, handlerChain)
    }

}
