package io.github.caoshen.androidadvance.jetpack.workmanager

import android.content.Context
import android.os.SystemClock
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class CompressWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        println("do compress...")
        SystemClock.sleep(3000)
        println("compress success")
        return Result.success()
    }
}