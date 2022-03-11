package io.github.caoshen.androidadvance.kotlin.generics

interface Comparator<in T> {
    fun compare(e1: T, e2: T): Int
}

val strings: List<String> = listOf("a", "c", "b")

fun sortString() {
    val anyComparator = Comparator<Any> { e1, e2 ->
        println("$e1 hashcode=${e1.hashCode()}")
        println("$e2 hashcode=${e2.hashCode()}")
        e1.hashCode() - e2.hashCode()
    }
    // sortedWith 需要 Comparator<String> 作为参数，我们传入了 Comparator<Any>。
    // 因为 Comparator 是逆变的，Comparator<Any> 是 Comparator<String> 的子类型，
    // 所以可以使用泛型父类代替泛型子类传递参数。
    val sortedWith = strings.sortedWith(anyComparator)

    println(sortedWith)
    println(strings)
}

// 箭头操作符的单参数函数形式 Function1
// 通常我们会写成 (P) -> R，这种表示法隐含了 P 是逆变的，R 是协变的。
interface Function1<in P, out R> {
    operator fun invoke(p: P): R
}

open class Animal {

}

class Cat : Animal() {

}

// Cat 是参数，f 在 Cat 上是逆变的。Number 是返回值，f 在 Number 是协变的。
fun enumerateCats(f: (Cat) -> Number) {

}

fun Animal.getIndex(): Int = 0

fun testEnumerateCats() {
    enumerateCats(Animal::getIndex)
}

fun main() {
    sortString()
}
