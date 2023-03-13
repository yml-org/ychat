package co.yml.ychat.entrypoint.impl

import co.yml.ychat.YChat
import co.yml.ychat.di.module.LibraryModule
import co.yml.ychat.entrypoint.features.ChatCompletions
import co.yml.ychat.entrypoint.features.Completion
import co.yml.ychat.entrypoint.features.ImageGenerations
import org.koin.core.KoinApplication

internal class YChatImpl(apiKey: String) : YChat {

    val koinApp = KoinApplication.init()

    init {
        val modules = LibraryModule(apiKey).modules()
        koinApp.modules(modules)
    }

    override fun completion(): Completion {
        return koinApp.koin.get()
    }

    override fun chatCompletions(): ChatCompletions {
        return koinApp.koin.get()
    }

    override fun imageGenerations(): ImageGenerations {
        return koinApp.koin.get()
    }
}
