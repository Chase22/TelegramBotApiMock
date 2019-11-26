package io.github.chase22.test


import io.github.chase22.util.ApiMockBuilder
import io.github.chase22.util.TestBot
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi
import spock.lang.Specification

class UpdateIntegrationSpec extends Specification {
    def "a bot should be able to be started against the mock"() {
        given:
        ApiMockBuilder.default.start()
        TestBot testBot = TestBot.create(ApiMockBuilder.PORT)

        when:
        ApiContextInitializer.init()
        TelegramBotsApi api = new TelegramBotsApi()
        api.registerBot(testBot)

        then:
        noExceptionThrown()
    }
}