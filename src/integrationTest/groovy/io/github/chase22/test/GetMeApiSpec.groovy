package io.github.chase22.test


import com.fasterxml.jackson.databind.ObjectMapper
import io.github.chase22.telegram.telegrambotapimock.api.TelegramBotApiMock
import io.github.chase22.util.ApiMockBuilder
import io.undertow.util.StatusCodes
import spock.lang.Specification
import spock.lang.Subject

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

import static io.github.chase22.data.UserFixture.BOT_USER
import static io.undertow.util.Headers.CONTENT_TYPE_STRING
import static java.net.http.HttpRequest.BodyPublishers.noBody
import static java.net.http.HttpResponse.BodyHandlers.ofString

class GetMeApiSpec extends Specification {

    final static client = HttpClient.newBuilder().build()

    @Subject
    TelegramBotApiMock apiMock = ApiMockBuilder.default

    def setup() {
        apiMock.stop()
        apiMock.start()
    }

    def "GetMe should return the bot"() {
        given:
        def request = HttpRequest.newBuilder()
                .method("GET", noBody())
                .header(CONTENT_TYPE_STRING, "application/json")
                .uri(URI.create("http://localhost:${ApiMockBuilder.PORT}/token/getMe"))
                .build()

        when:
        HttpResponse<String> response = client.send(request, ofString())

        then:
        response.statusCode() == StatusCodes.OK
        Map body = new ObjectMapper().readValue(response.body(), Map)

        body.id == BOT_USER.id
        body.username == BOT_USER.username
        body.first_name == BOT_USER.firstName
        body.last_name == BOT_USER.lastName
        body.is_bot == BOT_USER.bot
        body.language_code == BOT_USER.languageCode
    }
}
