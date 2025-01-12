package entities.set

class MyTreeSet<T : Comparable<T>> : MyMutableSet<T> {


    private var root: Node<T>? = null


    override fun iterator(): MutableIterator<T> {
        TODO("Not yet implemented")
    }

    override val size: Int = 0

    override fun add(element: T): Boolean {
        val newElement = Node(element)
        if (root == null) {
            root = newElement
            return true
        } else {
            var current = root
            while (current != null) {
                if (element == current.value) {
                    return false
                } else if (element > current.value) {
                    if (current.rightChild == null) {
                        current.rightChild = newElement
                        current.rightChild?.parent = current
                    } else {
                        current = current.rightChild
                    }
                } else {
                    if (current.leftChild == null) {
                        current.leftChild = newElement
                        current.leftChild?.parent = current
                    } else {
                        current = current.leftChild
                    }
                }
            }
            return true
        }
    }

    override fun remove(element: T): Boolean {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun contains(element: T): Boolean {
        TODO("Not yet implemented")
    }

    fun printTree() {
        root?.printNode()
    }

//    fun printTree(root: Node<T>? = this.root, indent: String = "", isRight: Boolean = true) {
//        if (root == null) {
//            println("$indent${if (isRight) "└──" else "┌──"} null")
//            return
//        }
//
//        println("$indent${if (isRight) "└──" else "┌──"} ${root.value}")
//        printTree(root.leftChild, indent + if (isRight) "    " else "│   ", false)
//        printTree(root.rightChild, indent + if (isRight) "    " else "│   ", true)
//    }


    class Node<T>(val value: T) {
        var parent: Node<T>? = null
        var leftChild: Node<T>? = null
        var rightChild: Node<T>? = null

        var begin = 0
        var index = 0

        override fun toString(): String {
            return value.toString()
        }

        private fun sizeAndLevels(): Pair<Int, List<List<Node<T>>>> {
            val levels = mutableListOf<List<Node<T>>>()

            var mutableList = mutableListOf<Node<T>>()
            this.let {
                mutableList.add(it)
                levels.add(mutableList)
            }
            var level = 0
            while (mutableList.isNotEmpty()) {
                mutableList = mutableListOf()
                for (node in levels[level]) {
                    node.leftChild?.let {
                        mutableList.add(it)
                    }
                    node.rightChild?.let {
                        mutableList.add(it)
                    }
                }
                if (mutableList.isNotEmpty()) {
                    levels.add(mutableList)
                    level++
                }
            }
            return levels.sumOf { it.size } * 2 + 1 to levels
        }

        fun printNode() {
            val (size, levels) = sizeAndLevels()
            // создаём полотно
            val canvas = levels.map {
                val string = StringBuilder(size)
                repeat(size) {
                    string.append(' ')
                }
                string
            }
            // Заменяем точки полотна на ноды
            for ((index, level) in levels.withIndex()) {
                // устанавливаем root индекс
                if (index == 0) {
                    level[0].index = size / 2
                }
                for (node in level) {

                    node.leftChild?.let {
                        // устанавливаем начало левому ребёнку
                        it.begin = node.begin
                        // устанавливаем индекс левому ребёнку
                        val leftSize = it.sizeAndLevels().first
                        it.index = it.begin + leftSize / 2
                        // рисуем левое плечо
                        for (i in it.index..<node.index) {
                            if (i == it.index) canvas[index].replace(i, i + 1, "┌")
                            else canvas[index].replace(i, i + 1, "─")
                        }


                        node.rightChild?.let { rc ->
                            // устанавливаем начало правому ребёнку
                            rc.begin = node.begin + leftSize
                            // устанавливаем индекс правому ребёнку
                            val rightHalfSize = rc.sizeAndLevels().first / 2
                            rc.index = rc.begin + rightHalfSize

                            // рисуем правое плечо
                            for (i in node.index + 1..rc.index) {
                                if (i == rc.index) canvas[index].replace(i, i + 1, "┐")
                                else canvas[index].replace(i, i + 1, "─")
                            }


                        }
                    }
                    if (node.leftChild == null) {
                        node.rightChild?.let {
                            // устанавливаем начало правому ребёнку
                            it.begin = node.begin + 2
                            // устанавливаем индекс правому ребёнку
                            val rightHalfSize = it.sizeAndLevels().first / 2
                            it.index = it.begin + rightHalfSize
                            // рисуем правое плечо
                            for (i in node.index + 1..it.index) {
                                if (i == it.index) canvas[index].replace(i, i + 1, "┐")
                                else canvas[index].replace(i, i + 1, "─")
                            }
                        }

                    }

                    //рисуем нод на его позиции
                    canvas[index].replace(node.index, node.index + 1, node.value.toString())
                }

            }
            canvas.forEach(::println)
        }

    }
}