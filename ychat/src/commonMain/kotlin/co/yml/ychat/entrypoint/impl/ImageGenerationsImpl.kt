package co.yml.ychat.entrypoint.impl

import co.yml.ychat.YChat
import co.yml.ychat.domain.model.ImageGenerated
import co.yml.ychat.domain.model.ImageGenerationsParams
import co.yml.ychat.domain.usecases.ImageGenerationsUseCase
import co.yml.ychat.entrypoint.features.ImageGenerations
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class ImageGenerationsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val imageGenerationsUseCase: ImageGenerationsUseCase,
) : ImageGenerations {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private var params: ImageGenerationsParams = ImageGenerationsParams()

    override fun setResults(results: Int): ImageGenerations {
        params.results = results
        return this
    }

    override fun setSize(size: String): ImageGenerations {
        params.size = size
        return this
    }

    override fun setResponseFormat(responseFormat: String): ImageGenerations {
        params.responseFormat = responseFormat
        return this
    }

    override suspend fun execute(prompt: String): List<ImageGenerated> {
        params.prompt = prompt
        return imageGenerationsUseCase.requestImageGenerations(params)
    }

    override fun execute(prompt: String, callback: YChat.Callback<List<String>>) {
        scope.launch {
            kotlin.runCatching { execute(prompt) }
                .onSuccess { it -> callback.onSuccess(it.map { it.url }) } // fix here
                .onFailure { callback.onError(it) }
        }
    }


}
