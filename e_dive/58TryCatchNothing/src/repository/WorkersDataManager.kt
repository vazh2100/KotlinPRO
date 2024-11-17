package repository

import entities.Assistant
import entities.Director
import entities.Worker
import java.io.File


object WorkersDataManager {

    private val workersFile = File("workers.txt")

    //Мы создаём backing field - оно доступно для изменения и чтения только внутри класса
    private val _workers = loadWorkers()

    //Мы создаём публичное поле, которое можно только читать и нельзя ничего добавлять
    val workers: Set<Worker>
        //Несмотря на то, что мы создаём новую коллекцию, эта коллекция ссылается на те же самые объекты,
        //что и в старой коллекции. Если мы удалим или добавим в новую коллекцию элемент, коллекции будут различаться.
        //Но если мы изменим элемент в новой колекции(его поле var), то в старой коллекции тоже изменится этот элемент
        get() = _workers.toSet()
    //В данном случае можно просто привести к List не создавая новую коллекцию
//        get() = _workers

    fun saveWorker(worker: Worker) = _workers.add(worker)


    fun saveWorkers() {
        val builder = StringBuilder()
        for (worker in _workers) {
            builder.append(worker.toJson() + "\n")
        }
        workersFile.writeText(builder.toString())
    }

    fun deleteWorker(id: Int) = _workers.removeIf {
        it.id == id
    }


    fun changeWorkerSalary(
        id: Int,
        salary: Int
    ) {
        for (worker in _workers) {
            if (worker.id == id) {
                if (salary < worker.salary) {
                    println("Зарплата меньше, чем была. Мы не уменьшаем зарплаты")
                } else {
                    _workers.remove(worker)
                    _workers.add(worker.copy(salary = salary))
                }
                break
            }
        }
    }

    fun changeWorkerAge(
        id: Int,
        age: Byte
    ) {
        for (worker in _workers) {
            if (worker.id == id) {
                _workers.remove(worker)
                _workers.add(worker.copy(age = age))
                break
            }
        }
    }

    fun findAssistant(): Assistant? {
//        _workers.find { it is Assistant }
        for (worker in _workers) {
            if (worker is Assistant) return worker
        }
        return null
    }

    fun findDirector(): Director? {
//        _workers.find { it is Director }
        for (worker in _workers) {
            if (worker is Director) return worker
        }
        return null
    }

    private fun loadWorkers(): MutableSet<Worker> {
        if (!workersFile.exists()) workersFile.createNewFile()
        return workersFile.readLines().mapNotNull { if (it.isNotEmpty()) Worker.fromJson(it) else null }.toMutableSet()
    }


}