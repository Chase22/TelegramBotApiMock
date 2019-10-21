package io.github.chase22.infrastructure.server.endpoint;

import io.github.chase22.api.endpoints.GetMeEndpoint;
import io.github.chase22.infrastructure.server.TelegramApiEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class EndpointRegistry {
    private final Logger LOGGER = LoggerFactory.getLogger(EndpointRegistry.class);

    private Map<String, TelegramApiEndpoint> endpointMap = new HashMap<>();

    public EndpointRegistry() {
        registerTelegramEndpoints();
    }

    public TelegramApiEndpoint getForPath(String path) {
        return endpointMap.get(path);
    }

    private void registerTelegramEndpoints() {
        addEndpoint(new GetMeEndpoint());
    }

    private void addEndpoint(TelegramApiEndpoint servlet) {
        LOGGER.info("Registering servlet " + servlet.getClass().getName() + " with mapping " + servlet.getPath());
        endpointMap.put(servlet.getPath(), servlet);
    }
}
