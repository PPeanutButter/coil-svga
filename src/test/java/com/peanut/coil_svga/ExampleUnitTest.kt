package com.peanut.coil_svga

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*

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


}

suspend fun main() {
    println("start")
    coroutineScope {
        launch {
            delay(100)
            println("end a")
        }
        launch {
            delay(50)
            println("end b")
        }
    }
    println("end")
}