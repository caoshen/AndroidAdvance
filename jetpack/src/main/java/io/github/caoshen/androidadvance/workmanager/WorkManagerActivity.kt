package io.github.caoshen.androidadvance.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest

/**
 * @author caoshen
 * @date 2020/12/30
 */
class WorkManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<UploadWorker>()
                .build()

        val workManager: WorkManager = WorkManager.getInstance(this)
        workManager.enqueue(uploadWorkRequest)
    }
}