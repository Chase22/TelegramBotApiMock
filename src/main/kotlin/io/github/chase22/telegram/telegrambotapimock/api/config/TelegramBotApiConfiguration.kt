package io.github.chase22.telegram.telegrambotapimock.api.config;

import io.github.chase22.telegram.telegrambotapimock.telegram.data.User;

data class TelegramBotApiConfiguration(
        val bot: User,
        val botToken: String
)