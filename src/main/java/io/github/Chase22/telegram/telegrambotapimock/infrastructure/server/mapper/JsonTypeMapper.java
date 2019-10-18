package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper;

import io.undertow.server.HttpServerExchange;

import java.io.IOException;

public class JsonTypeMapper implements ContentTypeMapper {
    @Override
    public String getSupportedContentType() {
        return "application/json";
    }

    @Override
    public <T> T mapToObject(final HttpServerExchange exchange, final Class<T> target) throws IOException {
        return ContentTypeMapperContext.getInstance().getObjectMapper().readValue(exchange.getInputStream(), target);
    }
}
