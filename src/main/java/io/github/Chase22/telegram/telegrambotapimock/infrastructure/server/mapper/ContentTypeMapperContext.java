package io.github.Chase22.telegram.telegrambotapimock.infrastructure.server.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class ContentTypeMapperContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentTypeMapperContext.class);

    private static ContentTypeMapperContext instance;

    private Map<String, ContentTypeMapper> mapper = new HashMap<>();
    private final ObjectMapper objectMapper;

    private ContentTypeMapperContext(final ObjectMapper objectMapper, ContentTypeMapper... mappers) {
        this.objectMapper = objectMapper;
        Arrays.stream(mappers).forEach(this::registerMapper);

        String mapperTypes = this.mapper.values()
                .stream()
                .map(ContentTypeMapper::getSupportedContentType)
                .collect(Collectors.joining(", ", "[", "]"));

        LOGGER.info("Registered Mapper for types {}", mapperTypes);
    }

    public static ContentTypeMapperContext getInstance() {
        if (isNull(instance)) {
            throw new IllegalStateException("ContentMapperTypeContext was not initialized");
        }
        return instance;
    }

    public static void init(ObjectMapper objectMapper, ContentTypeMapper... mapper) {
        instance = new ContentTypeMapperContext(objectMapper, mapper);
    }

    public ContentTypeMapper getMapperForType(String contentType) {
        return Optional.ofNullable(mapper.get(contentType)).orElseThrow(NoContentTypeMapperException::new);
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private void registerMapper(ContentTypeMapper typeMapper) {
        if (isNull(typeMapper.getSupportedContentType())) {
            LOGGER.error("Content mapper with supported type equals null", typeMapper);
        } else {
            if (mapper.containsKey(typeMapper.getSupportedContentType())) {
                LOGGER.error("Duplicate Mapper for type {}", typeMapper);
            } else {
                mapper.put(typeMapper.getSupportedContentType(), typeMapper);
            }
        }
    }
}
