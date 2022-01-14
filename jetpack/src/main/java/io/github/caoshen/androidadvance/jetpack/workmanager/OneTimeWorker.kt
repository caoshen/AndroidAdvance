package io.github.caoshen.androidadvance.jetpack.workmanager

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import java.lang.RuntimeException
import java.util.*
import kotlin.random.Random

class OneTimeWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {


    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {
        val message = RuntimeException("onetime").stackTrace
        println(Arrays.toString(message))
        val userName = inputData.getString("userName")
        val random = Random.nextInt(0, 2)
        for (i in 1..10) {
            delay(1000)
            println("delay $i...")
        }
        val outputData = Data.Builder()
            .putInt("random", random)
            .build()
//        return if (random == 0 ) {
//            Result.Success(outputData)
//        } else {
//            Result.failure(outputData)
//        }
        return Result.success(outputData)
    }
}