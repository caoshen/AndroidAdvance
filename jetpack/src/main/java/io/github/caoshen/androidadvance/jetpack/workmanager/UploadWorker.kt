package io.github.caoshen.androidadvance.jetpack.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * @author caoshen
 * @date 2020/12/30
 */
class UploadWorker(appContext: Context, workerParams: WorkerParameters) :
        Worker(appContext, workerParams) {
    override fun doWork(): Result {
        uploadImages()

        return Result.success()
    }

    private fun uploadImages() {


    }
}