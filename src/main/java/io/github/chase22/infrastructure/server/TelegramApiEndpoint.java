package io.github.chase22.infrastructure.server;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.AttachmentKey;
import io.undertow.util.Headers;

import static io.undertow.util.StatusCodes.BAD_REQUEST;
import static java.util.Objects.isNull;

public abstract class TelegramApiEndpoint<T> implements HttpHandler {

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

        process(exchange);
    }
}
