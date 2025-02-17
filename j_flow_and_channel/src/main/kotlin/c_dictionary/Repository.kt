package c_dictionary

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URI

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
        json.decodeFromString<Definition>(response).replace().also {
            connection.disconnect()
        }
    }

    private fun Definition.replace(): String {
        return this.definition.plus("10. Andrey").replace(Regex(""" (\d+\. )"""), "\n\n$1").trim()
    }
}
