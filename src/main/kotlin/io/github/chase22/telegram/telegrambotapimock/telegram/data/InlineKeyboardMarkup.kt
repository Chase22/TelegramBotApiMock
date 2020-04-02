package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty

class InlineKeyboardMarkup(
        @field:JsonProperty("inline_keyboard") val inlineKeyboard: List<InlineKeyboardButton>
)

class InlineKeyboardButton
