package q_control_work

open class Worker {

    internal val name: String
    protected val age: Byte
    private val workerType: WorkerType

    constructor(name: String, age: Byte, workerType: WorkerType) {
        this.name = name
        this.age = age
        this.workerType = workerType
    }

    constructor(map: Map<String, String>) : this(
        map["name"]!!,
        map["age"]!!.toByte(),
        WorkerType.valueOf(map["workerType"]!!),
    )

    open fun work() {
        print("I'm working.")
    }

    open fun toJson(): String {
        return "name=$name;age=$age;workerType=$workerType"
    }

    override fun toString(): String {
        return toJson()
    }

    companion object {

        fun fromJson(json: String): Worker {
            val map = json.split(";").map { it.split("=") }.associate { it[0] to it[1] }
            val type = map["workerType"]?.let { WorkerType.valueOf(it) }
            return when (type) {
                WorkerType.DIRECTOR -> Director(map)
                WorkerType.ASSISTANT -> Assistant(map)
                WorkerType.CONSULTANT -> Consultant(map)
                WorkerType.ACCOUNTANT -> Accountant(map)
                null -> Worker(map)
            }
        }
    }
}