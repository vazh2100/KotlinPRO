package entities

import java.io.File

//Синглтон object - можно применять, если нет конструктора
// Может быть потомком других классов, но не может быть родителем
object WorkersDataManager {

    private val workersFile = File("workers.txt")
    val workers = loadWorkers()

    fun saveWorker(worker: Worker) = workers.add(worker)


    fun saveWorkers() {
        val builder = StringBuilder()
        for (worker in workers) {
            builder.append(worker.toJson() + "\n")
        }
        workersFile.writeText(builder.toString())
    }

    fun deleteWorker(id: Int) = workers.removeAt(id)


    fun changeWorkerSalary(id: Int, salary: Int) {
        for ((index, worker) in workers.withIndex()) {
            if (index == id) worker.salary = salary
        }
    }

    private fun loadWorkers(): MutableList<Worker> {
        if (!workersFile.exists()) workersFile.createNewFile()
        return workersFile.readLines().mapNotNull { if (it.isNotEmpty()) Worker.fromJson(it) else null }.toMutableList()
    }


}