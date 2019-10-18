package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server;

import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.handler.HandlerChain;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;

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
