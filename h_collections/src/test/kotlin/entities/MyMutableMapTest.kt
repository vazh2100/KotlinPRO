package entities

import entities.map.MyHashMap
import entities.map.MyMutableMap
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class MyMutableMapTest {
    companion object {
        @JvmStatic
        fun source() = listOf(
            MyHashMap<Int, Int>(),
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
    fun `When put key that not exist and load factor lesser 0 75 and no collision`(map: MyMutableMap<Int, Int>) {
        repeat(6) { map.put(it, it) }
        val keyValue = 10
        map.put(keyValue, keyValue)
        assertEquals(7, map.size)
        assertTrue(map.containsKey(keyValue))
        assertTrue(map.containsValue(keyValue))
        repeat(6) { assertTrue(map.containsKey(it)) }
        repeat(6) { assertTrue(map.containsValue(it)) }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When put key that not exist and load factor lesser 0 75 and collision`(map: MyMutableMap<Int, Int>) {
        repeat(6) { map.put(it * 16, it * 16) }
        val keyValue = 10 * 16
        map.put(keyValue, keyValue)
        assertEquals(7, map.size)
        assertTrue(map.containsKey(keyValue))
        assertTrue(map.containsValue(keyValue))
        repeat(6) { assertTrue(map.containsKey(it * 16)) }
        repeat(6) { assertTrue(map.containsValue(it * 16)) }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When put key that exist and load factor lesser 0 75 and no collision`(map: MyMutableMap<Int, Int>) {
        repeat(6) { map.put(it, it) }
        val key = 5
        val value = 100
        map.put(key, value)
        assertEquals(6, map.size)
        assertTrue(map.containsKey(key))
        assertTrue(map.containsValue(value))
        assertTrue(!map.containsValue(key))
        repeat(6) { assertTrue(map.containsKey(it)) }
        repeat(6) {
            if (it == key) return@repeat
            assertTrue(map.containsValue(it))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When put key that exist and load factor lesser 0 75 and collision`(map: MyMutableMap<Int, Int>) {
        repeat(6) { map.put(it * 16, it * 16) }
        val key = 5 * 16
        val value = 100
        map.put(key, value)
        assertEquals(6, map.size)
        assertTrue(map.containsKey(key))
        assertTrue(map.containsValue(value))
        assertTrue(!map.containsValue(key))
        repeat(6) { assertTrue(map.containsKey(it * 16)) }
        repeat(6) {
            if (it * 16 == key) return@repeat
            assertTrue(map.containsValue(it * 16))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When put key that not exist and load factor more 0 75 and no collision`(map: MyMutableMap<Int, Int>) {
        repeat(12) { map.put(it, it) }
        val keyValue = 14
        map.put(keyValue, keyValue)
        assertEquals(13, map.size)
        assertTrue(map.containsKey(keyValue))
        assertTrue(map.containsValue(keyValue))
        repeat(12) { assertTrue(map.containsKey(it)) }
        repeat(12) { assertTrue(map.containsValue(it)) }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When put key that not exist and load factor more 0 75 and collision`(map: MyMutableMap<Int, Int>) {
        repeat(6) { map.put(it * 32, it * 32) }
        val keyValue = 10 * 32
        map.put(keyValue, keyValue)
        assertEquals(7, map.size)
        assertTrue(map.containsKey(keyValue))
        assertTrue(map.containsValue(keyValue))
        repeat(6) { assertTrue(map.containsKey(it * 32)) }
        repeat(6) { assertTrue(map.containsValue(it * 32)) }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When put key that exist and load factor more 0 75 and no collision`(map: MyMutableMap<Int, Int>) {
        repeat(12) { map.put(it, it) }
        val key = 5
        val value = 100
        map.put(key, value)
        assertEquals(12, map.size)
        assertTrue(map.containsKey(key))
        assertTrue(map.containsValue(value))
        assertTrue(!map.containsValue(key))
        repeat(12) { assertTrue(map.containsKey(it)) }
        repeat(12) {
            if (it == key) return@repeat
            println(it)
            assertTrue(map.containsValue(it))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When put key that exist and load factor more 0 75 and collision`(map: MyMutableMap<Int, Int>) {
        repeat(12) { map.put(it * 32, it * 32) }
        val key = 5 * 32
        val value = 100
        map.put(key, value)
        assertEquals(12, map.size)
        assertTrue(map.containsKey(key))
        assertTrue(map.containsValue(value))
        assertTrue(!map.containsValue(key))
        repeat(12) { assertTrue(map.containsKey(it * 32)) }
        repeat(12) {
            if (it * 32 == key) return@repeat
            assertTrue(map.containsValue(it * 32))
        }
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
    fun `When remove single key that exist`(map: MyMutableMap<Int, Int>) {
        repeat(6) { map.put(it, it) }
        val key = 0
        map.remove(key)
        assertEquals(5, map.size)
        assertFalse(map.containsKey(key))
        assertFalse(map.containsValue(key))
        repeat(6) {
            if (it == 0) return@repeat
            assertTrue(map.containsKey(it))
            assertTrue(map.containsValue(it))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When remove root key that exist`(map: MyMutableMap<Int, Int>) {
        repeat(6) { map.put(it * 16, it * 16) }
        val key = 0 * 16
        map.remove(key)
        assertEquals(5, map.size)
        assertFalse(map.containsKey(key))
        assertFalse(map.containsValue(key))
        repeat(6) {
            if (it == 0) return@repeat
            assertTrue(map.containsKey(it * 16))
            assertTrue(map.containsValue(it * 16))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When remove second key that exist`(map: MyMutableMap<Int, Int>) {
        repeat(6) { map.put(it * 16, it * 16) }
        val key = 1 * 16
        map.remove(key)
        assertEquals(5, map.size)
        assertFalse(map.containsKey(key))
        assertFalse(map.containsValue(key))
        repeat(6) {
            if (it == 1) return@repeat
            assertTrue(map.containsKey(it * 16))
            assertTrue(map.containsValue(it * 16))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When remove last key that exist`(map: MyMutableMap<Int, Int>) {
        repeat(6) { map.put(it * 16, it * 16) }
        val key = 5 * 16
        map.remove(key)
        assertEquals(5, map.size)
        assertFalse(map.containsKey(key))
        assertFalse(map.containsValue(key))
        repeat(6) {
            if (it == 5) return@repeat
            assertTrue(map.containsKey(it * 16))
            assertTrue(map.containsValue(it * 16))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When remove single key that not exist`(map: MyMutableMap<Int, Int>) {
        repeat(6) { map.put(it, it) }
        val key = 7
        map.remove(key)
        assertEquals(6, map.size)
        assertFalse(map.containsKey(key))
        assertFalse(map.containsValue(key))
        repeat(6) {
            assertTrue(map.containsKey(it))
            assertTrue(map.containsValue(it))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When remove root key that not exist`(map: MyMutableMap<Int, Int>) {
        repeat(6) {
            if (it == 0) return@repeat
            map.put(it * 16, it * 16)
        }
        val key = 0 * 16
        map.remove(key)
        assertEquals(5, map.size)
        assertFalse(map.containsKey(key))
        assertFalse(map.containsValue(key))
        repeat(6) {
            if (it == 0) return@repeat
            assertTrue(map.containsKey(it * 16))
            assertTrue(map.containsValue(it * 16))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When remove second key that not exist`(map: MyMutableMap<Int, Int>) {
        repeat(6) {
            if (it == 1) return@repeat
            map.put(it * 16, it * 16)
        }
        val key = 1 * 16
        map.remove(key)
        assertEquals(5, map.size)
        assertFalse(map.containsKey(key))
        assertFalse(map.containsValue(key))
        repeat(6) {
            if (it == 1) return@repeat
            assertTrue(map.containsKey(it * 16))
            assertTrue(map.containsValue(it * 16))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `When remove last key that not exist`(map: MyMutableMap<Int, Int>) {
        repeat(6) {
            if (it == 5) return@repeat
            map.put(it * 16, it * 16)
        }
        val key = 5 * 16
        map.remove(key)
        assertEquals(5, map.size)
        assertFalse(map.containsKey(key))
        assertFalse(map.containsValue(key))
        repeat(6) {
            if (it == 5) return@repeat
            assertTrue(map.containsKey(it * 16))
            assertTrue(map.containsValue(it * 16))
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when modify map while iterating then throws`(map: MyMutableMap<Int, Int>) {
        for (i in 0..9) {
            map.put(i, i)
        }
        assertThrows(ConcurrentModificationException::class.java) {
            for (key in map.keys) {
                map.put(20, 20)
            }
        }
        assertThrows(ConcurrentModificationException::class.java) {
            for (key in map.keys) {
                map.remove(5)
            }
        }
        assertDoesNotThrow {
            for (key in map.keys) {
                map.remove(40)
            }
        }

        assertThrows(ConcurrentModificationException::class.java) {
            for (key in map.values) {
                map.put(21, 21)
            }
        }
        assertThrows(ConcurrentModificationException::class.java) {
            for (key in map.values) {
                map.remove(21)
            }
        }
        assertDoesNotThrow {
            for (key in map.values) {
                map.remove(40)
            }
        }

        assertThrows(ConcurrentModificationException::class.java) {
            for (key in map.entries) {
                map.put(22, 22)
            }
        }
        assertThrows(ConcurrentModificationException::class.java) {
            for (key in map.entries) {
                map.remove(22)
            }
        }
        assertDoesNotThrow {
            for (key in map.entries) {
                map.remove(40)
            }
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when modify subCollections while iterating then throws`(map: MyMutableMap<Int, Int>) {
        for (i in 0..9) {
            map.put(i, i)
        }

        val keys = map.keys
        val values = map.values
        val entries = map.entries

        assertThrows(ConcurrentModificationException::class.java) {
            for (key in keys) {
                keys.remove(key)
            }
        }
        assertThrows(ConcurrentModificationException::class.java) {
            for (value in values) {
                values.remove(5)
            }
        }
        assertThrows(ConcurrentModificationException::class.java) {
            for (entry in entries) {
                entries.remove(entry)
            }
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when iterating the order is correct`(map: MyMutableMap<Int, Int>) {
        val expected = mutableListOf<Int>()
        for (i in 1..95) {
            map.put(i * 32, i * 32)
            expected.add(i * 32)
        }

        for (i in 1..95) {
            map.remove(i * 32 * 2)
            expected.remove(i * 32 * 2)
        }
        val result = mutableListOf<Int>()
        for (key in map.keys) {
            result.add(key)
        }
        when (map) {
//            is MyLinkedHashSet -> assertEquals(expected, result)
            is MyMutableMap -> assertNotEquals(expected, result)
        }
    }
}
