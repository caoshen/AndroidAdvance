package io.github.caoshen.androidadvance.kotlin.chapter21select

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

/**
 * select the fastest result from 2 suspend functions
 */
fun main() = runBlocking {
    suspend fun getCacheInfo(productId: String): ProductCache? {
        delay(100L)
        return ProductCache(productId, 9.9)
    }

    suspend fun getCacheInfo2(productId: String): ProductCache? {
        delay(50L)
        return ProductCache(productId, 10.0)
    }

    suspend fun getCacheInfo2000(productId: String): ProductCache? {
        delay(2000L)
        return ProductCache(productId, 9.9)
    }

    suspend fun getNetworkInfo(productId: String): ProductCache? {
        delay(200L)
        return ProductCache(productId, 9.8)
    }

    fun updateUI(product: ProductCache) {
        println("${product.productId}===${product.price}")
    }

    val startTime = System.currentTimeMillis()
    val productId = "xxxId"

    val cacheDeferred = async {
        getCacheInfo(productId)
    }

    val cacheDeferred2 = async {
        getCacheInfo2(productId)
    }

    val latestDeferred = async {
        getNetworkInfo(productId)
    }

    val cache2000Deferred = async {
        getCacheInfo2000(productId)
    }

    val product = select<ProductCache?> {
        cacheDeferred.onAwait {
            it?.copy(isCache = true)
        }

        cacheDeferred2.onAwait {
            it?.copy(isCache = true)
        }

        latestDeferred.onAwait {
            it?.copy(isCache = false)
        }
    }

    if (product != null) {
        updateUI(product)
        println("Time cost: ${System.currentTimeMillis() - startTime}")
    }

    if (product != null && product.isCache) {
        val latest = latestDeferred.await() ?: return@runBlocking
        updateUI(latest)
        println("Time cost: ${System.currentTimeMillis() - startTime}")
    }
}

//output

//xxxId===9.9
//Time cost: 134
//xxxId===9.8
//Time cost: 220

// output 2000 cache
//xxxId===9.8
//Time cost: 232