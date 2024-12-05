import entities.Counter
import repositories.UserRepository
import kotlin.concurrent.thread

fun main() {
    val counter = Counter()

    val a = thread {
        repeat(100_000) {
            counter.increment()
        }
    }

    val b = thread {
        repeat(100_000) {
            counter.increment()
        }
    }

    a.join()
    b.join()
    println(counter.i)


    return
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

