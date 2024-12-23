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
    fun `when get 5th element then result is correct`(list: NumbersMutableList) {
        repeat(10) {
            list.add(it)
        }
        val result = list.get(4)
        val expected = 4
        assertEquals(expected, result)
    }

}