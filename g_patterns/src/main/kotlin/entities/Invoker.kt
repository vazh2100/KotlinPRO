package entities

//Второй участник паттерна Command. Тот кто добавляет команды и управляет тем, как управлять всеми поступившими командами
interface Invoker {
    fun addCommand(command: Command)
}