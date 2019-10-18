package io.github.Chase22.telegram.telegrambotapimock.util

import io.github.Chase22.telegram.telegrambotapimock.TelegramBotApiMock
import io.github.Chase22.telegram.telegrambotapimock.config.TelegramBotApiConfiguration
import io.github.Chase22.telegram.telegrambotapimock.data.UserFixture

class ApiMockBuilder {

    static TelegramBotApiMock getDefault() {
        return new TelegramBotApiMock(
                new TelegramBotApiConfiguration()
                        .bot(UserFixture.BOT_USER)
                        .botToken("token")
        )
    }
}
