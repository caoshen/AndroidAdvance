package io.github.caoshen.androidadvance.kotlin.chapter21select

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    suspend fun getCacheInfo(productId: String): Product? {
        delay(100L)
        return Product(productId, 9.9)
    }

    suspend fun getNetworkInfo(productId: String): Product? {
        delay(200L)
        return Product(productId, 9.8)
    }

    fun updateUI(product: Product) {
        println("${product.productId}==${product.price}")
    }

    val startTime = System.currentTimeMillis()
    val productId = "xxxId"

    val cacheInfo = getCacheInfo(productId)
    if (cacheInfo != null) {
        updateUI(cacheInfo)
        println("Time cost: ${System.currentTimeMillis() - startTime}")
    }

    val networkInfo = getNetworkInfo(productId)
    if (networkInfo != null) {
        updateUI(networkInfo)
        println("Time cost: ${System.currentTimeMillis() - startTime}")
    }
}

data class Product(
    val productId: String,
    val price: Double
)

// output
//xxxId==9.9
//Time cost: 114
//xxxId==9.8
//Time cost: 317