package io.github.caoshen.androidadvance.kotlin.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import java.lang.Appendable
import kotlin.concurrent.thread
import kotlin.coroutines.coroutineContext

//interface List<T> {
//    operator fun get(index: Int): T
//}
//
//class StringList : List<String> {
//    override fun get(index: Int): String {
//        TODO("Not yet implemented")
//    }
//}

//class ArrayList<T> : List<T> {
//    override fun get(index: Int): T {
//        TODO("Not yet implemented")
//    }
//}

//interface Comparable<T> {
//    fun compareTo(other: T): Int
//}
//
//class String : Comparable<String> {
//    override fun compareTo(other: String): Int {
//        TODO("Not yet implemented")
//    }
//}

fun <T : Number> oneHalf(num: T): Double {
    return num.toDouble() / 2.0
}


//fun <T : Number> List<T>.sum(): T

fun <T : Comparable<T>> max(first: T, second: T): T {
    return if (first > second) {
        first
    } else {
        second
    }
}

fun <T> ensureTrailingPeriod(seq: T) where T : CharSequence, T : Appendable {
    if (!seq.endsWith('.')) {
        seq.append('.')
    }
}

class Processor<T> {
    fun process(arg: T) {
        arg.hashCode()
    }
}

fun <T : Any> saveSomething(some : T) {
    val set = sortedSetOf<T>()
    set.add(some)
}

suspend fun testContext() = coroutineContext

fun main() = runBlocking {
    println(testContext())
//    val nullableProcessor = Processor<String?>()
//    nullableProcessor.process(null)
//    saveSomething("null")
}

/**
 * Add 1
Get 1
Add 2
Get 2
Add 3
Get 3
Add 4
Get 4
 */
//fun main() = runBlocking {
//    val sequence = getSequence()
//    printSequence(sequence)
//    val channel = getProducer(this)
//    testConsumer(channel)
//}

//fun main() = runBlocking(Dispatchers.IO) {
//    repeat(3) {
//        launch {
//            repeat(3) {
//                println(Thread.currentThread().name)
//                delay(100)
//            }
//        }
//    }
//
//    delay(500L)
//}

//fun main() = runBlocking {
//    GlobalScope.launch {
//        println("Coroutine started!")
//        delay(1000L)
//        println("Hello World!")
//    }
//    println("After launch!")
//    Thread.sleep(2000L)
//    println("Process end!")

//    runBlocking {
//        println("Coroutine started!")
//        delay(1000L)
//        println("Hello World!")
//    }
//    println("After launch!")
//    Thread.sleep(2000L)
//    println("Process end!")

//    val result = runBlocking {
//        delay(1000L)
//        return@runBlocking "Coroutine done!"
//    }
//
//    println("Result is:$result")
//
//    runBlocking {
//        async {
//
//        }
//    }

// coroutine:4 created
//    val deferred: Deferred<String> = async {
//         coroutine:4 RUNNING on thread main
//        println("In async:${Thread.currentThread().name}")
//        delay(1000L)
//        println("In aysnc after delay!")
//        return@async "Task completed!"
//    }
// coroutine:1 on thread main Running
//    println("start runblocking")
//    val result = deferred.await()
//    println("finish await ")
// coroutine:1 on thread main SUSPENDED
//    delay(2000L)
//    println("finish delay ")

//}




