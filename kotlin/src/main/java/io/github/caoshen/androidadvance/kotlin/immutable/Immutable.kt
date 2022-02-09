package io.github.caoshen.androidadvance.kotlin.immutable

data class Person(
    val name: String?,
    val age: Int?
)

fun main() {
    val person = Person("zhang san", 30)
}



