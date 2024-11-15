package entities



abstract class Worker {
    internal val name: String
    internal val age: Byte
    private val workerType: WorkerType

//если мы напишем такой код, то он не откомпилируется, потому что котлин создаст getSalary() и setSalary
    // и произойдёт дублирование. Если добавить private дублирования не произойдёт и код скомпилируется
//    var salary = 15000
//    fun getSalary():Int = salary
//    fun setSalary(salary: Int) {
//        if (salary < this.salary) {
//            println("Зарплата меньше, чем была. Мы не уменьшаем зарплаты")
//        } else {
//            this.salary = salary
//        }
//    }

    //для var создаётся getter и setter, а для val только getter
    // в примере ниже мы переопределяем геттер и сеттер, чтобы поле можно было изменять везде оно public
    var salary = 15000
        set(value) {
            if (value < field) {
                println("Зарплата меньше, чем была. Мы не уменьшаем зарплаты")
            } else {
                field = salary
            }
        }

    constructor(name: String, age: Byte, workerType: WorkerType) {
        this.name = name
        this.age = age
        this.workerType = workerType
    }

    constructor(map: Map<String, String>) : this(
        map["name"]!!,
        map["age"]!!.toByte(),
        WorkerType.valueOf(map["workerType"]!!),
    ) {
        salary = map["salary"]!!.toInt()
    }

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