package io.github.chase22.telegram.telegrambotapimock.infrastructure.server.handler

import com.fasterxml.jackson.databind.ObjectMapper
import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange
import io.undertow.server.ServerConnection
import io.undertow.util.PathTemplateMatch
import io.undertow.util.StatusCodes
import spock.lang.Specification
import spock.lang.Subject

class TokenHandlerSpec extends Specification {

    HttpHandler handlerMock = Mock()

    ServerConnection serverConnectionMock = Mock()
    HttpServerExchange exchange = new HttpServerExchange(serverConnectionMock)

    @Subject
    TokenHandler tokenHandler = new TokenHandler("someToken", handlerMock)

    def "handleRequest() should set statusCode 404 if no id was supplied"() {

        when:
        tokenHandler.handleRequest(exchange)

        then:
        exchange.getStatusCode() == StatusCodes.NOT_FOUND
    }

    def "handleRequest() should set a statusCode 403 if the given token was wrong"() {
        given:
        PathTemplateMatch pathTemplateMatchMock = Mock(PathTemplateMatch) {
            getParameters() >> ["id":"someOtherToken"]
        }

        exchange.putAttachment(PathTemplateMatch.ATTACHMENT_KEY, pathTemplateMatchMock)

        when:
        tokenHandler.handleRequest(exchange)

        then:
        exchange.getStatusCode() == StatusCodes.UNAUTHORIZED
    }

    def "handleRequest should call the next handler if the token is correct"() {
        given:
        PathTemplateMatch pathTemplateMatchMock = Mock(PathTemplateMatch) {
            getParameters() >> ["id":"someToken"]
        }

        exchange.putAttachment(PathTemplateMatch.ATTACHMENT_KEY, pathTemplateMatchMock)

        when:
        tokenHandler.handleRequest(exchange)

        then:
        1 * handlerMock.handleRequest(exchange)
    }
}
