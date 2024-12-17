package entities

import java.util.concurrent.LinkedBlockingDeque
import kotlin.concurrent.thread


//Реализация Invoker.
object UsersInvoker : Invoker {

    //Блокирующая очередь
    private val commands = LinkedBlockingDeque<Command>()

    init {
        thread {
            while (true) {
                // если в очереди нет элементов, то поток засыпает до тех пор, пока элемент не появится
                val command = commands.take()
                command()
            }
        }
    }

    override fun addCommand(command: Command) {
        commands.add(command)
    }
}