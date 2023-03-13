package co.yml.ychat.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a message in a conversation, consisting of a [role] indicating the speaker
 * (e.g., “system”, “user” or “assistant”), and the [content] of the message sent by the speaker.
 * @property role The role of the speaker who sends the message.
 * @property content The content of the message sent by the speaker.
 */
@Serializable
data class ImageGenerated(
    @SerialName("url")
    val url: String,
)
