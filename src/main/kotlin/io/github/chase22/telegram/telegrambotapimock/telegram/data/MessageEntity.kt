package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue

data class MessageEntity(
        @field:JsonProperty("type") private val type: MessageEntityType,
        @field:JsonProperty("offset") private val offset: Int,
        @field:JsonProperty("length") private val length: Int,
        @field:JsonProperty("url") private val url: String?,
        @field:JsonProperty("user") private val user: User?
)

enum class MessageEntityType(private val type: String) {
    MENTION("mention"),
    HASHTAG("hashtag"),
    CASHTAG("cashtag"),
    BOT_COMMAND("bot_command"),
    URL("url"),
    EMAIL("email"),
    PHONE_NUMBER("phone_number"),
    BOLD("bold"),
    ITALIC("italic"),
    CODE("code"),
    PRE("pre"),
    TEXT_LINK("text_link"),
    TEXT_MENTION("text_mention");

    @JsonValue
    fun getType(): String {
        return this.type
    }
}
