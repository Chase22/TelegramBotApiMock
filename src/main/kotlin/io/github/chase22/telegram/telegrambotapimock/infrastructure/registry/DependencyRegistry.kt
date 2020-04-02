package io.github.chase22.telegram.telegrambotapimock.infrastructure.registry

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.github.chase22.telegram.telegrambotapimock.api.config.TelegramBotApiConfiguration
import io.github.chase22.telegram.telegrambotapimock.infrastructure.server.ApiServer
import io.github.chase22.telegram.telegrambotapimock.infrastructure.server.handler.HandlerChain
import io.github.chase22.telegram.telegrambotapimock.telegram.data.Update
import kotlinx.coroutines.channels.Channel

class DependencyRegistry(port: Int, configuration: TelegramBotApiConfiguration) {
    val objectMapper: ObjectMapper = ObjectMapper().apply {
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        this.registerModule(KotlinModule())
    }

    val updateChannel: Channel<Update> = Channel(Channel.UNLIMITED)

    val endpointRegistry: EndpointRegistry = EndpointRegistry(objectMapper, configuration.bot, updateChannel)

    val handlerChain: HandlerChain = HandlerChain(endpointRegistry, configuration, objectMapper)

    val apiServer: ApiServer = ApiServer(port, handlerChain)

}
