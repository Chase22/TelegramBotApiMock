package io.github.chase22.util

import io.github.chase22.TelegramBotApiMock
import io.github.chase22.config.TelegramBotApiConfiguration
import io.github.chase22.data.UserFixture

class ApiMockBuilder {

    static TelegramBotApiMock getDefault() {
        return new TelegramBotApiMock(
                new TelegramBotApiConfiguration(
                        UserFixture.BOT_USER,
                        "token"
                )
        )
    }
}
