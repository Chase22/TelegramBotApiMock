package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.Chase22.telegram.telegrambotapimock.config.TelegramBotApiConfiguration;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.endpoint.EndpointRegistry;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.handler.HandlerChain;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper.ContentTypeMapperContext;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper.JsonTypeMapper;

public class DependencyRegistry {
    private final ObjectMapper objectMapper;
    private final JsonTypeMapper jsonTypeMapper;
    private final EndpointRegistry endpointRegistry;
    private final ApiServer apiServer;
    private final HandlerChain handlerChain;

    public DependencyRegistry(int port, final TelegramBotApiConfiguration configuration) {
        objectMapper = new ObjectMapper();
        jsonTypeMapper = new JsonTypeMapper();
        endpointRegistry = new EndpointRegistry();

        handlerChain = new HandlerChain(endpointRegistry, configuration);

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        ContentTypeMapperContext.init(objectMapper, jsonTypeMapper);

        apiServer = new ApiServer(port, handlerChain);
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public JsonTypeMapper getJsonTypeMapper() {
        return jsonTypeMapper;
    }

    public EndpointRegistry getEndpointRegistry() {
        return endpointRegistry;
    }

    public ApiServer getApiServer() {
        return apiServer;
    }

    public HandlerChain getHandlerChain() {
        return handlerChain;
    }
}
