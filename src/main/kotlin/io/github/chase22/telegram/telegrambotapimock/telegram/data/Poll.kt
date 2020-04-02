package io.github.chase22.telegram.telegrambotapimock.telegram.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Poll(
        @field:JsonProperty("id") val id: String,
        @field:JsonProperty("question") val question: String,
        @field:JsonProperty("options") val options: List<PollOption>,
        @field:JsonProperty("is_closed") val is_closed: Boolean
)

data class PollOption(
        @field:JsonProperty("text") val text: String?,
        @field:JsonProperty("voter_count") val voter_count: Int
)