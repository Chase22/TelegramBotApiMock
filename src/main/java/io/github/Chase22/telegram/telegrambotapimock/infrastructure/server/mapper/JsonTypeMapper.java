package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JsonTypeMapper implements ContentTypeMapper {
    @Override
    public String getSupportedContentType() {
        return "application/json";
    }

    @Override
    public <T> T mapToObject(final HttpServletRequest request, Class<T> target) throws IOException {
        return ContentTypeMapperContext.getInstance().getObjectMapper().readValue(request.getInputStream(), target);
    }
}
