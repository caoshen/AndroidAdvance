package io.github.caoshen.androidadvance.jetpack.stantard

import java.lang.StringBuilder

public inline fun <T, R> with(receiver: T, block: T.() -> R): R {
    // 返回第二个参数 lambda 的值
    println("standard function: with.")

    return receiver.block()
}

public fun <T> T.apply(block: T.() -> Unit): T {
    println("standard function: apply.")

    block()
    // 返回接收者自身
    return this
}

public fun String.lastChar() = get(length - 1)


fun main() {
//    println("Kotlin".lastChar())

    // 测试 with，返回第二个参数 block lambda 的最后一行的值receiver.block()
    val alphabetWith = alphabetWith()
    println(alphabetWith)

    // 测试 apply，返回接收者自身this
    println(alphabetApply())

    // buildString 替代 apply
    println(alphabetBuildString())
}

fun alphabetWith() = with(StringBuilder()) {
    for (letter in 'A' .. 'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
    toString()
}

fun alphabetApply() = StringBuilder().apply {
    for (letter in 'A'.. 'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}.toString()

fun alphabetBuildString() = buildString {
    println("standard string function: buildString.")
    for (letter in 'A'.. 'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}