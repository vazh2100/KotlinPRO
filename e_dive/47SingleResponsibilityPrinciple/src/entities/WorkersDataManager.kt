package entities

import java.io.File

//Repository - объект, который управляет данными
class WorkersDataManager {

    private val workersFile = File("workers.txt")

    fun saveWorker(worker: Worker) {
        workersFile.appendText(worker.toJson() + "\n")
    }

    fun deleteWorker(id: Int) {
        val workers = loadWorkers()
        try {
            workers.removeAt(id)
            workersFile.writeText("")
            for (worker in workers) {
                workersFile.appendText(worker.toJson() + "\n")
            }
        } catch (_: Throwable) {

        }
    }


    fun changeWorkerSalary(id: Int, salary: Int) {
        val workers = loadWorkers()
        workersFile.writeText("")
        for ((index, worker) in workers.withIndex()) {
            if (index == id) worker.salary = salary
            workersFile.appendText(worker.toJson() + "\n")
        }
    }

    fun loadWorkers(): MutableList<Worker> {
        if (!workersFile.exists()) workersFile.createNewFile()
        return workersFile.readLines().mapNotNull { if (it.isNotEmpty()) Worker.fromJson(it) else null }
            .toMutableList()
    }

}