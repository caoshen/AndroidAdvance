package io.github.caoshen.androidadvance.jetpack.livedata

import android.util.Log
import androidx.lifecycle.LiveData
import io.github.caoshen.androidadvance.jetpack.model.StockManager
import java.math.BigDecimal

class StockLiveData(symbol: String) : LiveData<BigDecimal>() {
    companion object {
        private const val TAG = "StockLiveData"
    }

    private val stockManager = StockManager(symbol)

    private val listener = { price: BigDecimal ->
        value = price
    }

    override fun onActive() {
        Log.d(TAG, "onActive")
        stockManager.requestPriceUpdates(listener)
    }

    override fun onInactive() {
        Log.d(TAG, "onInactive")
        stockManager.removeUpdates(listener)
    }
}