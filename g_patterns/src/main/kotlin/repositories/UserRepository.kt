package repositories

import entities.MutableObservable
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
        private val LOCK = Any()
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
    //Зависимость от абстракций, а не от реализаций
    private val userFile: File = File("users.json")
    private val _users: MutableList<User> = loadUsers()
    val users = MutableObservable<List<User>>(_users)
    private var _oldestUser = _users.maxBy { it.age }
    val oldestUser = MutableObservable(_oldestUser)


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
        notifyUsers()
        if (user.age > _oldestUser.age) {
            _oldestUser = user
            notifyOldestUser()
        }
    }

    fun deleteUser(id: Int) {
        val user = _users.find { it.id == id } ?: return
        _users.remove(user)
        notifyUsers()
        if (user == oldestUser.currentValue) {
            _oldestUser = _users.maxBy { it.age }
            notifyOldestUser()
        }
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

    private fun notifyUsers() = users.notifyObservers(_users)
    private fun notifyOldestUser() = oldestUser.notifyObservers(_oldestUser)

    // Nested classes: defined at the end for better readability
}




