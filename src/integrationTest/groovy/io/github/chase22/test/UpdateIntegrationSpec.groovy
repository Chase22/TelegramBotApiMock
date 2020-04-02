package io.github.chase22.test

import io.github.chase22.telegram.telegrambotapimock.api.TelegramBotApiMock
import io.github.chase22.telegram.telegrambotapimock.telegram.data.User
import io.github.chase22.telegram.telegrambotapimock.telegram.data.chat.Chat
import io.github.chase22.telegram.telegrambotapimock.telegram.data.chat.ChatType
import io.github.chase22.util.ApiMockBuilder
import io.github.chase22.util.TestBot
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.objects.Update
import spock.lang.PendingFeature
import spock.lang.Specification

class UpdateIntegrationSpec extends Specification {
    TelegramBotApiMock apiMock

    void setup() {
        apiMock = ApiMockBuilder.default
        apiMock.start()
    }

    void cleanup() {
        apiMock.stop()
    }

    def "a bot should be able to be started against the mock"() {
        given:
        TestBot testBot = TestBot.create(ApiMockBuilder.PORT)

        when:
        ApiContextInitializer.init()
        TelegramBotsApi api = new TelegramBotsApi()
        api.registerBot(testBot)

        then:
        noExceptionThrown()
    }

    def "a bot should receive a message send to the mock"() {
        given:
        String messageText = "text"
        User someUser = new User(1000, false, "de", "testuser")
        Chat someChat = new Chat(100, ChatType.PRIVATE, null, null, null, null, null, null, null, null, null, null, null)

        TestBot testBot = TestBot.create(ApiMockBuilder.PORT)

        when:
        ApiContextInitializer.init()
        TelegramBotsApi api = new TelegramBotsApi()
        api.registerBot(testBot)

        apiMock.getChatSender(someChat).sendMessage(someUser, messageText)


        then:
        Update update = testBot.waitForNextUpdate()
        update.message.text == messageText
    }
}