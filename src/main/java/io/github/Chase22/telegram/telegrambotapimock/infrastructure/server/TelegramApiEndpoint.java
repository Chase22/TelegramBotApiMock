package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server;

import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.endpoint.Endpoint;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper.ContentTypeMapper;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper.ContentTypeMapperContext;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper.NoContentTypeMapperException;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static io.undertow.util.StatusCodes.BAD_REQUEST;
import static io.undertow.util.StatusCodes.INTERNAL_SERVER_ERROR;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public abstract class TelegramApiEndpoint<T> implements Endpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramApiEndpoint.class);

    protected abstract void process(T body, HttpServerExchange response);

    protected abstract Class<T> getBodyType();

    @Override
    public void process(final HttpServerExchange exchange) {
        ContentTypeMapperContext mapperContext = ContentTypeMapperContext.getInstance();

        final String contentTypeHeader = exchange.getRequestHeaders().getFirst(Headers.CONTENT_TYPE);
        if (isNull(contentTypeHeader)) {
            exchange.setStatusCode(BAD_REQUEST);
            return;
        }

        try {
            T body = null;
            if (nonNull(getBodyType())) {
                final ContentTypeMapper contentMapper = mapperContext.getMapperForType(contentTypeHeader);
                body = contentMapper.mapToObject(exchange, getBodyType());
            }

            process(body, exchange);
        } catch (IOException e) {
            LOGGER.error("Error processing request", e);
            exchange.setStatusCode(INTERNAL_SERVER_ERROR);
        } catch (NoContentTypeMapperException e) {
            LOGGER.error("No Content Type mapper for type " + contentTypeHeader, e);
            exchange.setStatusCode(INTERNAL_SERVER_ERROR);
        }
    }
}
