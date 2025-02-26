package j_data_class

import a_get_set_1.WorkerType

abstract class Worker(
    open val id: Int,
    open val name: String,
    open val age: Byte,
    open val salary: Int,
    val workerType: WorkerType,
) {

    abstract fun work()

    fun toJson(): String {
        return "id=$id;name=$name;age=$age;workerType=$workerType;salary=$salary"
    }

    ///Можно иметь абстрактную версию метода, только с другим количеством параметров, но такую копию нужно обязательно
    //переопределять в потомках
    abstract fun copy(
        age: Byte = this.age,
        salary: Int = this.salary,
    ): Worker


    companion object {
        fun fromJson(json: String): Worker {
            val map = json.split(";").map { it.split("=") }.associate { it[0] to it[1] }
            val type = map["workerType"]?.let { WorkerType.valueOf(it) }
            return when (type) {
                WorkerType.DIRECTOR -> Director.fromJson(map)
                WorkerType.ASSISTANT -> Assistant.fromJson(map)
                WorkerType.CONSULTANT -> Consultant.fromJson(map)
                WorkerType.ACCOUNTANT -> Accountant.fromJson(map)
                else -> throw Exception("")
            }
        }
    }
}