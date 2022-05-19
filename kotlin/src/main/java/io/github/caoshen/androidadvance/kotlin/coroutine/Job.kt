package io.github.caoshen.androidadvance.kotlin.coroutine

import kotlinx.coroutines.*
import kotlin.math.log
import kotlin.system.measureTimeMillis

fun main1() = runBlocking {
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
Thread:${Thread.currentThread().name}, time:${System.currentTimeMillis()}
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

fun main5() = runBlocking {
    val deferred = async {
        logX("Coroutine start.")
        delay(1000)
        logX("Coroutine end.")
        "Coroutine result"
    }
    val result = deferred.await()
    logX("Result = $result")
    logX("Process end!")
}

fun main6() = runBlocking {
    val parentJob: Job
    var job1 : Job? = null
    var job2 : Job? = null
    var job3 : Job? = null

    parentJob = launch {
        job1 = launch {
            logX("coroutine start.")
            delay(1000)
            logX("coroutine end.")
        }
        job2 = launch {
            logX("coroutine start.")
            delay(3000)
            logX("coroutine end.")
        }
        job3 = launch {
            logX("coroutine start.")
            delay(5000)
            logX("coroutine end.")
        }
    }

    delay(500)

    parentJob.children.forEachIndexed { index, job ->
        when(index) {
            0 -> logX("job === job1 is ${job === job1}")
            1 -> logX("job === job2 is ${job === job2}")
            2 -> logX("job === job3 is ${job === job3}")
        }
    }

    parentJob.cancel()
    logX("Process end.")
}

fun main7() = runBlocking {

    suspend fun getResult1(): String {
        delay(1000)
        return "Result1"
    }

    suspend fun getResult2(): String {
        delay(1000)
        return "Result2"
    }

    suspend fun getResult3(): String {
        delay(1000)
        return "Result3"
    }

    val results = mutableListOf<String>()
    val time = measureTimeMillis {
        val deferred1 = async {
            getResult1()
        }
        val deferred2 = async {
            getResult2()
        }
        val deferred3 = async {
            getResult3()
        }
        results.add(deferred1.await())
        results.add(deferred2.await())
        results.add(deferred3.await())
    }
    println("results:$results")
    println("time:$time")
}

fun main() = runBlocking {
    val job1 = launch {
        println("job1 start.")
        delay(1000)
        println("job1 end.")
    }
    job1.join()

    job1.log()

    val job2 = launch(job1) {
        println("job2 start.")
        delay(1000)
        println("job2 end.")
    }
    job2.join()

    println("process end.")
}