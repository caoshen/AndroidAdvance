package io.github.caoshen.androidadvance.kotlin.chapter21select

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

/**
 * select the fastest result from 2 suspend functions
 */
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val productId = "xxxId"

    suspend fun getCacheInfo(productId: String): Product? {
        delay(100L)
        return Product(productId, 9.9)
    }

    suspend fun getCacheInfo2000(productId: String): Product? {
        delay(2000L)
        return Product(productId, 9.9)
    }

    suspend fun getNetworkInfo(productId: String): Product? {
        delay(200L)
        return Product(productId, 9.8)
    }

    fun updateUI(product: Product) {
        println("${product.productId}===${product.price}")
    }

    val product = select<Product?> {
        async {
            getCacheInfo2000(productId)
        }.onAwait {
            it
        }

        async {
            getNetworkInfo(productId)
        }.onAwait {
            it
        }
    }

    if (product != null) {
        updateUI(product)
        println("Time cost: ${System.currentTimeMillis() - startTime}")
    }

}



//output
//xxxId===9.9
//Time cost: 124

//output2000
//xxxId===9.8
//Time cost: 233

