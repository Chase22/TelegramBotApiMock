package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Update(
        @field:JsonProperty("update_id") val updateId: Int,
        @field:JsonProperty("message") val message: Message? = null,
        @field:JsonProperty("edited_message") val editedMessage: Message? = null,
        @field:JsonProperty("channel_post") val channelPost: Message? = null,
        @field:JsonProperty("edited_channel_post") val editedChannelPost: Message? = null,
        @field:JsonProperty("inline_query") val inlineQuery: InlineQuery? = null,
        @field:JsonProperty("chosen_inline_result") val chosenInlineResult: ChosenInlineResult? = null,
        @field:JsonProperty("callback_query") val callbackQuery: CallbackQuery? = null,
        @field:JsonProperty("shipping_query") val shippingQuery: ShippingQuery? = null,
        @field:JsonProperty("pre_checkout_query") val preCheckoutQuery: PreCheckoutQuery? = null,
        @field:JsonProperty("poll") val poll: Poll? = null
)