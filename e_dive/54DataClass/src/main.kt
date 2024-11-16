import entities.Assistant
import entities.Person
import entities.Worker

fun main() {

    val numbers = mutableSetOf(1, 2, 3, 3, 3, 5)
    numbers.add(6)
    numbers.add(3)
    for (number in numbers) {
        println(number) // дубликаты не были добавлены
    }


    val workers = mutableSetOf<Worker>()
    workers.add(Assistant("", 10, 10000))
    workers.add(Assistant("", 10, 10000))
    for (worker in workers) {
        println(worker) // при не переопределенном методе hashCode дубликаты добавляются, несмотря на то,
        //что equals переопределен
        //если определить hashCode то не добавляются
        //всегда добавляйте hashCode вместе с equals
    }

    val a = 5
    val b = 5
    println(a.hashCode()) //5
    println(b.hashCode()) //5
    println(b == a)

    val c = Assistant("", 10, 10000)
    val d = Assistant("", 10, 10000)
    println(c.hashCode()) //5
    println(d.hashCode()) //5
    println(c == d)


    val person1 = Person("aa", "..", 100, 50)
    val person2 = person1.copy()
    val person3 = person2.copy("assasin")
    val person4 = Person("aa", "...", 100, 50)
    val set = setOf(person1, person2, person3, person4)
    println(set)


}