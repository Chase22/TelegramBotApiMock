package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.Chase22.telegram.telegrambotapimock.config.TelegramBotApiConfiguration;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.endpoint.EndpointRegistry;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper.ContentTypeMapperContext;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper.JsonTypeMapper;

public class DependencyRegistry {
    private final ObjectMapper objectMapper;
    private final JsonTypeMapper jsonTypeMapper;
    private final EndpointRegistry endpointRegistry;
    private final ApiServer apiServer;

    public DependencyRegistry(int port, final TelegramBotApiConfiguration configuration) {
        objectMapper = new ObjectMapper();
        jsonTypeMapper = new JsonTypeMapper();
        endpointRegistry = new EndpointRegistry();


        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        ContentTypeMapperContext.init(objectMapper, jsonTypeMapper);

        apiServer = new ApiServer(port, endpointRegistry);
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
}
