package j_details

fun main() {
    val zombie = Zombie("Zombie")
    val human = Human("Human")
    zombie.move()
    zombie.fight()
    human.move()
    human.fight()
    val premiumZombie = PremiumPlayer(zombie)
    val flyingHuman = FlyingPlayer(human)
    premiumZombie.callForHelp()
    flyingHuman.fly()
    val mutableList = mutableListOf(10)
    val loggingMutableList = LoggingMutableList(mutableList)
    loggingMutableList.add(11)
}

interface Player {
    fun move()
    fun fight()
}

data class Zombie(val name: String) : Player {
    override fun move() {
        println("I am moving very slowly")
    }

    override fun fight() {
        println("I am eating human")
    }
}

data class Human(val name: String) : Player {
    override fun move() {
        println("I am running very fast")
    }

    override fun fight() {
        println("I am shooting a gun")
    }
}

// Паттерн Wrapper
data class PremiumPlayer(val player: Player) : Player by player {
//    override fun move() = player.move()
//    override fun fight() = player.fight()

    fun callForHelp() {
        println("I am calling for help")
    }
}

data class FlyingPlayer(val player: Player) : Player by player {
//    override fun move() = player.move()
//    override fun fight() = player.fight()

    fun fly() {
        println("I am flying")
    }
}

class LoggingMutableList<T>(private val list: MutableList<T>) : MutableList<T> by list {
    override fun add(element: T): Boolean {
        println("Added $element")
        return list.add(element)
    }
}
