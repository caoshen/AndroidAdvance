package io.github.caoshen.androidadvance.kotlin.oop

fun testPerson() {
    val person = Person("zhangsan", 30)
    person.age = 31

    println("${person.name} ${person.age} , is adult: ${person.isAdult}")
}

fun main() {
    testPerson()

    val person3 = Person3("zhang san", 18)
    val component1 = person3.component1()
    val component2 = person3.component2()
    println("component1:$component1, component2:$component2")
}