package io.github.caoshen.androidadvance.kotlin.oop

class Person(val name: String, var age: Int) {
    val isAdult
        get() = age >= 18
}