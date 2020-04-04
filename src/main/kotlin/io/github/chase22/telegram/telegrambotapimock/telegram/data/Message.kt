package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.chase22.telegram.telegrambotapimock.telegram.data.chat.Chat
import io.github.chase22.telegram.telegrambotapimock.telegram.data.passport.PassportData
import io.github.chase22.telegram.telegrambotapimock.telegram.data.shipping.SuccessfulPayment
import java.time.LocalDateTime

data class Message(
        @field:JsonProperty("message_id") private val messageId: Int,
        @field:JsonProperty("from") private val from: User? = null,
        @field:JsonProperty("date") private val date: LocalDateTime,
        @field:JsonProperty("chat") private val chat: Chat,
        @field:JsonProperty("forward_from") private val forwardFrom: User? = null,
        @field:JsonProperty("forward_from_chat") private val forwardFromChat: Chat? = null,
        @field:JsonProperty("forward_from_message_id") private val forwardFromMessageId: Int? = null,
        @field:JsonProperty("forward_signature") private val forwardSignature: String? = null,
        @field:JsonProperty("forward_sender_name") private val forwardSenderName: String? = null,
        @field:JsonProperty("forward_date") private val forwardDate: Int? = null,
        @field:JsonProperty("reply_to_message") private val replyToMessage: Message? = null,
        @field:JsonProperty("edit_date") private val editDate: Int? = null,
        @field:JsonProperty("media_group_id") private val mediaGroupId: String? = null,
        @field:JsonProperty("author_signature") private val authorSignature: String? = null,
        @field:JsonProperty("text") private val text: String? = null,
        @field:JsonProperty("entities") private val entities: List<MessageEntity>? = null,
        @field:JsonProperty("caption_entities") private val captionEntities: List<MessageEntity>? = null,
        @field:JsonProperty("audio") private val audio: Audio? = null,
        @field:JsonProperty("document") private val document: Document? = null,
        @field:JsonProperty("animation") private val animation: Animation? = null,
        @field:JsonProperty("game") private val game: Game? = null,
        @field:JsonProperty("photo") private val photo: List<PhotoSize>? = null,
        @field:JsonProperty("sticker") private val sticker: Sticker? = null,
        @field:JsonProperty("video") private val video: Video? = null,
        @field:JsonProperty("voice") private val voice: Voice? = null,
        @field:JsonProperty("video_note") private val videoNote: VideoNote? = null,
        @field:JsonProperty("caption") private val caption: String? = null,
        @field:JsonProperty("contact") private val contact: Contact? = null,
        @field:JsonProperty("location") private val location: Location? = null,
        @field:JsonProperty("venue") private val venue: Venue? = null,
        @field:JsonProperty("poll") private val poll: Poll? = null,
        @field:JsonProperty("new_chat_members") private val newChatMembers: List<User>? = null,
        @field:JsonProperty("left_chat_member") private val leftChatMember: User? = null,
        @field:JsonProperty("new_chat_title") private val newChatTitle: String? = null,
        @field:JsonProperty("new_chat_photo") private val newChatPhoto: List<PhotoSize>? = null,
        @field:JsonProperty("delete_chat_photo") private val deleteChatPhoto: Boolean? = null,
        @field:JsonProperty("group_chat_created") private val groupChatCreated: Boolean? = null,
        @field:JsonProperty("supergroup_chat_created") private val supergroupChatCreated: Boolean? = null,
        @field:JsonProperty("channel_chat_created") private val channelChatCreated: Boolean? = null,
        @field:JsonProperty("migrate_to_chat_id") private val migrateToChatId: Int? = null,
        @field:JsonProperty("migrate_from_chat_id") private val migrateFromChatId: Int? = null,
        @field:JsonProperty("pinned_message") private val pinnedMessage: Message? = null,
        @field:JsonProperty("invoice") private val invoice: Invoice? = null,
        @field:JsonProperty("successful_payment") private val successfulPayment: SuccessfulPayment? = null,
        @field:JsonProperty("connected_website") private val connectedWebsite: String? = null,
        @field:JsonProperty("passport_data") private val passportData: PassportData? = null,
        @field:JsonProperty("reply_markup") private val replyMarkup: InlineKeyboardMarkup? = null
)
