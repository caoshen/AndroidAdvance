package io.github.caoshen.androidadvance.kotlin.delegation

class Delegate : Subject {
    override fun buy() {
        println("I can buy it because I live abroad.")
    }
}