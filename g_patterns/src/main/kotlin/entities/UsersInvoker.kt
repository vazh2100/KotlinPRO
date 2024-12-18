package entities

import java.util.concurrent.LinkedBlockingDeque
import kotlin.concurrent.thread


//Реализация Invoker.
object UsersInvoker : Invoker<AdministratorCommand> {

    //Блокирующая очередь
    private val commands = LinkedBlockingDeque<Command>()

    init {
        thread {
            while (true) {
                // если в очереди нет элементов, то поток засыпает до тех пор, пока элемент не появится
                println("Waiting...")
                val command = commands.take()
                println("Выполняю команду: $command")
                command()
                println("Команда выполнена: $command")
            }
        }
    }

    override fun addCommand(command: AdministratorCommand) {
        commands.add(command)
    }
}

