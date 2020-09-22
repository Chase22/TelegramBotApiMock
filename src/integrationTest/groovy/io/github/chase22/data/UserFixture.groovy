package io.github.chase22.data

import io.github.chase22.telegram.telegrambotapimock.telegram.data.User
import io.github.chase22.telegram.telegrambotapimock.telegram.data.chat.Chat
import io.github.chase22.telegram.telegrambotapimock.telegram.data.chat.ChatType


class UserFixture {
    static final User BOT_USER = new User(0, true, "test", "bot", "testbot", "en")
    static final User TEST_USER = new User(1000, false, "de", "testuser")

    static Chat getPrivateChat(User user) {
        return new Chat(
                user.id as Integer,
                ChatType.PRIVATE,
                null,
                user.username,
                user.firstName,
                user.lastName,
                null,
                null,
                null,
                null,
                null,
                null,
                false
        )
    }
}
