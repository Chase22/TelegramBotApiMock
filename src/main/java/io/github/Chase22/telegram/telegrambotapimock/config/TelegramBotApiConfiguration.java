package io.github.Chase22.telegram.telegrambotapimock.config;

import io.github.Chase22.telegram.telegrambotapimock.api.data.User;

public class TelegramBotApiConfiguration {
    private User bot;
    private String botToken;

    public User getBot() {
        return bot;
    }

    public TelegramBotApiConfiguration bot(final User bot) {
        this.bot = bot;
        return this;
    }

    public String getBotToken() {
        return botToken;
    }

    public TelegramBotApiConfiguration botToken(final String botToken) {
        this.botToken = botToken;
        return this;
    }
}
