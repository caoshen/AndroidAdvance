package io.github.caoshen.androidadvance.kotlin.oop

import android.util.Log

class Person2(val name: String) {
    companion object {
        const val TAG = "Person2"
    }

    var age: Int = 0
        private set(value: Int) {
            log(value)
            field = value
        }

    private fun log(value: Int) {
        Log.d(TAG, "val:$value")
    }
}