package io.github.chase22.util


import io.github.chase22.data.UserFixture
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.generics.BotSession

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

class TestBot extends TelegramLongPollingBot {

    static TestBot create(int port) {
        DefaultBotOptions botOptions = new DefaultBotOptions()
        botOptions.setBaseUrl("http://localhost:${port}/bot")
        return new TestBot(botOptions)
    }

    private BlockingQueue<Update> messageQueue = new LinkedBlockingQueue<>()
    private BotSession session

    TestBot(final DefaultBotOptions options) {
        super(options)
    }

    @Override
    void onUpdateReceived(final Update update) {
        messageQueue.offer(update)
    }

    @Override
    String getBotUsername() {
        return UserFixture.BOT_USER.username
    }

    @Override
    String getBotToken() {
        return ApiMockBuilder.TOKEN
    }

    Update waitForNextUpdate(long timeoutSeconds = 5) {
        return messageQueue.poll(timeoutSeconds, TimeUnit.SECONDS)
    }

    void register(TelegramBotsApi api) {
        session = api.registerBot(this)
    }

    void stop() {
        session.stop()
    }
}
