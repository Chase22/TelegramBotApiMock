package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.Chase22.telegram.telegrambotapimock.config.TelegramBotApiConfiguration;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.Router;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.endpoint.EndpointRegistry;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.BlockingHandler;
import io.undertow.server.handlers.PathTemplateHandler;

public class HandlerChain {
    private final HttpHandler handler;

    public HandlerChain(final EndpointRegistry endpointRegistry,
                        final TelegramBotApiConfiguration config,
                        final ObjectMapper objectMapper
    ) {

        ParameterHandler parameterHandler = new ParameterHandler(objectMapper);

        Router router = new Router(parameterHandler, endpointRegistry);
        BlockingHandler blockingHandler = new BlockingHandler(router);

        TokenHandler tokenHandler = new TokenHandler(config.getBotToken(), blockingHandler);

        PathTemplateHandler pathTemplateHandler = new PathTemplateHandler();
        pathTemplateHandler.add("/{id}/*", tokenHandler);

        handler = pathTemplateHandler;
    }

    public HttpHandler getHandler() {
        return handler;
    }
}
