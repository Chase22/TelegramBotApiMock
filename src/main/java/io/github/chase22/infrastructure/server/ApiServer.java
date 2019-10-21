package io.github.chase22.infrastructure.server;

import io.github.chase22.infrastructure.server.handler.HandlerChain;
import io.undertow.Undertow;

public class ApiServer {

    private final Undertow server;

    ApiServer(int port, HandlerChain handlerChain) {
        server = Undertow.builder().addHttpListener(port, "localhost")
                .setHandler(handlerChain.getHandler())
                .build();
    }

    public void start() throws Exception {
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }
}
