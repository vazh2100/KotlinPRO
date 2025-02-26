package a_get_set_1

class Director : Worker {

    constructor(name: String, age: Byte) : super(name, age, WorkerType.DIRECTOR)
    constructor(map: Map<String, String>) : super(map)

    override fun work() {
        print("I am drinking coffee...\n")
    }

}