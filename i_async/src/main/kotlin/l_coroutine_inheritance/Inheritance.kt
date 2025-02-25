package l_coroutine_inheritance

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.time.measureTime

private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    scope.launch {
        val time = measureTime {
            loadMovies().also { println(it) }
        }
        println(time)
    }

}

// wrong variant
private suspend fun loadMovies2(): List<String> {
    return loadMoviesIds().map {
        scope.async {
            loadMovieById(it)
        }
    }.awaitAll()
}

private suspend fun loadMoviesIds(): List<Int> = withContext(Dispatchers.IO) {
    delay(3000)
    (0..100).toList()
}

private suspend fun loadMovieById(id: Int): String = withContext(Dispatchers.IO) {
    delay(100)
    "Movie: $id"
}

private suspend fun loadMovies(): List<String> = withContext(Dispatchers.IO) {
    val ids = loadMoviesIds()
    val movies = coroutineScope { ids.map { async { loadMovieById(it) } }.awaitAll() }
    movies
}