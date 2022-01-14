package io.github.caoshen.androidadvance.kotlin.conventions

import java.time.LocalDate

class Range {
    fun range() {
        val now = LocalDate.now()
        val vacation = now..now.plusDays(10L)
        println(now.plusWeeks(1) in vacation)
    }

    fun rangePlus() {
        val n = 9
        // 0..10
        println(0..n + 1)
        // 0..10
        println(0..(n + 1))
        // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1]
        println((0..n) + 1)
    }
}

