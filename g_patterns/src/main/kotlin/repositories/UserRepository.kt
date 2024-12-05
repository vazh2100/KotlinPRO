package repositories

import entities.User
import kotlinx.serialization.json.Json
import java.io.File

val json = Json { ignoreUnknownKeys = true }


//чтобы запретить создавать любое количество объектов запрещаем использовать конструктор из вне
class UserRepository private constructor() {

    init {
        println("Creating")
    }

    private val userFile: File = File("users.json")

    private val _users: MutableList<User> = loadUsers()
    val users get() = _users.toList()


    private fun loadUsers(): MutableList<User> {
        val content = userFile
            .readText()
            .trim()
        return json.decodeFromString(content)

    }


    companion object {
        private var _instance: UserRepository? = null
        val LOCK = Any()
        fun instance(): UserRepository {
            synchronized(LOCK) {
                if (_instance == null) _instance = UserRepository()
                return _instance!!
            }
        }
    }


}