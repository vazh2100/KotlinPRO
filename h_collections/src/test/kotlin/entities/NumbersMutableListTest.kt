package entities

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class NumbersMutableListTest {

    companion object {
        @JvmStatic
        fun source() = listOf(NumbersArrayList())
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when add 1 element then size is 1`(list: NumbersMutableList) {
        list.add(0)
        val expected = 1
        assertEquals(expected, list.size)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when add 10 element then size is 10`(list: NumbersMutableList) {
        repeat(10) {
            list.add(it)
        }
        val expected = 10
        assertEquals(expected, list.size)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when add 100 element then size is 100`(list: NumbersMutableList) {
        repeat(100) {
            list.add(it)
        }
        val expected = 100
        assertEquals(expected, list.size)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when get 5th element then result is correct`(list: NumbersMutableList) {
        repeat(10) {
            list.add(it)
        }
        val result = list.get(4)
        val expected = 4
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("source")
    fun `when get 50th element then result is correct`(list: NumbersMutableList) {
        repeat(100) {
            list.add(it)
        }
        val result = list.get(49)
        val expected = 49
        assertEquals(expected, result)
    }


    @ParameterizedTest
    @MethodSource("source")
    fun `when remove 50th element then size is 99`(list: NumbersMutableList) {
        repeat(100) {
            list.add(it)
        }
        list.removeAt(50)
        val expected = 99
        assertEquals(expected, list.size)

        val result = list.get(50)
        val expected2 = 51
        assertEquals(expected2, result)
    }

}