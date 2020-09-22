package io.github.chase22.telegram.telegrambotapimock.api.registry
import io.github.chase22.telegram.telegrambotapimock.telegram.data.Update
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.TimeUnit
import io.github.chase22.telegram.telegrambotapimock.telegram.data.chat.Chat as TelegramChat

class Chat(val telegramChat: TelegramChat) {
    val updateQueue: BlockingQueue<Update> = ArrayBlockingQueue(100)

    fun waitForNextMessage(timeoutSeconds: Long): Update? {
        return updateQueue.poll(timeoutSeconds, TimeUnit.SECONDS)
    }
}
