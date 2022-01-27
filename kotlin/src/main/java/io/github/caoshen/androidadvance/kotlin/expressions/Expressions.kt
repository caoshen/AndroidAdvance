package io.github.caoshen.androidadvance.kotlin.expressions

import java.lang.NullPointerException
import java.lang.NumberFormatException
import kotlin.math.abs

fun javaStyleExpressions() {
    val data: Int? = 3

    var i = 0
    if (data != null) {
        i = data
    }

    var j = 0
    if (data != null) {
        j = data
    } else {
        j = getDefault()
        println(j)
    }

    var k = 0
    if (data != null) {
        k = data
    } else {
        throw NullPointerException()
    }

    var x = 0
    when (data) {
        is Int -> x = data
        else -> x = 0
    }

    var y = 0
    try {
        y = "Kotin".toInt()
    } catch (e: NumberFormatException) {
        println(e)
        y = 0
    }
}

fun kotlinStyleExpressions() {
    val data: Int? = 3

    val i = data ?: 0

    val j = data ?: getDefault().also { println(it) }

    val k = data ?: throw NullPointerException()

    val x = when (data) {
        is Int -> data
        else -> 0
    }

    val y = try {
        "Kotin".toInt()
    } catch (e: NumberFormatException) {
        println(e)
        0
    }
}

fun getDefault() = -1

fun main() {
    javaStyleExpressions()

    kotlinStyleExpressions()
}

/**
 * 语句，是一句不产生值的代码
 */
fun statement() {
    val a = 1
    println(a)

    val data: Int? = 1
    var i = 0
    if (data != null) {
        i = data
    }
}

/**
 * 表达式是一段可以产生值的代码
 */
fun expressions() {
    val b = 1 + 2
}

fun minus(a: Int, b: Int) = if (a > b) a - b else b - a

// throw NotImplementedError() 是一个表达式，返回 Nothing 类型
fun calculate(): Int = throw NotImplementedError()
