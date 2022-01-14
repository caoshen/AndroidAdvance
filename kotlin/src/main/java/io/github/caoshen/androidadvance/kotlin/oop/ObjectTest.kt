package io.github.caoshen.androidadvance.kotlin.oop

fun testPerson() {
    val person = Person("zhangsan", 30)
    person.age = 31

    println("${person.name} ${person.age} , is adult: ${person.isAdult}")
}

fun main() {
    testPerson()
}