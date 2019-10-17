package io.github.Chase22.telegram.telegrambotapimock.test

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.Chase22.telegram.telegrambotapimock.TelegramBotApiMock
import io.github.Chase22.telegram.telegrambotapimock.util.ApiMockBuilder
import spock.lang.Specification
import spock.lang.Subject

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

import static io.github.Chase22.telegram.telegrambotapimock.data.UserFixture.BOT_USER
import static java.net.http.HttpRequest.BodyPublishers.noBody
import static java.net.http.HttpResponse.BodyHandlers.ofString
import static org.eclipse.jetty.http.HttpHeader.CONTENT_TYPE
import static org.eclipse.jetty.http.HttpStatus.OK_200

class GetMeApiSpec extends Specification {

    final static int port = 50300
    final static client = HttpClient.newBuilder().build()

    @Subject
    TelegramBotApiMock apiMock = ApiMockBuilder.default

    def setup() {
        apiMock.stop()
        apiMock.start(port)
    }

    def "GetMe should return the bot"() {
        given:
        def request = HttpRequest.newBuilder()
                .method("GET", noBody())
                .header(CONTENT_TYPE as String, "application/json")
                .uri(URI.create("http://localhost:${port}/getMe"))
                .build()

        when:
        HttpResponse<String> response = client.send(request, ofString())

        then:
        response.statusCode() == OK_200
        Map body = new ObjectMapper().readValue(response.body(), Map)

        body.id == BOT_USER.id
        body.username == BOT_USER.username
        body.first_name == BOT_USER.firstName
        body.last_name == BOT_USER.lastName
        body.bot == BOT_USER.bot
        body.language_code == BOT_USER.languageCode
    }
}
