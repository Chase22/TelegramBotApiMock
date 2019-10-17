package io.github.Chase22.telegram.telegrambotapimock.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpServletJsonResponse {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServletJsonResponse.class);
    private final HttpServletResponse httpServletResponse;
    private final ObjectMapper objectMapper;

    public HttpServletJsonResponse(final HttpServletResponse httpServletResponse, ObjectMapper objectMapper) {
        this.httpServletResponse = httpServletResponse;
        this.objectMapper = objectMapper;
    }

    public void setBody(Object body) {
        try {
            objectMapper.writeValue(httpServletResponse.getOutputStream(), body);
        } catch (IOException e) {
            LOGGER.error("IOException while trying to set body", e);
        }
    }
}
