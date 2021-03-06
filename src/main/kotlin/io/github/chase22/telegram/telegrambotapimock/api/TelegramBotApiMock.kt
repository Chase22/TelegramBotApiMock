/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.github.chase22.telegram.telegrambotapimock.api

import io.github.chase22.telegram.telegrambotapimock.api.config.TelegramBotApiConfiguration
import io.github.chase22.telegram.telegrambotapimock.infrastructure.registry.DependencyRegistry
import io.github.chase22.telegram.telegrambotapimock.telegram.data.chat.Chat

class TelegramBotApiMock(configuration: TelegramBotApiConfiguration, port: Int) {
    private val registry = DependencyRegistry(port, configuration)

    fun start() {
        try {
            registry.apiServer.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun stop() = registry.apiServer.stop()

    fun getChatSender(chat: Chat): ChatSender {
        return ChatSender(chat, registry.updateChannel)
    }
}
