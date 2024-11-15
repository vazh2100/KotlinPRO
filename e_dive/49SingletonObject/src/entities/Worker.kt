package entities



abstract class Worker {
    internal val name: String
    internal val age: Byte
    private val workerType: WorkerType

    //первый случай, когда мы используем геттеры и сеттеры - это когда нам нужно, чтобы значения не прост
    //устанавливались, а выполнялась дополнительная логика
    var salary: Int = 0 // не используется
        internal set(value) {
            if (value < field) {
                println("Зарплата меньше, чем была. Мы не уменьшаем зарплаты")
            } else {
                field = value
            }
        }

    constructor(name: String, age: Byte, workerType: WorkerType, salary: Int) {
        this.name = name
        this.age = age
        this.workerType = workerType
        this.salary = salary
    }

    constructor(map: Map<String, String>) : this(
        name = map["name"]!!,
        age = map["age"]!!.toByte(),
        workerType = WorkerType.valueOf(map["workerType"]!!),
        salary = map["salary"]!!.toInt()
    )

    abstract fun work()

    fun toJson(): String {
        return "name=$name;age=$age;workerType=$workerType;salary=$salary"
    }

    override fun toString(): String = toJson()


    companion object {
        fun fromJson(json: String): Worker {
            val map = json.split(";").map { it.split("=") }.associate { it[0] to it[1] }
            val type = map["workerType"]?.let { WorkerType.valueOf(it) }
            return when (type) {
                WorkerType.DIRECTOR -> Director(map)
                WorkerType.ASSISTANT -> Assistant(map)
                WorkerType.CONSULTANT -> Consultant(map)
                WorkerType.ACCOUNTANT -> Accountant(map)
                else -> throw Exception("")
            }
        }
    }
}