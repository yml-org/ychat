package co.yml.ychat.di.module

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.data.api.impl.ChatGptApiImpl
import co.yml.ychat.data.infrastructure.ApiExecutor
import co.yml.ychat.data.storage.ChatLogStorage
import co.yml.ychat.di.provider.NetworkProvider
import co.yml.ychat.domain.usecases.ChatCompletionsUseCase
import co.yml.ychat.domain.usecases.CompletionUseCase
import co.yml.ychat.domain.usecases.ImageGenerationsUseCase
import co.yml.ychat.entrypoint.features.ChatCompletions
import co.yml.ychat.entrypoint.features.Completion
import co.yml.ychat.entrypoint.features.ImageGenerations
import co.yml.ychat.entrypoint.impl.ChatCompletionsImpl
import co.yml.ychat.entrypoint.impl.CompletionImpl
import co.yml.ychat.entrypoint.impl.ImageGenerationsImpl
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

internal class LibraryModule(private val apiKey: String) {

    fun modules(): List<Module> =
        entrypointModule + domainModule + dataModule + platformModule()

    private val entrypointModule = module {
        factory<Completion> { CompletionImpl(Dispatchers.Default, get()) }
        factory<ChatCompletions> { ChatCompletionsImpl(Dispatchers.Default, get()) }
        factory<ImageGenerations> { ImageGenerationsImpl(Dispatchers.Default, get()) }
    }

    private val domainModule = module {
        factory { CompletionUseCase(get(), get()) }
        factory { ChatCompletionsUseCase(get()) }
        factory { ImageGenerationsUseCase(get()) }
    }

    private val dataModule = module {
        single { NetworkProvider.provideHttpClient(get(), apiKey) }
        single { ChatLogStorage() }
        factory { ApiExecutor(get()) }
        factory<ChatGptApi> { ChatGptApiImpl(get()) }
    }
}

internal expect fun platformModule(): Module
