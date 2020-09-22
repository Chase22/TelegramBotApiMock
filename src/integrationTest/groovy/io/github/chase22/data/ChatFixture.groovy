package io.github.chase22.data

import io.github.chase22.telegram.telegrambotapimock.telegram.data.chat.Chat
import io.github.chase22.telegram.telegrambotapimock.telegram.data.chat.ChatType

class ChatFixture {
    static final Chat TEST_CHAT = new Chat(100, ChatType.PRIVATE, null, null, null, null, null, null, null, null, null, null, null)
}

