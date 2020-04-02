package io.github.chase22.telegram.telegrambotapimock.telegram.data.chat

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.github.chase22.telegram.telegrambotapimock.telegram.data.Message

data class Chat(
        @field:JsonProperty("id") private val id: Int,
        @field:JsonProperty("type") private val type: ChatType,
        @field:JsonProperty("title") private val title: String? = null,
        @field:JsonProperty("username") private val username: String? = null,
        @field:JsonProperty("first_name") private val firstName: String? = null,
        @field:JsonProperty("last_name") private val lastName: String? = null,
        @field:JsonProperty("photo") private val photo: ChatPhoto? = null,
        @field:JsonProperty("description") private val description: String? = null,
        @field:JsonProperty("invite_link") private val inviteLink: String? = null,
        @field:JsonProperty("pinned_message") private val pinnedMessage: Message? = null,
        @field:JsonProperty("permissions") private val permissions: ChatPermissions? = null,
        @field:JsonProperty("sticker_set_name") private val stickerSetName: String? = null,
        @field:JsonProperty("can_set_sticker_set") private val canSetStickerSet: Boolean? = null
)

enum class ChatType{
    PRIVATE,
    GROUP,
    SUPERGROUP,
    CHANNEL;

    @JsonValue
    fun toLowerCase() = this.name.toLowerCase()
}