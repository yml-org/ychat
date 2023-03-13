package co.yml.ychat.domain.usecases

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.domain.mapper.toImageGenerated
import co.yml.ychat.domain.mapper.toImageGenerationsParamsDto
import co.yml.ychat.domain.model.ImageGenerated
import co.yml.ychat.domain.model.ImageGenerationsParams

internal data class ImageGenerationsUseCase(private val chatGptApi: ChatGptApi) {

    suspend fun requestImageGenerations(params: ImageGenerationsParams): List<ImageGenerated> {
        val requestDto = params.toImageGenerationsParamsDto()
        val response = chatGptApi.imageGenerations(requestDto)
        return response.getBodyOrThrow().toImageGenerated()
    }
}
