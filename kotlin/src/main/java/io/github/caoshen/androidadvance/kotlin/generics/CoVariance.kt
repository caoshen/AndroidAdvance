package io.github.caoshen.androidadvance.kotlin.generics

interface Producer<out T> {
    fun produce(): T
}

open class Animal {
    fun feed() {
    }
}

class Herd<out T : Animal> {
    private val _animals: List<T> = listOf()
    val size: Int
        get() = _animals.size

    operator fun get(i: Int): T = _animals[i]
}

fun feedAll(animals: Herd<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}

class Cat : Animal() {
    fun cleanLitter() {
    }
}

fun takeCareOfCats(cats: Herd<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
        // feedAll 函数的参数是 Herd<Animal>，但是传入了 Herd<Cat> 所以编译器报错
        // 正常来说，喂养所有的猫是合理的，因为猫是动物的子类型。
        // 但是编译器不认为 Herd<Cat> 是 Herd<Animal> 的子类型
        feedAll(cats)
    }
}

interface Transformer<T> {
    fun transform(t: T): T
}

class Herd2<out T : Animal>(private var leadAnimal: T, vararg animals: T) {

}

fun main() {

}