package entities

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class NumbersHashSetTest {

    companion object {
        @JvmStatic
        fun source() = listOf(
            NumbersHashSet()
        )
    }


    /**
     * Когда добавляем того чего нет, размер увеличивается. Коллекция содержит новый элемент и все другие элементы
     * Когда добавляем то, что есть, размер не увеличивается. Коллекция осталась неизменной и содержит элемент.
     * Повторить предыдущие тесты: Когда при добавлении коллекция увеличивает размер.
     * Повторить предыдущие тесты: Когда возникает коллизия.
     * */
    @ParameterizedTest
    @MethodSource("source")
    fun `When add element that not exist and load factor lesser 0 75 and no collision`(set: NumbersMutableSet) {
        repeat(6) { set.add(it) }
        val element = 10
        set.add(element)
        assertEquals(7, set.size)
        assertTrue(set.contains(element))
        repeat(6) { assertTrue(set.contains(it)) }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When add element that not exist and load factor lesser 0 75 and collision`(set: NumbersMutableSet) {
        repeat(6) { set.add(it * 16) }
        println(set)
        val element = 10 * 16
        set.add(element)
        assertEquals(7, set.size)
        assertTrue(set.contains(element))
        repeat(6) { assertTrue(set.contains(it * 16)) }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When add element that exist and load factor lesser 0 75 and no collision`(set: NumbersMutableSet) {
        repeat(6) { set.add(it) }
        val element = 5
        set.add(element)
        assertEquals(6, set.size)
        assertTrue(set.contains(element))
        repeat(6) { assertTrue(set.contains(it)) }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When add element that exist and load factor lesser 0 75 and collision`(set: NumbersMutableSet) {
        repeat(6) { set.add(it * 16) }
        println(set)
        val element = 5 * 16
        set.add(element)
        assertEquals(6, set.size)
        assertTrue(set.contains(element))
        repeat(6) { assertTrue(set.contains(it * 16)) }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When add element that not exist and load factor more 0 75 and no collision`(set: NumbersMutableSet) {
        repeat(12) { set.add(it) }
        val element = 14
        set.add(element)
        assertEquals(13, set.size)
        assertTrue(set.contains(element))
        repeat(12) { assertTrue(set.contains(it)) }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When add element that not exist and load factor more 0 75 and collision`(set: NumbersMutableSet) {
        repeat(12) { set.add(it * 32) }
        val element = 12 * 32
        set.add(element)
        assertEquals(13, set.size)
        assertTrue(set.contains(element))
        repeat(12) { assertTrue(set.contains(it * 32)) }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When add element that exist and load factor more 0 75 and no collision`(set: NumbersMutableSet) {
        repeat(12) { set.add(it) }
        val element = 5
        set.add(element)
        println(set)
        assertEquals(12, set.size)
        assertTrue(set.contains(element))
        repeat(12) { assertTrue(set.contains(it)) }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When add element that exist and load factor more 0 75 and collision`(set: NumbersMutableSet) {
        repeat(12) { set.add(it * 32) }
        val element = 5 * 32
        set.add(element)
        println(set)
        assertEquals(12, set.size)
        assertTrue(set.contains(element))
        repeat(12) { assertTrue(set.contains(it * 32)) }
    }

    /**

     * Когда удаляем то, что есть размер уменьшается, коллекция содержит все элементы кроме
     * Когда удаляем того, чего нет размер не уменьшается.
     *  Повторить предыдущие тесты: Когда удаляем единственный элемент.
     * Повторить предыдущие тесты: Когда удаляем корневой элемент.
     * Повторить предыдущие тесты: Когда удаляем второй элемент в коллизии.
     * Повторить предыдущие тесты: Когда удаляем последний элемент в коллизии
     * */

    @ParameterizedTest
    @MethodSource("source")
    fun `When remove single element that exist`(set: NumbersMutableSet) {
        repeat(6) { set.add(it) }
        val element = 0
        set.remove(element)
        println(set)
        assertEquals(5, set.size)
        assertFalse(set.contains(element))
        repeat(6) {
            if (it == 0) return@repeat
            assertTrue(set.contains(it))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When remove root element that exist`(set: NumbersMutableSet) {
        repeat(6) { set.add(it * 16) }
        val element = 0 * 16
        set.remove(element)
        println(set)
        assertEquals(5, set.size)
        assertFalse(set.contains(element))
        repeat(6) {
            if (it == 0) return@repeat
            assertTrue(set.contains(it * 16))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When remove second element that exist`(set: NumbersMutableSet) {
        repeat(6) { set.add(it * 16) }
        val element = 1 * 16
        set.remove(element)
        println(set)
        assertEquals(5, set.size)
        assertFalse(set.contains(element))
        repeat(6) {
            if (it == 1) return@repeat
            assertTrue(set.contains(it * 16))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When remove last element that exist`(set: NumbersMutableSet) {
        repeat(6) { set.add(it * 16) }
        val element = 5 * 16
        set.remove(element)
        assertEquals(5, set.size)
        assertFalse(set.contains(element))
        repeat(6) {
            if (it == 5) return@repeat
            assertTrue(set.contains(it * 16))
        }
    }


    @ParameterizedTest
    @MethodSource("source")
    fun `When remove single element that not exist`(set: NumbersMutableSet) {
        repeat(6) { set.add(it) }
        val element = 7
        set.remove(element)
        println(set)
        assertEquals(6, set.size)
        assertFalse(set.contains(element))
        repeat(6) {
            if (it == 0) return@repeat
            assertTrue(set.contains(it))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When remove root element that not exist`(set: NumbersMutableSet) {
        repeat(6) {
            if (it == 0) return@repeat
            set.add(it * 16)
        }
        val element = 0 * 16
        set.remove(element)
        assertEquals(5, set.size)
        assertFalse(set.contains(element))
        repeat(6) {
            if (it == 0) return@repeat
            assertTrue(set.contains(it * 16))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When remove second element that not exist`(set: NumbersMutableSet) {
        repeat(6) {
            if (it == 1) return@repeat
            set.add(it * 16)
        }
        val element = 1 * 16
        set.remove(element)
        assertEquals(5, set.size)
        assertFalse(set.contains(element))
        repeat(6) {
            if (it == 1) return@repeat
            assertTrue(set.contains(it * 16))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When remove last element that not exist`(set: NumbersMutableSet) {
        repeat(6) {
            if (it == 5) return@repeat
            set.add(it * 16)
        }
        val element = 5 * 16
        set.remove(element)
        assertEquals(5, set.size)
        assertFalse(set.contains(element))
        repeat(6) {
            if (it == 5) return@repeat
            assertTrue(set.contains(it * 16))
        }
    }


}