package c_dictionary

import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URI
import java.util.concurrent.Executors

internal object Repository {

    private val json = Json {
        ignoreUnknownKeys = true
    }
    private const val HEADER = "X-API-KEY"
    private const val URL = "https://api.api-ninjas.com/v1/dictionary?word="
    private const val API_KEY = "XgXjpbBovmyzDrgiwcYHQA==gL38pRmTsPhEBctN"

    internal suspend fun loadDefinition(word: String): String = withContext(Dispatchers.IO) {
        val url = URI(URL + word).toURL()
        val connection = url.openConnection() as HttpURLConnection
        connection.addRequestProperty(HEADER, API_KEY)
        // байты -> символы -> текст
        val response = connection.inputStream.bufferedReader().readText()
        json.decodeFromString<Definition>(response).definition.also {
            connection.disconnect()
        }
    }
}

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    scope.launch {
        Repository.loadDefinition("mother").let(::println)
    }
}

