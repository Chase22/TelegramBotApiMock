package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.endpoint;

import io.github.Chase22.telegram.telegrambotapimock.api.servlets.GetMeServlet;
import org.eclipse.jetty.servlet.ServletHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;

public class EndpointRegistry {
    private final Logger LOGGER = LoggerFactory.getLogger(EndpointRegistry.class);
    private final ServletHandler servletHandler = new ServletHandler();

    public EndpointRegistry() {
        registerEndpoints();
        registerTelegramEndpoints();
    }

    public ServletHandler getServletHandler() {
        return servletHandler;
    }

    private void registerEndpoints() {
        addEndpoint(HealthEndpoint.class, "/health");
    }

    private void registerTelegramEndpoints() {
        addEndpoint(GetMeServlet.class, "/getMe");
    }

    private void addEndpoint(Class<? extends Servlet> servlet, String path) {
        LOGGER.info("Registering servlet " + servlet.getName() + " with mapping " + path);
        servletHandler.addServletWithMapping(servlet, path);
    }
}
