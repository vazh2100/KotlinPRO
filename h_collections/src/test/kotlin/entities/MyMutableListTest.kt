package entities

import entities.list.MyArrayList
import entities.list.MyLinkedList
import entities.list.MyMutableList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class MyMutableListTest {

    companion object {
        @JvmStatic
        fun source() = listOf(
            MyArrayList(),
            MyLinkedList<Int>()
        )
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when add 1 element then size is 1`(list: MyMutableList<Int>) {
        list + 0
        val expected = 1
        assertEquals(expected, list.size)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when add 10 element then size is 10`(list: MyMutableList<Int>) {
        repeat(10) {
            list + it
        }
        val expected = 10
        assertEquals(expected, list.size)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when add 100 element then size is 100`(list: MyMutableList<Int>) {
        repeat(100) {
            list + it
        }
        val expected = 100
        assertEquals(expected, list.size)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when get 5th element then result is correct`(list: MyMutableList<Int>) {
        repeat(10) {
            list + it
        }
        val result = list[4]
        val expected = 4
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when get index 10 element then result is throws`(list: MyMutableList<Int>) {
        repeat(10) {
            list + it
        }
        assertThrows(IllegalStateException::class.java) { list[10] }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when get 50th element then result is correct`(list: MyMutableList<Int>) {
        repeat(100) {
            list + it
        }
        val result = list[49]
        val expected = 49
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when remove 50th element then size is 99`(list: MyMutableList<Int>) {
        repeat(100) {
            list + it
        }
        list.removeAt(50)
        val expected = 99
        assertEquals(expected, list.size)

        val result = list[50]
        val expected2 = 51
        assertEquals(expected2, result)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when remove element then size is 99`(list: MyMutableList<Int>) {
        repeat(100) {
            list + it
        }
        list - 50
        val expected = 99
        assertEquals(expected, list.size)
        val result = list[50]
        val expected2 = 51
        assertEquals(expected2, result)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when remove last element then size is 99`(list: MyMutableList<Int>) {
        repeat(100) {
            list + it
        }
        list - 99
        val expected = 99
        assertEquals(expected, list.size)
        val result = list[98]
        val expected2 = 98
        assertEquals(expected2, result)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when remove first element then size is 99`(list: MyMutableList<Int>) {
        repeat(100) {
            list + it
        }
        list - 0
        val expected = 99
        assertEquals(expected, list.size)
        val result = list[0]
        val expected2 = 1
        assertEquals(expected2, result)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when insert element at 2 index then size is 6`(list: MyMutableList<Int>) {
        for (i in 0..4) {
            list.add(i)
        }
        val index = 2

        list.insert(index, 77)
        println(list)
        val result1 = list.size
        val expected1 = 6
        assertEquals(expected1, result1)

        val result2 = list.get(index)
        val expected2 = 77
        assertEquals(expected2, result2)

        val result3 = list.get(index + 1)
        val expected3 = index
        assertEquals(expected3, result3)

        val result4 = list.get(index - 1)
        val expected4 = index - 1
        assertEquals(expected4, result4)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when insert element at 4 index then size is 6`(list: MyMutableList<Int>) {
        for (i in 0..4) {
            list.add(i)
        }
        val index = 4

        list.insert(index, 77)
        println(list)
        val result1 = list.size
        val expected1 = 6
        assertEquals(expected1, result1)

        val result2 = list.get(index)
        val expected2 = 77
        assertEquals(expected2, result2)

        val result3 = list.get(index + 1)
        val expected3 = index
        assertEquals(expected3, result3)

        val result4 = list.get(index - 1)
        val expected4 = index - 1
        assertEquals(expected4, result4)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when insert element at 5 index then size is 6`(list: MyMutableList<Int>) {
        for (i in 0..4) {
            list.add(i)
        }
        val index = 5

        list.insert(index, 77)
        println(list)
        val result1 = list.size
        val expected1 = 6
        assertEquals(expected1, result1)

        val result2 = list.get(index)
        val expected2 = 77
        assertEquals(expected2, result2)

        val result4 = list.get(index - 1)
        val expected4 = index - 1
        assertEquals(expected4, result4)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when insert element at 6 index then throws`(list: MyMutableList<Int>) {
        for (i in 0..4) {
            list.add(i)
        }
        assertThrows(IllegalStateException::class.java) {
            list.insert(6, 77)
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when insert element at 0 index then size is 6`(list: MyMutableList<Int>) {
        for (i in 0..4) {
            list.add(i)
        }
        val index = 0

        list.insert(index, 77)
        println(list)
        val result1 = list.size
        val expected1 = 6
        assertEquals(expected1, result1)

        val result2 = list.get(index)
        val expected2 = 77
        assertEquals(expected2, result2)

        val result3 = list.get(index + 1)
        val expected3 = index
        assertEquals(expected3, result3)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when insert element at 1 index then size is 6`(list: MyMutableList<Int>) {
        for (i in 0..4) {
            list.add(i)
        }
        val index = 1

        list.insert(index, 77)
        println(list)
        val result1 = list.size
        val expected1 = 6
        assertEquals(expected1, result1)

        val result = list.get(index)
        val expected2 = 77
        assertEquals(expected2, result)

        val result3 = list.get(index + 1)
        val expected3 = index
        assertEquals(expected3, result3)

        val result4 = list.get(index - 1)
        val expected4 = index - 1
        assertEquals(expected4, result4)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when insert element at 5 index then size is 11`(list: MyMutableList<Int>) {
        for (i in 0..9) {
            list.add(i)
        }
        val index = 5

        list.insert(index, 77)
        println(list)
        val result1 = list.size
        val expected1 = 11
        assertEquals(expected1, result1)

        val result2 = list.get(index)
        val expected2 = 77
        assertEquals(expected2, result2)

        val result3 = list.get(index + 1)
        val expected3 = index
        assertEquals(expected3, result3)

        val result4 = list.get(index - 1)
        val expected4 = index - 1
        assertEquals(expected4, result4)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when insert element at 9 index then size is 11`(list: MyMutableList<Int>) {
        for (i in 0..9) {
            list.add(i)
        }
        val index = 9

        list.insert(index, 77)
        println(list)
        val result1 = list.size
        val expected1 = 11
        assertEquals(expected1, result1)

        val result2 = list.get(index)
        val expected2 = 77
        assertEquals(expected2, result2)

        val result3 = list.get(index + 1)
        val expected3 = index
        assertEquals(expected3, result3)

        val result4 = list.get(index - 1)
        val expected4 = index - 1
        assertEquals(expected4, result4)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when insert element at 10 index then size is 11`(list: MyMutableList<Int>) {
        for (i in 0..9) {
            list.add(i)
        }
        val index = 10

        list.insert(index, 77)
        println(list)
        val result1 = list.size
        val expected1 = 11
        assertEquals(expected1, result1)

        val result2 = list.get(index)
        val expected2 = 77
        assertEquals(expected2, result2)

        val result4 = list.get(index - 1)
        val expected4 = index - 1
        assertEquals(expected4, result4)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when insert element at 11 index then throws`(list: MyMutableList<Int>) {
        for (i in 0..9) {
            list.add(i)
        }
        assertThrows(IllegalStateException::class.java) {
            list.insert(11, 77)
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when insert element at 0 index then size is 11`(list: MyMutableList<Int>) {
        for (i in 0..9) {
            list.add(i)
        }
        val index = 0

        list.insert(index, 77)
        println(list)
        val result1 = list.size
        val expected1 = 11
        assertEquals(expected1, result1)

        val result2 = list.get(index)
        val expected2 = 77
        assertEquals(expected2, result2)

        val result3 = list.get(index + 1)
        val expected3 = index
        assertEquals(expected3, result3)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when insert element at 1 index then size is 11`(list: MyMutableList<Int>) {
        for (i in 0..9) {
            list.add(i)
        }
        val index = 1

        list.insert(index, 77)
        println(list)
        val result1 = list.size
        val expected1 = 11
        assertEquals(expected1, result1)

        val result = list.get(index)
        val expected2 = 77
        assertEquals(expected2, result)

        val result3 = list.get(index + 1)
        val expected3 = index
        assertEquals(expected3, result3)

        val result4 = list.get(index - 1)
        val expected4 = index - 1
        assertEquals(expected4, result4)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when clear size is 0`(list: MyMutableList<Int>) {
        for (i in 0..9) {
            list.add(i)
        }
        list.clear()
        println(list)
        val result1 = list.size
        val expected1 = 0
        assertEquals(expected1, result1)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when call contains return true`(list: MyMutableList<Int>) {
        for (i in 0..9) {
            list.add(i)
        }
        val result1 = list.contains(5)
        assertTrue(result1)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when call contains return false`(list: MyMutableList<Int>) {
        for (i in 0..9) {
            list.add(i)
        }
        val result1 = list.contains(10)
        assertFalse(result1)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when modify while iterating then throws`(list: MyMutableList<Int>) {
        for (i in 0..9) {
            list.add(i)
        }
        assertThrows(ConcurrentModificationException::class.java) {
            for (element in list) {
                list.add(20)
            }
        }
        assertThrows(ConcurrentModificationException::class.java) {
            for (element in list) {
                list.insert(0, 20)
            }
        }
        assertThrows(ConcurrentModificationException::class.java) {
            for (element in list) {
                list.remove(5)
            }
        }
        assertDoesNotThrow {
            for (element in list) {
                list.remove(40)
            }
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `iterator throws illegal exceptions when remove`(list: MyMutableList<Int>) {
        for (i in 0..9) {
            list.add(i)
        }

        val iteratorA = list.iterator()
        assertThrows(IllegalStateException::class.java) {
            iteratorA.remove()
        }
        val iteratorB = list.iterator()
        assertThrows(IllegalStateException::class.java) {
            iteratorB.next()
            iteratorB.remove()
            iteratorB.remove()
        }

        val iteratorC = list.iterator()
        assertThrows(IllegalStateException::class.java) {
            iteratorC.next()
            iteratorC.remove()
            iteratorC.next()
            iteratorC.remove()
            iteratorC.remove()
        }
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `iterator remove correctly`(list: MyMutableList<Int>) {
        for (i in 0..9) {
            list.add(i)
        }

        val iteratorA = list.iterator()
        iteratorA.next()
        iteratorA.remove()
        assertEquals(1, list[0])
        iteratorA.next()
        iteratorA.next()
        iteratorA.remove()
        assertEquals(3, list[1])
        iteratorA.next()
        iteratorA.remove()
        assertEquals(4, list[1])
        iteratorA.next()
        iteratorA.remove()
        assertEquals(5, list[1])
        iteratorA.next()
        iteratorA.next()
        iteratorA.remove()
        assertEquals(7, list[2])
    }
}
