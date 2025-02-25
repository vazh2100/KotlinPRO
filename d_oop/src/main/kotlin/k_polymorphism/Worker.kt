package k_polymorphism

open class Worker(val name: String, val age: Byte? = null) {

    open fun work() {
        print("I'm working.")
    }
}