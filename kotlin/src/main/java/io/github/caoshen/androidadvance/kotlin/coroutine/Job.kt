package io.github.caoshen.androidadvance.kotlin.coroutine

import kotlinx.coroutines.*
import kotlin.math.log

fun main() = runBlocking {
    val job = launch {
        delay(1000L)
    }
    job.log()
    job.cancel()
    job.log()
    delay(500)
    job.log()
    delay(1500L)
    job.log()
}

fun Job.log() {
    logX(
        """
        isActive = $isActive
        isCancelled = $isCancelled
        isCompleted = $isCompleted
    """.trimIndent()
    )
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

fun main3() = runBlocking {
    val job = launch(start = CoroutineStart.LAZY) {
        logX("Coroutine start!")
        delay(1000L)
    }

    delay(500L)
    job.log()
    job.start()
    job.log()
    delay(500L)

    job.cancel()
    delay(500L)
    job.log()

    delay(2000L)
    logX("Process end!")

    val str = "abc"
    str.apply {

    }

    with(str) {

    }

    str.let {

    }
}


fun song(f: (String) -> Unit) {
    println("f:$f")
}

inline fun song2(crossinline f: (String) -> Unit) {
//    println("f:$f")
}

fun behavior(): Unit {
    song {
        return@song
    }
}

fun behavior2(): Unit {
    song2 {
        return@song2
    }
}

fun main4() = runBlocking {
    val parentJob: Job
    var job1: Job
    var job2: Job
    var job3: Job

    val a: Int
    a = 1
    "".isNullOrEmpty()
    println("a:$a")

    parentJob = launch {
        job1 = launch {
            delay(1000L)
        }
        job2 = launch {
            delay(3000L)
        }
        job3 = launch {
            delay(5000L)
        }
    }

}