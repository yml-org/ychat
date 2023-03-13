package co.yml.ychat

import co.yml.ychat.entrypoint.features.ChatCompletions
import co.yml.ychat.entrypoint.features.Completion
import co.yml.ychat.entrypoint.features.ImageGenerations
import co.yml.ychat.entrypoint.impl.YChatImpl
import kotlin.jvm.JvmStatic
import kotlin.jvm.Volatile
import kotlin.native.concurrent.ThreadLocal

/**
 * YChat aims to abstract all API call logic from ChatGPT for multiple platforms.
 *
 * To initialize the SDK, just call the static method ChatGpt.create(apiKey), informing the api key.
 * See [this](https://beta.openai.com/docs/api-reference/authentication) for more details on how
 * to get the api key.
 */
interface YChat {

    /**
     * The completions api can be used for a wide variety of tasks. You input some text as a
     * prompt, and the model will generate a text completion that attempts to match whatever
     * context or pattern you gave it. For example, if you give the API the prompt, "As Descartes
     * said, I think, therefore", it will return the completion " I am" with high probability.
     *
     * You can configure the parameters of the completion before executing it. Example:
     * ```
     * val result = YChat.create(apiKey).completion()
     *      .setInput("As Descartes said, I think, therefore")
     *      .setMaxTokens(1024)
     *      .set...
     *      .execute()
     * ```
     */
    fun completion(): Completion

    /**
     * The chatCompletions api generates a list of chat completions for the given input message.
     * It uses machine learning algorithms to generate responses that match the context or pattern
     * provided in the input message.
     *
     * You can configure various parameters to customize the chat completions, such as the model to use,
     * the number of results to generate, and the maximum number of tokens allowed for the generated answer.
     *
     * Example usage:
     * ```
     * val result = YChat.create(apiKey).chatCompletions()
     *      .setModel("gpt-3.5-turbo")
     *      .setResults(3)
     *      .setMaxTokens(1024)
     *      .execute("Hello, how are you?")
     * ```
     * This would generate three chat completion strings based on the input message "Hello, how are you?"
     * using the "gpt-3.5-turbo" model, with a maximum of 1024 tokens allowed for each generated answer.
     *
     * You can also use the `addMessage` method to provide additional context or information to the API,
     * which can be used to restrict the generated responses to a certain topic or context.
     *
     * Example usage:
     * ```
     * val result = YChat.create(apiKey).chatCompletions()
     *      .setModel("gpt-3.5-turbo")
     *      .setResults(3)
     *      .setMaxTokens(1024)
     *      .addMessage(
     *          role = "assistant",
     *          content = "You are a helpful assistant that only answers questions related to fitness"
     *      )
     *      .execute("What is the best exercise for building muscle?")
     * ```
     * This would generate three chat completion strings based on the input message "What is the
     * best exercise for building muscle?", using the "gpt-3.5-turbo" model, with a maximum of 1024
     * tokens allowed for each generated answer. The `addMessage` method is used to provide context
     * to the API, restricting the generated responses to questions related to fitness, since the
     * assistant is set to only answer questions related to that topic.
     *
     * @return A new instance of the `ChatCompletions` class.
     */
    fun chatCompletions(): ChatCompletions

    fun imageGenerations(): ImageGenerations

    /**
     * Callback is an interface used for handling the results of an operation.
     * It provides two methods, `onSuccess` and `onError`, for handling the success
     * and error cases of the operation.
     *
     * @param T The type of the result of the operation.
     */
    interface Callback<in T> {

        /**
         * This method is called when the operation has been successful.
         * @param result The result of the operation.
         */
        fun onSuccess(result: T)

        /**
         * This method is called when the operation has failed.
         * @param throwable The exception thrown during the operation.
         */
        fun onError(throwable: Throwable)
    }

    @ThreadLocal
    companion object {

        @Volatile
        private var instance: YChat? = null

        @JvmStatic
        fun create(apiKey: String): YChat {
            return instance ?: YChatImpl(apiKey).also { instance = it }
        }
    }
}
