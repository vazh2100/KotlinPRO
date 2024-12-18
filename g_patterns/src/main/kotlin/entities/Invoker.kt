package entities

//Второй участник паттерна Command. Тот кто добавляет команды и управляет тем, как управлять всеми поступившими командами
interface Invoker<T : Command> {
    fun addCommand(command: T)
}