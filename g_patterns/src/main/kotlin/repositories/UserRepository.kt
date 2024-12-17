package repositories

import entities.MutableObservable
import entities.Observable
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
    private val userList = loadUsers()
    private val _users = MutableObservable(userList.toList())
    val users: Observable<List<User>> = _users
    private val _oldestUser = MutableObservable(userList.maxBy { it.age })
    val oldestUser: Observable<User> = _oldestUser


    //3 Initialization block: used for complex initialization
    init {
        println("Creating")
    }

    // Secondary constructor(s)
    // Overridden methods

    // Public methods


    fun saveUser(user: User) {
        Thread.sleep(2000)
        val id = userList.maxOf { it.id } + 1
        user
            .copy(id = id)
            .also {
                _users.currentValue = userList.apply { add(it) }
                if (it.age > _oldestUser.currentValue.age) {
                    _oldestUser.currentValue = it
                }
            }
    }

    fun deleteUser(id: Int) {
        val user = userList.find { it.id == id } ?: return
        Thread.sleep(2000)
        _users.currentValue = userList.apply { remove(user) }
        if (user == _oldestUser.currentValue) {
            _oldestUser.currentValue = userList.maxBy { it.age }
        }


    }

    fun saveChanges() {
        json
            .encodeToString(userList)
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
}




