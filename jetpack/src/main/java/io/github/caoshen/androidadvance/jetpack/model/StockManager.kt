package io.github.caoshen.androidadvance.jetpack.model

import java.math.BigDecimal

class StockManager(symbol: String) {

    fun requestPriceUpdates(listener: (BigDecimal) -> Unit) {
        val price = BigDecimal(1000)
        listener.invoke(price)
    }

    fun removeUpdates(listener: (BigDecimal) -> Unit) {
        val price = BigDecimal(0)
        listener.invoke(price)
    }
}