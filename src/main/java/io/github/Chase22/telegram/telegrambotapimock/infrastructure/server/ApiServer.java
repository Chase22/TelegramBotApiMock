package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server;

import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.endpoint.EndpointRegistry;
import io.undertow.Undertow;
import io.undertow.server.handlers.BlockingHandler;

public class ApiServer {

    private final Undertow server;
    private final Router router;

    ApiServer(int port, EndpointRegistry endpointRegistry) {
        router = new Router(endpointRegistry);

        server = Undertow.builder().addHttpListener(port, "localhost").setHandler(new BlockingHandler(router)).build();
    }

    public void start() throws Exception {
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }
}
