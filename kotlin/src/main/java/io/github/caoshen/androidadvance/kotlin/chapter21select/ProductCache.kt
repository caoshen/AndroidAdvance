package io.github.caoshen.androidadvance.kotlin.chapter21select

data class ProductCache(
    val productId: String,
    val price: Double,
    val isCache: Boolean = false
)
