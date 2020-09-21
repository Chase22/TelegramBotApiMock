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
import spock.lang.Specification

class UpdateIntegrationSpec extends Specification {
    TelegramBotApiMock apiMock
    TelegramBotsApi api

    void setup() {
        apiMock = ApiMockBuilder.default
        apiMock.start()

        ApiContextInitializer.init()
        api = new TelegramBotsApi()
    }

    void cleanup() {
        apiMock.stop()
    }

    def "a bot should be able to be started against the mock"() {
        given:
        TestBot testBot = TestBot.create(ApiMockBuilder.PORT)

        when:
        testBot.register(api)
        testBot.stop()

        then:
        noExceptionThrown()
    }

    def "a bot should receive a message send to the mock"() {
        given:
        String messageText = "text"
        User someUser = new User(1000, false, "de", "testuser")
        Chat someChat = new Chat(100, ChatType.PRIVATE, null, null, null, null, null, null, null, null, null, null, null)

        TestBot testBot = TestBot.create(ApiMockBuilder.PORT)
        testBot.register(api)

        when:
        apiMock.getChatSender(someChat).sendMessage(someUser, messageText)

        then:
        Update update = testBot.waitForNextUpdate()
        update.message.text == messageText

        cleanup:
        testBot.stop()
    }
}
