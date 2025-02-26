package h_equals

fun main() {
    // т.к не определен метод equals то программа считает эти объекты разными
    val a = Test(5)
    val b = Test(5)
    println(a == b) //false
    println(a)
    println(b)
    // т.к переменные ссылаются на один и тот же объект, они будут равны
    val c = Test(5)
    val d = c
    println(c)
    println(d)
    println(c == d) //true
    // при изменении объекта через одну переменную, при обращении к другой переменной мы увидим
    // эти изменения
    println(c.number) //5
    println(d.number) //5
    d.number = 10
    println(c.number) // 10
    println(d.number) // 10
    // === сравнивает ссылаются ли переменные на один и тот же объект
    println(c === d) //true
    //у массивов метод equals не переопределен, а коллекций(list) переопределен
    val list1 = arrayOf("as", "bas")
    val list2 = arrayOf("as", "bas")
    println(list1 contentEquals list2)
    println(list1 == list2)
    val accountant = Accountant("Andrey", 30, 30000)
    accountant.work()

}