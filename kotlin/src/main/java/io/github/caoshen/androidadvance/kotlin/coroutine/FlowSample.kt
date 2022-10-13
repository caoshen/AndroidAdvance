package io.github.caoshen.androidadvance.kotlin.coroutine

import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.IllegalStateException

fun main() {
//    flowEmit()
//    flowOfFun()
//    flow2list()
//    onStartFun()
    onStartFun2()
//    onCompleteFun()
//    cancelOnCompleteFun()
//    flowFirst()
//    flowSingle()
//    flowFold()
//    flowReduce()
}

fun flowEmit() = runBlocking {
    flow {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
    }
        .filter {
            it > 2
        }
        .map {
            it * 2
        }
        .take(2)
        .collect {
            // 6 8
            println(it)
        }
}

fun flowFirst() = runBlocking {
    val first = flow {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
    }
        .filter {
            it > 2
        }
        .map {
            it * 2
        }
        .take(2)
        .first()
    // 6
    println(first)
}

fun flowSingle() = runBlocking {
    val single = flow {
        emit(3)
    }
        .filter {
            it > 2
        }
        .map {
            it * 2
        }
        .take(2)
        .single()
    // 6
    println(single)
}

fun flowFold() = runBlocking {
    val fold = flow {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
    }.filter {
        it > 2
    }.map {
        it * 2
    }.take(2)
        .fold(0) { acc, value ->
            acc + value
        }

    // 14
    println(fold)
}

fun flowReduce() = runBlocking {
    val reduce = flow {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
    }.filter {
        it > 2
    }.map {
        it * 2
    }.take(2)
        .reduce { acc, value ->
            acc + value
        }
    // 14
    println(reduce)
}

fun flowOfFun() = runBlocking {
    flowOf(1, 2, 3, 4, 5)
        .filter { it > 2 }
        .map { it * 2 }
        .take(2)
        .collect {
            // 6 8
            println(it)
        }

    listOf(1, 2, 3, 4, 5)
        .filter { it > 2 }
        .map { it * 2 }
        .take(2)
        .forEach {
            // 6 8
            println(it)
        }
}

fun flow2list() = runBlocking {
    flowOf(1, 2, 3, 4, 5)
        // flow to list
        .toList()
        .filter { it > 2 }
        .map { it * 2 }
        .take(2)
        .forEach {
            println(it)
        }

    listOf(1, 2, 3, 4, 5)
        // list as flow
        .asFlow()
        .filter { it > 2 }
        .map { it * 2 }
        .take(2)
        .collect {
            println(it)
        }
}

// flow lifecycle
fun onStartFun() = runBlocking {
    flowOf(1, 2, 3, 4, 5)
        .filter {
            println("filter: $it")
            it > 2
        }
        .map {
            println("map: $it")
            it * 2
        }
        .take(2)
        .onStart {
            println("onStart")
        }
        .collect {
            println("collect: $it")
        }
}

fun onStartFun2() = runBlocking {
    flowOf(1, 2, 3, 4, 5)
        .take(2)
        .filter {
            println("filter: $it")
            it > 2
        }
        .map {
            println("map: $it")
            it * 2
        }
        .onStart {
            println("onStart")
        }
        .collect {
            println("collect: $it")
        }
}

fun onCompleteFun() = runBlocking {
    flowOf(1, 2, 3, 4, 5)
        .onCompletion {
            println("onCompletion")
        }
        .filter {
            println("filter: $it")
            it > 2
        }
        .take(2)
        .collect {
            println("collect: $it")
        }
}

fun cancelOnCompleteFun() = runBlocking {
    launch {
        flow {
            emit(1)
            emit(2)
            emit(3)
        }
            // collect: 1
            //collect: 2
            //cancel
            //onCompletion first: kotlinx.coroutines.JobCancellationException
            .onCompletion {
                println("onCompletion first: $it")
            }
            .collect {
                println("collect: $it")
                if (it == 2) {
                    // cancel flow
                    cancel()
                    println("cancel")
                }
            }
    }

    delay(1000)

    flowOf(4, 5, 6)
//    collect: 4
//    onCompletion second: java.lang.IllegalStateException
        .onCompletion {
            println("onCompletion second: $it")
        }
        .collect {
            println("collect: $it")
            throw IllegalStateException()
        }

}

fun flowCatch() = runBlocking {
    val flow = flow {
        emit(1)
        emit(2)
        throw IllegalStateException()
        emit(3)
    }
    flow.map {
        it * 2
    }.catch {
        println("catch: $it")
    }.collect {
        println(it)
    }
}

fun flowCatchDownStream() = runBlocking {
    val flow = flow {
        emit(1)
        emit(2)
        emit(3)
    }

    flow.map {
        it * 2
    }.catch {
        println("catch: $it")
    }.filter {
        it / 0 > 1
    }.collect {
        println(it)
    }
}

fun flowTryCatch() = runBlocking {
    flowOf(4, 5, 6)
        .onCompletion {
            println("onCompletion second: $it")
        }
        .collect {
            try {
                println("collect: $it")
                throw IllegalStateException()
            } catch (e: Exception) {
                println("cache $e")
            }
        }
}