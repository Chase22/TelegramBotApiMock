package io.github.chase22.telegram.telegrambotapimock.telegram.endpoints

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.chase22.telegram.telegrambotapimock.telegram.data.Update
import io.github.chase22.telegram.telegrambotapimock.telegram.response.TelegramResponse
import io.undertow.server.HttpServerExchange
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import java.time.Duration

class GetUpdates(private val updateChannel: Channel<Update>) : TelegramApiEndpoint<GetUpdateParameters>() {
    override val parameterType: Class<GetUpdateParameters>? = GetUpdateParameters::class.java
    override val path: String = "/getupdates"

    override fun process(exchange: HttpServerExchange) {
        val params = getParamAttachment(exchange)
        val timeoutSeconds = Duration.ofSeconds(params.timeout.toLong())

        GlobalScope.launch {
            val updates: List<Update> = withTimeoutOrNull(timeoutSeconds.toMillis()) {
                updateChannel.receive()
            }?.let { listOf(it) } ?: emptyList()

            TelegramResponse.response(updates)
        }
    }
}

data class GetUpdateParameters(
        @field:JsonProperty("offset") val offset: Int,
        @field:JsonProperty("limit") val limit: Int = 100,
        @field:JsonProperty("timeout") val timeout: Int = 0,
        @field:JsonProperty("allowed_updates") val allowedUpdates: List<String>?
)