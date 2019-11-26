package io.github.chase22.telegram.telegrambotapimock.telegram.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import io.undertow.server.HttpServerExchange
import io.undertow.util.StatusCodes

data class TelegramResponse<Body>(
        @JsonProperty("ok") val ok: Boolean,
        @JsonProperty("description") val description: String? = null,
        @JsonProperty("result") val result: Body? = null,
        @JsonProperty("error_code") val errorCode: Int? = null,
        @JsonProperty("parameters") val parameters: ResponseParameters? = null
) {
    fun sendToExchange(exchange: HttpServerExchange, objectMapper: ObjectMapper) = exchange
            .responseSender
            .send(objectMapper.writeValueAsString(this))

    companion object {
        fun errorResponse(description: String, errorCode: Int) = TelegramResponse<Unit>(
                ok = false,
                description = description,
                errorCode = errorCode
        )

        fun errorResponse(statusCode: Int) = errorResponse(StatusCodes.getReason(statusCode), statusCode)

        fun <Body> response(body: Body) = TelegramResponse<Body>(ok = true, result = body)

        fun <Body> response(body: Body, description: String) =
                TelegramResponse<Body>(ok = true, description = description, result = body)

    }
}

data class ResponseParameters(
        @JsonProperty("migrate_to_chat_id") private val migrateToChatId: Long?,
        @JsonProperty("retry_after") private val retryAfter: Int?

)