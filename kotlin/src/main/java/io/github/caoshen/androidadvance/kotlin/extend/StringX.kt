package io.github.caoshen.androidadvance.kotlin.extend

fun String.lastChar() = get(length - 1)

fun main() {
    println("Kotlin".lastChar())
}