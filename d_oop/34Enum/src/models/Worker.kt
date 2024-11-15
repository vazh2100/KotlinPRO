package models

open class Worker(val name: String, val age: Byte? = null) {
    open fun work() {
        print("I'am working.")
    }
}