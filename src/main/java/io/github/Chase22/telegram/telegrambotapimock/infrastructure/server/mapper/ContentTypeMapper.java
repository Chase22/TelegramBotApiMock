package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ContentTypeMapper {
    String getSupportedContentType();

    <T> T mapToObject(HttpServletRequest request, Class<T> target) throws IOException;
}
