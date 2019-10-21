package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.TelegramApiEndpoint;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import static io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.Router.ENDPOINT_ATTACHMENT_KEY;
import static java.util.Objects.nonNull;

public class ParameterHandler implements HttpHandler {


    private final ObjectMapper objectMapper;

    public ParameterHandler(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {
        final TelegramApiEndpoint endpoint = exchange.getAttachment(ENDPOINT_ATTACHMENT_KEY);

        if (nonNull(endpoint.getBodyType())) {
            Object parameter = objectMapper.readValue(exchange.getInputStream(), endpoint.getBodyType());
            exchange.putAttachment(endpoint.getAttachmentKey(), parameter);
        }

        endpoint.handleRequest(exchange);
    }
}
