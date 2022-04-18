package io.github.caoshen.androidadvance.kotlin.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

/**
 * 13 什么是协程思维模型
 */

/**
 * 普通程序
 */
//fun main() {
//    val list = getList()
//    printList(list)
//}

fun getList(): List<Int> {
    val list = mutableListOf<Int>()
    println("Add 1")
    list.add(1)
    println("Add 2")
    list.add(2)
    println("Add 3")
    list.add(3)
    println("Add 4")
    list.add(4)

    return list
}

fun printList(list: List<Int>) {
    val i = list[0]
    println("Get$i")
    val j = list[1]
    println("Get$j")
    val k = list[2]
    println("Get$k")
    val m = list[3]
    println("Get$m")
}

/**
 * 协程 yield
 */
//    Add 1
//    Get 1
//    Add 2
//    Get 2
//    Add 3
//    Get 3
//    Add 4
//    Get 4

//fun main() = runBlocking {
//    // yield 挂起，让步
//    val sequence = getSequence()
//    printSequence(sequence)
//}

/**
 * Sequence yield
 */
fun getSequence() = sequence<Int> {
    println("Add 1")
    yield(1)
    println("Add 2")
    yield(2)
    println("Add 3")
    yield(3)
    println("Add 4")
    yield(4)
}

fun printSequence(sequence: Sequence<Int>) {
    val iterator = sequence.iterator()
    val i = iterator.next()
    println("Get $i")
    val j = iterator.next()
    println("Get $j")
    val k = iterator.next()
    println("Get $k")
    val l = iterator.next()
    println("Get $l")
}

/**
 * 协程 channel
 */
//Send:1
//Receive:1
//Send:2
//Receive:2
//Send:3
//Receive:3
//Send:4
//Receive:4

//fun main() = runBlocking {
//    val channel = getProducer(this)
//    testConsumer(channel)
//}

/**
 * Channel
 */
fun getProducer(scope: CoroutineScope) = scope.produce<Int> {
    println("Send:1")
    send(1)
    println("Send:2")
    send(2)
    println("Send:3")
    send(3)
    println("Send:4")
    send(4)
}

suspend fun testConsumer(channel: ReceiveChannel<Int>) {
    delay(100)
    val i = channel.receive()
    println("Receive:$i")
    delay(100)
    val j = channel.receive()
    println("Receive:$j")
    delay(100)
    val k = channel.receive()
    println("Receive:$k")
    delay(100)
    val m = channel.receive()
    println("Receive:$m")
}

/**
 * 启动两个线程
 */

//main
//Thread-0
//fun main() {
//    println(Thread.currentThread().name)
//
//    thread {
//        println(Thread.currentThread().name)
//        Thread.sleep(100)
//    }
//    Thread.sleep(1000L)
//}


/**
 * 启动两个协层，需要配置 VM 参数：-Dkotlinx.coroutines.debug
 */

//main @coroutine#1
//main @coroutine#2
fun main() = runBlocking {
    println(Thread.currentThread().name)

    launch {
        println(Thread.currentThread().name)
        Thread.sleep(100L)
    }

    Thread.sleep(1000L)
}