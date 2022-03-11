package io.github.caoshen.androidadvance.kotlin.generics

fun printContents(list: List<Any>) {
    println(list.joinToString())
}

fun addAnswer(list: MutableList<Any>) {
    list.add(42)
}

fun main() {
    val list = mutableListOf("abc", "def")
    printContents(list)

//    val strings: MutableList<Any> = mutableListOf("abc", "defg")
//    addAnswer(strings)
//    println(strings.maxByOrNull {
//        it.length
//    })
}