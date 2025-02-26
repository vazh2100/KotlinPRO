package m_try_catch_nothing

import l_elvis_unit.WorkersDataManager

fun main() {
    try {
        val a = readln().toInt()
        val b = readln().toInt()
        val sum = a / b
        val list = listOf(0, 1, 2, 3, 4)
        list[0]
        println(sum)
        throw Throwable()
    } catch (e: NumberFormatException) {
        println("Неправильный ввод")
    } catch (e: ArithmeticException) {
        println("На ноль делить нельзя")
    } catch (e: ArrayIndexOutOfBoundsException) {
        println("Индекс за пределами коллекции")
    } catch (e: Throwable) {
        println(e.javaClass)
        println("Общая ошибка")
    } finally {
        println("Код который всегда выполняется")
    }
    // Директор необходим для работы программы. Так мы показываем другим программистам и пользователям, в чём дело
    val director = WorkersDataManager.findDirector() ?: throwDirectorIsRequired()
    director.age
///    TOD // возвращает Nothing, бросает исключение
}

///Указание на Nothing сообщает компилятору, что метод никогда не будет завершён, поэтому director не тип Any
//Тип Nothing автоматически является наследником всех классов
//Также это пометка для других программистов
fun throwDirectorIsRequired(): Nothing {
    throw IllegalStateException(
        "Директор необходим для работы программы. " + "Добавьте его в файл"
    )
}
