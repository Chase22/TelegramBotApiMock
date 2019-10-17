package io.github.Chase22.telegram.telegrambotapimock.api.servlets;

import io.github.Chase22.telegram.telegrambotapimock.TelegramBotApiMock;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.HttpServletJsonResponse;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.TelegramApiServlet;

public class GetMeServlet extends TelegramApiServlet<Void> {
    @Override
    protected Class<Void> getBodyType() {
        return Void.class;
    }

    @Override
    protected boolean hasParameters() {
        return false;
    }

    @Override
    protected void process(final Void body, final HttpServletJsonResponse response) {
        response.setBody(TelegramBotApiMock.configuration.getBot());
    }
}
