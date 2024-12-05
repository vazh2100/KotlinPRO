import repositories.UserRepository
import kotlin.concurrent.thread

fun main() {
    thread { UserRepository.instance().users }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
    thread { UserRepository.instance() }
}


//|Функция| Преобразовать | Конфигурировать | Возвращает          |
//|       |  объект?      |объект или фун?  |                     |
//|-------|---------------|-----------------|---------------------|
//| let   |     ✅        |       ❌        | Результат выражения |
//| apply |     ❌        |       ✅        | Сам объект          |
//| also  |     ❌        |       ❌        | Сам объект          |
//| run   |     ✅        |       ✅        | Результат выражения |
//| with  |     ✅        |       ✅        | Результат выражения |

