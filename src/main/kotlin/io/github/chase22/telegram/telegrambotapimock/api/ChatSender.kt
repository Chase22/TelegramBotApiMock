package io.github.chase22.telegram.telegrambotapimock.api

import io.github.chase22.telegram.telegrambotapimock.telegram.data.Message
import io.github.chase22.telegram.telegrambotapimock.telegram.data.Update
import io.github.chase22.telegram.telegrambotapimock.telegram.data.User
import io.github.chase22.telegram.telegrambotapimock.telegram.data.chat.Chat
import java.time.LocalDateTime
import java.util.concurrent.BlockingQueue

class ChatSender(private val chat: Chat, private val channel: BlockingQueue<Update>) {
    fun sendMessage(from: User, message: String) {
        sendUpdate(Update(1,
                Message(
                        messageId = 0,
                        from = from,
                        text = message,
                        chat = chat,
                        date = LocalDateTime.now())
        ))
    }

    fun sendUpdate(update: Update) {
        channel.offer(update)
    }
}
