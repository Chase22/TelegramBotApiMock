package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.endpoint;

import io.github.Chase22.telegram.telegrambotapimock.api.endpoints.GetMeEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class EndpointRegistry {
    private final Logger LOGGER = LoggerFactory.getLogger(EndpointRegistry.class);

    private Map<String, Endpoint> endpointMap = new HashMap<>();

    public EndpointRegistry() {
        registerEndpoints();
        registerTelegramEndpoints();
    }

    public Endpoint getForPath(String path) {
        return endpointMap.get(path);
    }

    private void registerEndpoints() {
        addEndpoint(new HealthEndpoint());
    }

    private void registerTelegramEndpoints() {
        addEndpoint(new GetMeEndpoint());
    }

    private void addEndpoint(Endpoint servlet) {
        LOGGER.info("Registering servlet " + servlet.getClass().getName() + " with mapping " + servlet.getPath());
        endpointMap.put(servlet.getPath(), servlet);
    }
}