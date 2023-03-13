package co.yml.ychat.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ImageGenerationsParamsDto(
    @SerialName("prompt")
    val prompt: String,
    @SerialName("n")
    val results: Int,
    @SerialName("size")
    val size: String,
    @SerialName("response_format")
    val responseFormat: String,
    @SerialName("user")
    val user: String,
)
