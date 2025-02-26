package a_get_set_1

class Assistant : Worker {

    constructor(name: String, age: Byte) : super(name, age, WorkerType.ASSISTANT)
    constructor(map: Map<String, String>) : super(map)

    override fun work() {
        print("I am bringing coffee...\n")
    }
}