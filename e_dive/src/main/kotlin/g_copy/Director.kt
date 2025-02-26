package g_copy

import a_get_set_1.WorkerType

class Director : Worker {

    constructor(name: String, age: Byte, salary: Int) : super(
        name, age, WorkerType.DIRECTOR, salary
    )

    constructor(map: Map<String, String>) : super(map)

    override fun copy(name: String, age: Byte, salary: Int): Director {
        return Director(name, age, salary)
    }

    override fun work() {
        print("I am drinking coffee...\n")
    }

}