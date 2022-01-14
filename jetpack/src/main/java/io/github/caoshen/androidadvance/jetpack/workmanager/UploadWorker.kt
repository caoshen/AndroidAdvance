package io.github.caoshen.androidadvance.jetpack.workmanager

import android.content.Context
import android.os.SystemClock
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.withContext

/**
 * @author caoshen
 * @date 2020/12/30
 */
class UploadWorker(appContext: Context, workerParams: WorkerParameters) :
        CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        uploadImages()
        return Result.success()
    }

    private fun uploadImages() {
        println("start upload...")
        SystemClock.sleep(3000)
        println("upload success")
    }
}