package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class ApiServer {

    private final Server server;

    public ApiServer(int port) {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.setConnectors(new Connector[]{connector});
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(HealthEndpoint.class, "/health");
    }

    public void start() throws Exception {
        server.start();
    }
}
