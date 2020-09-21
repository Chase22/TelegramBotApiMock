package io.github.chase22.telegram.telegrambotapimock.telegram.endpoints

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.telegram.telegrambotapimock.telegram.data.Update
import io.github.chase22.telegram.telegrambotapimock.telegram.response.TelegramResponse
import io.undertow.server.HttpServerExchange
import org.slf4j.LoggerFactory
import java.util.concurrent.BlockingQueue
import java.util.concurrent.TimeUnit

class GetUpdates(private val updateChannel: BlockingQueue<Update>, private val objectMapper: ObjectMapper) : TelegramApiEndpoint<GetUpdateParameters>() {
    override val parameterType: Class<GetUpdateParameters> = GetUpdateParameters::class.java
    override val path: String = "/getupdates"

    override fun process(exchange: HttpServerExchange) {
        exchange.dispatch( Runnable {
            val params = getParamAttachment(exchange)
            val updates: List<Update> = updateChannel.poll(params.timeout.toLong(), TimeUnit.SECONDS)?.let { listOf(it) } ?: emptyList()
            TelegramResponse.response(updates).sendToExchange(exchange, objectMapper)
            exchange.endExchange()
        })
    }

    companion object {
        val LOGGER = LoggerFactory.getLogger(GetUpdates::class.java)
    }
}

data class GetUpdateParameters(
        @field:JsonProperty("offset") val offset: Int,
        @field:JsonProperty("limit") val limit: Int = 100,
        @field:JsonProperty("timeout") val timeout: Int = 0,
        @field:JsonProperty("allowed_updates") val allowedUpdates: List<String>?
)
