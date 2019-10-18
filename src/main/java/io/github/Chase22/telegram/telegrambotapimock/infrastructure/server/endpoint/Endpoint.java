package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.endpoint;

import io.undertow.server.HttpServerExchange;

public interface Endpoint {

    String getPath();
    void process(HttpServerExchange exchange);
}
