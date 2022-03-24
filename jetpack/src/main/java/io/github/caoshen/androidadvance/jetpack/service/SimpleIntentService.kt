package io.github.caoshen.androidadvance.jetpack.service

import android.app.IntentService
import android.content.Intent
import android.util.Log

class SimpleIntentService : IntentService("SimpleIntentService") {
    companion object {
        const val TAG = "SimpleIntentService"
    }
    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent")
    }
}