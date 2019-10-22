package io.github.chase22.infrastructure.server.handler

import io.undertow.server.HttpHandler
import io.undertow.server.HttpServerExchange
import io.undertow.server.ServerConnection
import io.undertow.util.PathTemplateMatch
import io.undertow.util.StatusCodes
import spock.lang.Specification
import spock.lang.Subject

class TokenHandlerSpec extends Specification {

    HttpHandler handlerMock = Mock()
    PathTemplateMatch pathTemplateMatchMock


    ServerConnection serverConnectionMock = Mock()
    HttpServerExchange exchange = new HttpServerExchange(serverConnectionMock)

    @Subject
    TokenHandler tokenHandler = new TokenHandler("someToken", handlerMock)

    def "handleRequest() should set response code 404 if no id was supplied"() {

        when:
        tokenHandler.handleRequest(exchange)

        pathTemplateMatchMock = Mock(PathTemplateMatch) {
            getParameters() >> [:]
        }
        then:
        exchange.getStatusCode() == StatusCodes.NOT_FOUND
    }
}
