package n_with_apply

fun main() {
    withExample()
    applyExample()
}

fun withExample() {
    val a = with(mutableListOf<Int>()) {
        while (true) {
            readln()
                .toInt()
                .takeIf { it != 0 }
                ?.also { add(it) } ?: break
        }
        this
    }.forEach { println(it) }

}

fun applyExample() {
    mutableListOf<Int>()
        .apply {
            while (true) {
                readln()
                    .toInt()
                    .takeIf { it != 0 }
                    ?.also { add(it) } ?: break
            }
        }
        .forEach {
            println(it)
        }

}






