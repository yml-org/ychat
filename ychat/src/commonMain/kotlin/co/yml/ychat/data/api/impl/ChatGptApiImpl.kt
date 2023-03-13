package co.yml.ychat.data.api.impl

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.data.dto.ChatCompletionParamsDto
import co.yml.ychat.data.dto.ChatCompletionsDto
import co.yml.ychat.data.dto.CompletionDto
import co.yml.ychat.data.dto.CompletionParamsDto
import co.yml.ychat.data.dto.ImageGenerationsDto
import co.yml.ychat.data.dto.ImageGenerationsParamsDto
import co.yml.ychat.data.infrastructure.ApiExecutor
import co.yml.ychat.data.infrastructure.ApiResult
import io.ktor.http.HttpMethod

internal class ChatGptApiImpl(private val apiExecutor: ApiExecutor) : ChatGptApi {

    override suspend fun completion(paramsDto: CompletionParamsDto): ApiResult<CompletionDto> {
        return apiExecutor
            .setEndpoint("v1/completions")
            .setHttpMethod(HttpMethod.Post)
            .setBody(paramsDto)
            .execute()
    }

    override suspend fun chatCompletions(paramsDto: ChatCompletionParamsDto): ApiResult<ChatCompletionsDto> {
        return apiExecutor
            .setEndpoint("v1/chat/completions")
            .setHttpMethod(HttpMethod.Post)
            .setBody(paramsDto)
            .execute()
    }

    override suspend fun imageGenerations(paramsDto: ImageGenerationsParamsDto): ApiResult<ImageGenerationsDto> {
        return apiExecutor
            .setEndpoint("v1/images/generations")
            .setHttpMethod(HttpMethod.Post)
            .setBody(paramsDto)
            .execute()
    }
}
