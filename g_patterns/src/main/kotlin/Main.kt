import entities.Administrator
import ui.Display

fun main() {
    Display().show()
    Administrator().work()
}


//|Функция| Преобразовать | Конфигурировать | Возвращает          |
//|       |  объект?      |объект или фун?  |                     |
//|-------|---------------|-----------------|---------------------|
//| let   |     ✅        |       ❌        | Результат выражения |
//| apply |     ❌        |       ✅        | Сам объект          |
//| also  |     ❌        |       ❌        | Сам объект          |
//| run   |     ✅        |       ✅        | Результат выражения |
//| with  |     ✅        |       ✅        | Результат выражения |

