package io.github.caoshen.androidadvance.kotlin.coroutine

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.coroutines.coroutineContext

fun main2() = runBlocking {
    val user = getUserInfoIo()
    logX(user)
}

fun mainRunIo() = runBlocking(Dispatchers.IO) {
    val user = getUserInfoIo()
    logX(user)
}

fun mainRunDefault() = runBlocking(Dispatchers.Default) {
    val user = getUserInfoIo()
    logX(user)
}

val mySingleDispatcher = Executors.newSingleThreadExecutor {
    Thread(it, "mySingleThread").apply {
        isDaemon = true
    }
}
    .asCoroutineDispatcher()

fun mainRunSingle() = runBlocking(mySingleDispatcher) {
    val user = getUserInfoIo()
    logX(user)
}

fun mainLaunchDefault() = runBlocking {
    logX("Before launch") // 1
    launch {
        logX("In launch") // 2
        delay(1000)
        logX("End launch") // 3
    }
    logX("After launch") // 4
}

fun mainLaunchUnconfined() = runBlocking {
    logX("Before launch") // 1
    launch(Dispatchers.Unconfined) {
        logX("In launch") // 2
        delay(1000)
        logX("End launch") // 3
    }
    logX("After launch") // 4
}

fun mainScope() = runBlocking {
    val scope = CoroutineScope(Job())

    scope.launch {
        logX("first start")
        delay(1000)
        logX("first end")
    }

    scope.launch {
        logX("second start")
        delay(1000)
        logX("second end")
    }

    scope.launch {
        logX("third start")
        delay(1000)
        logX("third end")
    }

    delay(500)
    scope.cancel()
    delay(1000)
}

suspend fun mainSuspend() {
    val user = getUserInfoIo()
    logX(user)
}

@OptIn(ExperimentalStdlibApi::class)
fun mainContextPlus() = runBlocking {
    val scope = CoroutineScope(Job() + mySingleDispatcher)

    scope.launch {
        logX(coroutineContext[CoroutineDispatcher] == mySingleDispatcher)
        delay(500)
        logX("first end")
    }

    delay(500)
    scope.cancel()
    delay(1000)
}

@OptIn(ExperimentalStdlibApi::class)
fun mainCoroutineName() = runBlocking {
    val scope = CoroutineScope(Job() + mySingleDispatcher)

    scope.launch(CoroutineName("MyFirstCoroutine")) {
        logX(coroutineContext[CoroutineDispatcher] == mySingleDispatcher)
        delay(500)
        logX("first end")
    }

    delay(500)
    scope.cancel()
    delay(1000)
}

@OptIn(ExperimentalStdlibApi::class)
suspend fun mainExceptionHandler() {
    val scope = CoroutineScope(Job() + mySingleDispatcher)
    val handler = CoroutineExceptionHandler { _, throwable ->
        println("catch exception $throwable")
    }
    val job = scope.launch(handler) {
        logX(coroutineContext[CoroutineDispatcher] == mySingleDispatcher)
        val str: String? = null
        str!!.length
        logX("first end")
    }
    job.join()
}

suspend fun testCoroutineContext() = coroutineContext

fun main() = runBlocking {
    print(testCoroutineContext())
}


suspend fun getUserInfoIo(): String {
    logX("Before IO Context.")
    withContext(Dispatchers.IO) {
        logX("In IO Context.")
        delay(1000)
    }
    logX("After IO Context.")
    return "BoyCoder"
}