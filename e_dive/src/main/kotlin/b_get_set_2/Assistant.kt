package b_get_set_2

import a_get_set_1.WorkerType

class Assistant : Worker {

    constructor(name: String, age: Byte, salary: Int) : super(name, age, WorkerType.ASSISTANT, salary)
    constructor(map: Map<String, String>) : super(map)

    override fun work() {
        print("I am bringing coffee...\n")
    }

}