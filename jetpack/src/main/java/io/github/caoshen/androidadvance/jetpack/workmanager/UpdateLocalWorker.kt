package io.github.caoshen.androidadvance.jetpack.workmanager

import android.content.Context
import android.os.SystemClock
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class UpdateLocalWorker(context: Context, params: WorkerParameters)
    : CoroutineWorker(context, params){

    override suspend fun doWork(): Result {
        println("update local resource...")
        SystemClock.sleep(3000L)
        println("update finish")
        return Result.success()
    }
}