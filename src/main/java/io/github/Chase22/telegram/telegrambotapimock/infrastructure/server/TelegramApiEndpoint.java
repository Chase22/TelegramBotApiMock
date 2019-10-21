package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server;

import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper.NoContentTypeMapperException;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.AttachmentKey;
import io.undertow.util.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.undertow.util.StatusCodes.BAD_REQUEST;
import static io.undertow.util.StatusCodes.INTERNAL_SERVER_ERROR;
import static java.util.Objects.isNull;

public abstract class TelegramApiEndpoint<T> implements HttpHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramApiEndpoint.class);

    public abstract Class<T> getBodyType();
    public abstract String getPath();

    protected abstract void process(HttpServerExchange response);

    public AttachmentKey<T> getAttachmentKey() {
        return AttachmentKey.create(getBodyType());
    }

    @Override
    public void handleRequest(final HttpServerExchange exchange) {
        final String contentTypeHeader = exchange.getRequestHeaders().getFirst(Headers.CONTENT_TYPE);
        if (isNull(contentTypeHeader)) {
            exchange.setStatusCode(BAD_REQUEST);
            return;
        }

        try {
            process(exchange);
        } catch (NoContentTypeMapperException e) {
            LOGGER.error("No Content Type mapper for type " + contentTypeHeader, e);
            exchange.setStatusCode(INTERNAL_SERVER_ERROR);
        }
    }
}
