package io.github.caoshen.androidadvance.kotlin.generics

import java.lang.IllegalArgumentException

val <T> List<T>.penultimate: T
    get() = this[size - 2]

fun erase() {
    val list1: List<String> = listOf("a", "b");
    val list2: List<Int> = listOf(1, 2, 3)
}

//fun <T> checkInstance(value: List<T>) {
//    if (value is List<String>) {
//        println("is string list.")
//    }
//}

fun <T> checkListInstance(value: List<T>) {
    if (value is List<*>) {
        println("is a list with any type")
    }
}

fun printSum(c: Collection<*>) {
    val intList = c as? List<Int> ?: throw IllegalArgumentException("List is expected");
    println(intList.sum())
}

fun printSumInt(c: Collection<Int>) {
    if (c is List<Int>) {
        println(c.sum())
    }
}

fun main() {
    printSumInt(listOf(1, 2, 3))
//    printSum(listOf("a", "b", "c"))
//    printSum(listOf(1, 2, 3))
//    printSum(setOf(1, 2, 3))
//    val strList: List<String>
//    filter()

//    val readers2 = mutableListOf<String>("Dmi")
//
//
//    val letters = ('a'..'z').toList()
//    letters.penultimate.run {
//        println(this)
//    }
//    println(letters.slice<Char>(0..2))
//
//    println(letters.slice(10..13))


}

private fun filter() {
    val authors = listOf("Dmitry", "Svetlana")
    val readers: List<String> = mutableListOf("Dmitry", "Svetlana", "zhangsan", "lisi")
    // 输出[zhangsan, lisi]
    readers.filter {
        it !in authors
    }.apply { println(this) }
}