package io.github.chase22.telegram.telegrambotapimock.telegram.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import io.undertow.server.HttpServerExchange
import io.undertow.util.StatusCodes

data class TelegramResponse(
        @JsonProperty("ok") private val ok: Boolean,
        @JsonProperty("description") private val description: String? = null,
        @JsonProperty("result") private val result: Any? = null,
        @JsonProperty("error_code") private val errorCode: Int? = null,
        @JsonProperty("parameters") private val parameters: ResponseParameters? = null
) {
    fun sendToExchange(exchange: HttpServerExchange, objectMapper: ObjectMapper) = exchange
            .responseSender
            .send(objectMapper.writeValueAsString(this))

    companion object {
        fun errorResponse(description: String, errorCode: Int) = TelegramResponse(
                ok = false,
                description = description,
                errorCode = errorCode
        )

        fun errorResponse(statusCode: Int) = errorResponse(StatusCodes.getReason(statusCode), statusCode)

        fun response(body: Any) = TelegramResponse(ok = true, result = body)
    }
}

data class ResponseParameters(
        @JsonProperty("migrate_to_chat_id") private val migrateToChatId: Long?,
        @JsonProperty("retry_after") private val retryAfter: Int?

)