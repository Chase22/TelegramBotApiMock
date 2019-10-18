package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper;

import io.undertow.server.HttpServerExchange;

import java.io.IOException;

public interface ContentTypeMapper {
    String getSupportedContentType();

    <T> T mapToObject(HttpServerExchange exchange, Class<T> target) throws IOException;
}
