package com.example.androidbasic

import com.example.androidbasic.util.Util.Companion.signParam
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testSignParam() {
        val param = mapOf<String, Any>(
            "id" to 114514,
            "str" to "1919810",
            "test" to "いいよ，こいよ",
        )

        val p = signParam(param = LinkedHashMap(param))
        println(p)
    }
}