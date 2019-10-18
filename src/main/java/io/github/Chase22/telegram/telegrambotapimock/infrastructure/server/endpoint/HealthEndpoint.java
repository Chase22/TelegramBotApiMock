package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.endpoint;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.StatusCodes;

public class HealthEndpoint implements Endpoint {

    @Override
    public String getPath() {
        return "/health";
    }

    @Override
    public void process(final HttpServerExchange exchange) {
        exchange.setStatusCode(StatusCodes.OK);
    }
}
