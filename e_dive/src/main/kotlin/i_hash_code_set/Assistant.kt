package i_hash_code_set

import a_get_set_1.WorkerType

class Assistant : Worker {

    constructor(name: String, age: Byte, salary: Int) : super(name, age, WorkerType.ASSISTANT, salary)
    constructor(map: Map<String, String>) : super(map)

    override fun copy(name: String, age: Byte, salary: Int): Assistant {
        return Assistant(name, age, salary)
    }

    override fun work() {
        print("I am bringing coffee...\n")
    }

}