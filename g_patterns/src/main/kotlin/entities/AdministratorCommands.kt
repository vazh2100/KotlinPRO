package entities

import repositories.UserRepository


///sealed запрещает создание анонимных объектов/классов и ограничивает число реализаций на уровне пакета
sealed interface AdministratorCommand : Command {

    //data class выбран потому что в логах UserInvoker теперь видно, что за команда выполняется
    data class SaveUser(
        val userRepository: UserRepository,
        val user: User,
    ) : AdministratorCommand {
        override fun invoke() {
            userRepository.saveUser(user)
        }
    }

    //класс реализации команды должен содержать всё необходимое для выполнения команды
    data class DeleteUser(
        val userRepository: UserRepository,
        val id: Int,
    ) : AdministratorCommand {
        override fun invoke() {
            userRepository.deleteUser(id)
        }
    }

    data class SaveChanges(
        val userRepository: UserRepository,
    ) : AdministratorCommand {
        override fun invoke() {
            userRepository.saveChanges()
        }
    }
}



