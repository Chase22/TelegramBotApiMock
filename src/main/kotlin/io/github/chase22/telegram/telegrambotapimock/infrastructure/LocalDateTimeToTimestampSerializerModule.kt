package io.github.chase22.telegram.telegrambotapimock.infrastructure

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.time.LocalDateTime
import java.time.ZoneOffset

class LocalDateTimeToTimestampSerializerModule: SimpleModule() {

    init {
        this.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
    }

    class LocalDateTimeSerializer(clazz: Class<LocalDateTime>? = null): StdSerializer<LocalDateTime>(clazz) {
        override fun serialize(value: LocalDateTime?, gen: JsonGenerator?, provider: SerializerProvider?) {
            gen!!.writeNumber(value!!.toEpochSecond(ZoneOffset.UTC))
        }
    }
}
