package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.Chase22.telegram.telegrambotapimock.config.TelegramBotApiConfiguration;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.endpoint.EndpointRegistry;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.handler.HandlerChain;

public class DependencyRegistry {
    public final ObjectMapper objectMapper;
    public final EndpointRegistry endpointRegistry;
    public final ApiServer apiServer;
    public final HandlerChain handlerChain;

    public DependencyRegistry(int port, final TelegramBotApiConfiguration configuration) {
        objectMapper = new ObjectMapper();
        endpointRegistry = new EndpointRegistry();

        handlerChain = new HandlerChain(endpointRegistry, configuration, objectMapper);

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        apiServer = new ApiServer(port, handlerChain);
    }

}
