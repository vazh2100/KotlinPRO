package repositories

import entities.Observer
import entities.User
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

val json = Json { ignoreUnknownKeys = true }

//чтобы запретить создавать любое количество объектов запрещаем использовать конструктор из вне
class UserRepository private constructor() {
    // 1. Companion object: constants and static-like members
    companion object {
        private var _instance: UserRepository? = null
        val LOCK = Any()
        fun instance(): UserRepository {
            _instance?.also { return it }
            synchronized(LOCK) {
                _instance?.also { return it }
                return UserRepository().also {
                    _instance = it
                }
            }
        }

    }

    // 2 Properties: initialized in constructors,  directly and getters
    private val userFile: File = File("users.json")

    private val _users: MutableList<User> = loadUsers()
    val users get() = _users.toList()

    //Зависимость от абстракций, а не от реализаций
    private val observers: MutableList<Observer<List<User>>> = mutableListOf()
    fun addObserver(observer: Observer<List<User>>) {
        observers.add(observer)
        observer.onChanged(_users)
    }

    //3 Initialization block: used for complex initialization
    init {
        println("Creating")
    }

    // Secondary constructor(s)
    // Overridden methods

    // Public methods
    fun saveUser(user: User) {
        val id = _users.maxOf { it.id } + 1
        user
            .copy(id = id)
            .also {
                _users.add(it)
            }
        notifyObservers()
    }

    fun deleteUser(id: Int) {
        _users.removeIf { it.id == id }
        notifyObservers()
    }

    fun saveChanges() {
        json
            .encodeToString(_users)
            .also {
                userFile.writeText(it)
            }

    }

    // Private methods
    private fun loadUsers(): MutableList<User> {
        val content = userFile
            .readText()
            .trim()
        return json.decodeFromString<MutableList<User>>(content)

    }

    private fun notifyObservers() {
        observers.forEach { it.onChanged(_users) }
    }

    // Nested classes: defined at the end for better readability
}




