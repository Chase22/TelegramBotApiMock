package io.github.chase22.util

import io.github.chase22.TelegramBotApiMock
import io.github.chase22.config.TelegramBotApiConfiguration
import io.github.chase22.data.UserFixture

class ApiMockBuilder {
    final static int port = 50300

    static TelegramBotApiMock getDefault() {
        def configuration = new TelegramBotApiConfiguration(
                UserFixture.BOT_USER,
                "token"
        )

        return new TelegramBotApiMock(configuration, port)
    }
}
