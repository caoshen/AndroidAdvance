package io.github.caoshen.androidadvance.kotlin.generics

import java.util.*

val list: MutableList<Any?> = mutableListOf('a', 1, "qwe")

val chars = mutableListOf('a', 'b', 'c')

val unknownElements: MutableList<*> = if (Random().nextBoolean()) list else chars

fun testStart() {
    // compile error.
    // The integer literal does not conform to the expected type Nothing
//    unknownElements.add(42)
    println(unknownElements.first())
}

fun printFirst(list: List<*>) {
    if (list.isNotEmpty()) {
        println(list.first())
    }
}

fun <T> printFirst2(list: List<T>) {
    if (list.isNotEmpty()) {
        println(list.first())
    }
}

fun main() {
    printFirst(listOf("Svetlana", "Dmitry"))
    printFirst2(listOf("Svetlana", "Dmitry"))
//    testStart()
}