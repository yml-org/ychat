package co.yml.ychat.entrypoint.features

import co.yml.ychat.YChat
import co.yml.ychat.data.exception.ChatGptException
import co.yml.ychat.domain.model.ImageGenerated
import kotlin.coroutines.cancellation.CancellationException

interface ImageGenerations {

    fun setResults(results: Int): ImageGenerations

    fun setSize(size: String): ImageGenerations

    fun setResponseFormat(responseFormat: String): ImageGenerations

    @Throws(CancellationException::class, ChatGptException::class)
    suspend fun execute(prompt: String): List<ImageGenerated>

    fun execute(prompt: String, callback: YChat.Callback<List<String>>)
}
