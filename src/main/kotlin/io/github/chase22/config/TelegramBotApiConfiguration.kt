package io.github.chase22.config;

import io.github.chase22.telegram.telegrambotapimock.api.data.User;

data class TelegramBotApiConfiguration(
        val bot: User,
        val botToken: String
)