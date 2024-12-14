package repositories

import entities.ObservableDelegate
import entities.Observer
import entities.User
import kotlinx.serialization.encodeToString
import java.io.File

//чтобы запретить создавать любое количество объектов запрещаем использовать конструктор из вне
class UserRepositoryWithDelegate private constructor() {
    // 1. Companion object: constants and static-like members
    companion object {
        private var _instance: UserRepositoryWithDelegate? = null
        private val LOCK = Any()
        fun instance(): UserRepositoryWithDelegate {
            _instance?.also { return it }
            synchronized(LOCK) {
                _instance?.also { return it }
                return UserRepositoryWithDelegate().also {
                    _instance = it
                }
            }
        }

    }

    // 2 Properties: initialized in constructors,  directly and getters
    //Зависимость от абстракций, а не от реализаций
    private val userFile: File = File("users.json")
    private val _users: MutableList<User> = loadUsers()
    private val observableDelegate = ObservableDelegate<List<User>>(_users)


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
        notifyListeners()
    }

    fun deleteUser(id: Int) {
        _users.removeIf { it.id == id }
        notifyListeners()
    }

    fun saveChanges() {
        json
            .encodeToString(_users)
            .also {
                userFile.writeText(it)
            }

    }

    fun addOnUserChangeListener(listener: Observer<List<User>>) = observableDelegate.addObserver(listener)
    fun removeOnUserChangeListener(listener: Observer<List<User>>) = observableDelegate.removeObserver(listener)

    // Private methods
    private fun loadUsers(): MutableList<User> {
        val content = userFile
            .readText()
            .trim()
        return json.decodeFromString<MutableList<User>>(content)

    }

    private fun notifyListeners() = observableDelegate.notifyObservers(_users)

    // Nested classes: defined at the end for better readability
}




