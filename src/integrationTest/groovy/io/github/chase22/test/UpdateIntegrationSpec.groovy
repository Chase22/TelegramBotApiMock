package io.github.chase22.test

import io.github.chase22.telegram.telegrambotapimock.api.TelegramBotApiMock
import io.github.chase22.telegram.telegrambotapimock.telegram.data.chat.Chat
import io.github.chase22.util.ApiMockBuilder
import io.github.chase22.util.TestBot
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException
import spock.lang.Specification

import static io.github.chase22.data.ChatFixture.TEST_CHAT
import static io.github.chase22.data.UserFixture.TEST_USER
import static io.github.chase22.data.UserFixture.getPrivateChat

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

        TestBot testBot = TestBot.create(ApiMockBuilder.PORT)
        testBot.register(api)

        when:
        apiMock.getChatSender(TEST_CHAT).sendMessage(TEST_USER, messageText)

        then:
        Update update = testBot.waitForNextUpdate()
        update.message.text == messageText

        cleanup:
        testBot.stop()
    }

    def "sending a message to an unknown chat should cause an exception"() {
        given:
        String messageText = "text"
        Chat chat = getPrivateChat(TEST_USER)

        TestBot testBot = TestBot.create(ApiMockBuilder.PORT)
        testBot.register(api)

        when:
        testBot.execute(new SendMessage(chat.id, messageText))

        then:
        TelegramApiRequestException e = thrown(TelegramApiRequestException)
        e.errorCode == 400
        e.apiResponse == "Bad Request: chat not found"

        cleanup:
        testBot.stop()
    }

    def "a bot should be able to send a Message to a chat"() {
        given:
        String messageText = "text"
        Chat chat = getPrivateChat(TEST_USER)
        apiMock.registerChat(chat)

        TestBot testBot = TestBot.create(ApiMockBuilder.PORT)
        testBot.register(api)

        when:
        testBot.execute(new SendMessage(chat.id, messageText))

        then:
        Update update = apiMock.getChat(chat.id).waitForNextUpdate()
        update.message.text == messageText

        cleanup:
        testBot.stop()
    }
}
