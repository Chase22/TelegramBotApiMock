package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server;

import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.endpoint.EndpointRegistry;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class ApiServer {

    private final Server server;

    ApiServer(int port, EndpointRegistry endpointRegistry) {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.setConnectors(new Connector[]{connector});

        server.setHandler(endpointRegistry.getServletHandler());
    }

    public void start() throws Exception {
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }

    public boolean isRunning() {
        return server.isRunning();
    }
}
