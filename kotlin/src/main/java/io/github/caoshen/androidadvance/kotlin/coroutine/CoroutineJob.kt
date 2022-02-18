package io.github.caoshen.androidadvance.kotlin.coroutine

import kotlinx.coroutines.*
import kotlin.random.Random

fun coroutineJobJoin() = runBlocking {
    // main @coroutine#1
    logX("runBlocking start!")
    // define download function
    suspend fun download() {
        // main @coroutine#2
        val time = (Random.nextDouble() * 1000).toLong()
        logX("Delay time: = $time")
    }

    val job = launch(start = CoroutineStart.LAZY) {
        // main @coroutine#2
        logX("Coroutine start!")
        download()
        logX("Coroutine end!")
    }

    delay(500)
    job.log()
    job.start()
    job.log()
}

fun Job.log() {
    logX("""
        isActive = $isActive
        isCancelled = $isCancelled
        isCompleted = $isCompleted
    """)
}

fun logX(any: Any?) {
    println(
        """
        ================================
        $any
        Thread:${Thread.currentThread().name}
        ================================
    """.trimIndent()
    )
}

fun main() {
//    coroutineJobJoin()
    val s = """
        isActive
        isCancelled
        isCompleted
    """.trimIndent()

    println(s)

    // 包含变量时顶格写字符串模板
    val s1 = """
==============
$s
--------------
    """.trimIndent()
    println(s1.trimIndent())
}