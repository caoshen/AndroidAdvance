package io.github.caoshen.androidadvance.kotlin.stantard

import java.lang.StringBuilder

/**
 * with 是一个普通的内联方法，非扩展方法。
 *
 * receiver 接收者
 * block 接收者的一个扩展方法，空参数，返回值为 with 的返回值
 * 返回 block 的值
 */
public inline fun <T, R> with(receiver: T, block: T.() -> R): R {
    // 返回第二个参数 lambda 的值
    println("standard function: with.")

    return receiver.block()
}

/**
 * apply 是一个扩展方法
 *
 * block 被扩展的接收者的一个扩展方法，空参数，返回也为空
 * 返回接收者自身
 */
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