package io.github.chase22.telegram.telegrambotapimock.telegram.data.chat

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatPermissions(
        @field:JsonProperty("can_send_messages") private val canSendMessages: Boolean?,
        @field:JsonProperty("can_send_media_messages") private val canSendMediaMessages: Boolean?,
        @field:JsonProperty("can_send_polls") private val canSendPolls: Boolean?,
        @field:JsonProperty("can_send_other_messages") private val canSendOtherMessages: Boolean?,
        @field:JsonProperty("can_add_web_page_previews") private val canAddWebPagePreviews: Boolean?,
        @field:JsonProperty("can_change_info") private val canChangeInfo: Boolean?,
        @field:JsonProperty("can_invite_users") private val canInviteUsers: Boolean?,
        @field:JsonProperty("can_pin_messages") private val canPinMessages: Boolean?
)
