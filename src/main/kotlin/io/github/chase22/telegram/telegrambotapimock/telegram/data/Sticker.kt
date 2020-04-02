package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Sticker(
        @field:JsonProperty("file_id") val fileId: String,
        @field:JsonProperty("width") val width: Int,
        @field:JsonProperty("height") val height: Int,
        @field:JsonProperty("is_animated") val isAnimated: Boolean,
        @field:JsonProperty("thumb") val thumb: PhotoSize?,
        @field:JsonProperty("emoji") val emoji: String?,
        @field:JsonProperty("set_name") val setName: String?,
        @field:JsonProperty("mask_position") val maskPosition: MaskPosition?,
        @field:JsonProperty("file_size") val fileSize: Int?
)

data class StickerSet(
        @field:JsonProperty("name") val name: String,
        @field:JsonProperty("title") val title: String,
        @field:JsonProperty("is_animated") val isAnimated: Boolean,
        @field:JsonProperty("contains_masks") val containsMasks: Boolean,
        @field:JsonProperty("stickers") val stickers: List<Sticker>
)

data class MaskPosition(
        @field:JsonProperty("point") val point: String,
        @field:JsonProperty("x_shift") val xShift: Float,
        @field:JsonProperty("y_shift") val yShift: Float,
        @field:JsonProperty("scale") val scale: Float
)