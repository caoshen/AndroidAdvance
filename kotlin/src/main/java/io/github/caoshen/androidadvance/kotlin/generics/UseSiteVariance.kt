package io.github.caoshen.androidadvance.kotlin.generics

/**
 * 使用点变型
 */


// 不变型的复制方法
fun <T> copyData(source: MutableList<T>, destination: MutableList<T>) {
    for (item in source) {
        destination.add(item)
    }
}

fun <T : R, R> copyData2(source: MutableList<T>, destination: MutableList<R>) {
    for (item in source) {
        destination.add(item)
    }
}

fun testCopyData2() {
    val ints = mutableListOf(1, 2, 3)
    val anyItems = mutableListOf<Any>()

    copyData2(ints, anyItems)
    println(anyItems)
}

fun <T> copyData3(source: MutableList<out T>, destination: MutableList<T>) {
    for (item in source) {
        destination.add(item)
    }
}

fun testCopyData3() {
    val list : MutableList<out Number> = mutableListOf(1, 2, 3)
    // compile error, The integer literal does not conform to the expected type Nothing
//    list.add(42)
}

fun <T> copyData4(source: MutableList<T>, destination: MutableList<in T>) {
    for (item in source) {
        destination.add(item)
    }
}

fun testCopyData4() {
    val ints = mutableListOf(1, 2, 3)
    val anyItems = mutableListOf<Any>()

    copyData4(ints, anyItems)
    println(anyItems)
}

fun main() {
    testCopyData4()
}
