package io.github.caoshen.androidadvance.kotlin.generics

import android.content.Context
import android.content.Intent
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.collections.ArrayList

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

// Cannot check for instance of erased type: T
// Make type parameter reified and function inline
//fun <T> isA(value: Any) = value is T

inline fun <reified T> isA(value: Any) = value is T

fun printIsA() {
    // true
    println(isA<String>("abc"))
    // false
    println(isA<String>(123))
}

fun filterIsInstance() {
    val items = listOf("one", 2, "three")
    // [one, three]
    println(items.filterIsInstance<String>())
}

fun afterReified() {
    val items = listOf("one", 2, "three")
    val destination = ArrayList<String>()

    for (element in items) {
        if (element is String) {
            destination.add(element)
        }
    }

    // [one, three]
    println(destination)
}

fun reifiedClass() {
    val serviceImpl = ServiceLoader.load(Service::class.java)
    // reified
    val serviceImpl2 = loadService<Service>()
}

inline fun <reified T> loadService() {
    ServiceLoader.load(T::class.java)
}

fun startActivity2(context: Context) {
    val intent = Intent(context, SampleActivity::class.java)
    context.startActivity(intent)
}

inline fun <reified T> Context.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

fun main() {

    afterReified()
//    filterIsInstance()
//    printIsA()
//    printSumInt(listOf(1, 2, 3))
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