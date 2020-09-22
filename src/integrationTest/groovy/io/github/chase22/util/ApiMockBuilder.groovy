package io.github.chase22.util

import io.github.chase22.data.UserFixture
import io.github.chase22.telegram.telegrambotapimock.api.TelegramBotApiMock
import io.github.chase22.telegram.telegrambotapimock.api.config.TelegramBotApiConfiguration

class ApiMockBuilder {
    public static final int PORT = 50300
    public static final String TOKEN = "token"

    static TelegramBotApiMock getDefault() {
        def configuration = new TelegramBotApiConfiguration(
                UserFixture.BOT_USER,
                TOKEN,
                true
        )

        return new TelegramBotApiMock(configuration, PORT)
    }
}
