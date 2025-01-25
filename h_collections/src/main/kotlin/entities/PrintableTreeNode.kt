package entities

abstract class PrintableTreeNode<T> {
    abstract val value: T
    abstract val leftChild: PrintableTreeNode<T>?
    abstract val rightChild: PrintableTreeNode<T>?

    private var begin = 0
    private var index = 0

    private fun sizeAndLevels(): Pair<Int, List<List<PrintableTreeNode<T>>>> {
        val levels = mutableListOf<List<PrintableTreeNode<T>>>()

        var mutableList = mutableListOf<PrintableTreeNode<T>>()
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

    @Suppress("NestedBlockDepth")
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
                        if (i == it.index) {
                            canvas[index].replace(i, i + 1, "┌")
                        } else {
                            canvas[index].replace(i, i + 1, "─")
                        }
                    }

                    node.rightChild?.let { rc ->
                        // устанавливаем начало правому ребёнку
                        rc.begin = node.begin + leftSize
                        // устанавливаем индекс правому ребёнку
                        val rightHalfSize = rc.sizeAndLevels().first / 2
                        rc.index = rc.begin + rightHalfSize

                        // рисуем правое плечо
                        for (i in node.index + 1..rc.index) {
                            if (i == rc.index) {
                                canvas[index].replace(i, i + 1, "┐")
                            } else {
                                canvas[index].replace(i, i + 1, "─")
                            }
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
                            if (i == it.index) {
                                canvas[index].replace(i, i + 1, "┐")
                            } else {
                                canvas[index].replace(i, i + 1, "─")
                            }
                        }
                    }
                }

                // рисуем нод на его позиции
                canvas[index].replace(node.index, node.index + 1, node.value.toString())
            }
        }
        canvas.forEach(::println)
    }

    override fun toString(): String {
        return value.toString()
    }
}
