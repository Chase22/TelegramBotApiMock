package io.github.Chase22.telegram.telegrambotapimock.api.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.Chase22.telegram.telegrambotapimock.TelegramBotApiMock;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.TelegramApiEndpoint;
import io.undertow.server.HttpServerExchange;

import java.io.IOException;

public class GetMeEndpoint extends TelegramApiEndpoint<Void> {
    @Override
    public Class<Void> getBodyType() {
        return null;
    }

    @Override
    public String getPath() {
        return "/getMe";
    }

    @Override
    protected void process(final HttpServerExchange exchange) {
        try {
            new ObjectMapper().writeValue(exchange.getOutputStream(), TelegramBotApiMock.configuration.getBot());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
