package io.github.chase22.telegram.telegrambotapimock.infrastructure.registry

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.github.chase22.telegram.telegrambotapimock.api.config.TelegramBotApiConfiguration
import io.github.chase22.telegram.telegrambotapimock.infrastructure.LocalDateTimeToTimestampSerializerModule
import io.github.chase22.telegram.telegrambotapimock.infrastructure.server.ApiServer
import io.github.chase22.telegram.telegrambotapimock.infrastructure.server.handler.HandlerChain
import io.github.chase22.telegram.telegrambotapimock.telegram.data.Update
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

class DependencyRegistry(port: Int, configuration: TelegramBotApiConfiguration) {
    val objectMapper: ObjectMapper = ObjectMapper().apply {
        this.registerModule(KotlinModule())
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
        this.registerModule(LocalDateTimeToTimestampSerializerModule())
    }

    val updateChannel: BlockingQueue<Update> = ArrayBlockingQueue(100)

    val endpointRegistry: EndpointRegistry = EndpointRegistry(objectMapper, configuration.bot, updateChannel)

    val handlerChain: HandlerChain = HandlerChain(endpointRegistry, configuration, objectMapper)

    val apiServer: ApiServer = ApiServer(port, handlerChain)

}
