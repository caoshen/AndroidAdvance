package io.github.caoshen.androidadvance.kotlin.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    val userInfo = getUserInfo()
    val friendList = getFriendList(userInfo)
    val feedList = getFeedList(friendList)
    println(feedList)
}

suspend fun getUserInfo(): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "BoyCoder"
}

suspend fun getFriendList(user: String): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "friendList"
}

suspend fun getFeedList(friendList: String): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "feedList"
}