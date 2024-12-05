package entities

class Counter {
    val lock = Any() //intrinsic lock, встроенный замок, Mutex
    var i = 0

    fun increment(): Int {
        //блок синхронизации
        synchronized(lock) { //критическая секция
            return i++
        }
    }
}