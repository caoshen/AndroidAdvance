package io.github.caoshen.androidadvance.kotlin.generics

fun test(i : Int) {
    // compile ok
    val n : Number = i

    fun f(s: String) {
        println(s)
    }
    // compile error
//    f(i)
}

fun test2() {
    val s: String = "abc"
    // compile ok
    val t: String? = s

    // compile error
//    val r: String = t
}

fun main() {

}