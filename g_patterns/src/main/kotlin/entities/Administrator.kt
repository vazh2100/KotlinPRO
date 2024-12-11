package entities

import repositories.UserRepository


class Administrator {
    private val userRepository = UserRepository.instance()

    fun work() {
        val operationTypes = OperationType.entries
        while (true) {
            print("Введите код операции:")
            for (type in operationTypes) {
                print("\n${type.ordinal} - ${type.title}")
                if (type.ordinal >= operationTypes.size - 1) print("\n:")
            }
            val operationCode = operationTypes[readln().toInt()]
            when (operationCode) {
                OperationType.EXIT -> {
                    userRepository.saveChanges()
                    break
                }

                OperationType.ADD_USER -> addUser()
                OperationType.DELETE_USER -> deleteUser()
            }
        }

    }


    private fun addUser() {
        print("Введите имя: ")
        val name = readln()
        print("Введите фамилию: ")
        val lastName = readln()
        print("Введите возраст ")
        val age = readln().toInt()
        val user = User(name, lastName, age)
        userRepository.saveUser(user)
    }

    private fun deleteUser() {
        print("Введите id работника для удаления: ")
        val id = readln().toInt()
        userRepository.deleteUser(id)
    }

}
