package io.github.chase22.infrastructure.server.handler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.PathTemplateMatch;
import io.undertow.util.StatusCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenHandler implements HttpHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenHandler.class);


    private final String token;
    private final HttpHandler next;

    public TokenHandler(final String token, final HttpHandler next) {
        this.token = token;
        this.next = next;
    }

    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {
        String id = exchange.getAttachment(PathTemplateMatch.ATTACHMENT_KEY).getParameters().get("id");

        if (id == null) {
            LOGGER.warn("Passed token was null");
            exchange.setStatusCode(StatusCodes.NOT_FOUND);
            exchange.endExchange();
        } else if (!id.equalsIgnoreCase(token)) {
            LOGGER.warn("Passed token was incorrect. Expected {}, Got: {}", token, id);
            exchange.setStatusCode(StatusCodes.UNAUTHORIZED);
            exchange.endExchange();
        } else {
            next.handleRequest(exchange);
        }
    }
}
