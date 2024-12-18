package entities

import repositories.UserRepository

///Администратор может выполнять только те команды, которые реализованы в sealed interface
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
                    UsersInvoker.addCommand(AdministratorCommand.SaveChanges(userRepository))
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
        // Администратор является клиентом в паттерне Command
        ///Администратор может выполнять только те команды, которые реализованы в sealed interface
        UsersInvoker.addCommand(AdministratorCommand.SaveUser(userRepository, user))

    }

    private fun deleteUser() {
        print("Введите id работника для удаления: ")
        val id = readln().toInt()
        UsersInvoker.addCommand(AdministratorCommand.DeleteUser(userRepository, id))

    }

}
