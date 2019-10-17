package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server;

import io.github.Chase22.telegram.telegrambotapimock.infrastructure.HttpServletJsonResponse;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper.ContentTypeMapper;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper.ContentTypeMapperContext;
import io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper.NoContentTypeMapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public abstract class TelegramApiServlet<T> extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramApiServlet.class);

    private final ContentTypeMapperContext mapperContext;

    protected abstract void process(T body, HttpServletJsonResponse response);

    protected abstract Class<T> getBodyType();

    protected boolean hasParameters() {
        return true;
    }

    public TelegramApiServlet() {
        mapperContext = ContentTypeMapperContext.getInstance();
    }

    private void process(final HttpServletRequest request, final HttpServletResponse response) {
        if (isNull(request.getContentType())) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {
            T body = null;
            if (hasParameters()) {
                final ContentTypeMapper contentMapper = mapperContext.getMapperForType(request.getContentType());
                body = contentMapper.mapToObject(request, getBodyType());
            }
            process(body, new HttpServletJsonResponse(response, mapperContext.getObjectMapper()));

        } catch (IOException e) {
            LOGGER.error("Error processing request", e);
            response.setStatus(SC_INTERNAL_SERVER_ERROR);
        } catch (NoContentTypeMapperException e) {
            LOGGER.error("No Content Type mapper for type " + request.getContentType(), e);
            response.setStatus(SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) {
        process(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) {
        process(req, resp);
    }
}
