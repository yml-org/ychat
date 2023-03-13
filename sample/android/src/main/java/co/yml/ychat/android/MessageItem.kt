package co.yml.ychat.android

data class MessageItem(
    val message: String,
    val isOut: Boolean,
    val url: String? = null
)