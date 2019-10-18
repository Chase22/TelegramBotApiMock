package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server;

import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.endpoint.Endpoint;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.endpoint.EndpointRegistry;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.undertow.util.StatusCodes.NOT_FOUND;
import static java.util.Objects.nonNull;

public class Router implements HttpHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Router.class);
    private final EndpointRegistry endpointRegistry;

    public Router(final EndpointRegistry endpointRegistry) {
        this.endpointRegistry = endpointRegistry;
    }

    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {
        String[] requestSplit = exchange.getRequestPath().substring(1).split("/", 2);
        String botId = requestSplit[0];
        String path = "/" + requestSplit[1];

        Endpoint endpoint = endpointRegistry.getForPath(path);

        if (nonNull(endpoint)) {
            endpoint.process(exchange);
        } else {
            LOGGER.warn("No Endpoint for path {}", path );
            exchange.setStatusCode(NOT_FOUND);
        }

        exchange.endExchange();
    }
}
