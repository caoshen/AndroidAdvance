package io.github.caoshen.androidadvance.jetpack.workmanager

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*

/**
 * @author caoshen
 * @date 2020/12/30
 */
class WorkManagerActivity : AppCompatActivity() {

    companion object {
        const val TAG = "WorkManagerActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")

//        val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<UploadWorker>()
//            .build()

        val uploadWorkRequest = buildConstraintsRequest()

        val workManager: WorkManager = WorkManager.getInstance(this)
        workManager.enqueue(uploadWorkRequest)

//        2021-12-28 17:20:33.989 9202-9202 E/WorkManagerActivity: onCreate
//        2021-12-28 17:20:34.048 9202-9202 E/WorkManagerActivity: ENQUEUED
//        2021-12-28 17:20:34.051 9202-9202 E/WorkManagerActivity: RUNNING
//        2021-12-28 17:20:44.176 9202-9202 E/WorkManagerActivity: SUCCEEDED:0
        workManager.getWorkInfoByIdLiveData(uploadWorkRequest.id)
            .observe(this) { workInfo ->
                when (workInfo.state) {
                    WorkInfo.State.ENQUEUED -> {
                        Log.e(TAG, "ENQUEUED")
                    }
                    WorkInfo.State.RUNNING -> {
                        Log.e(TAG, "RUNNING")
                    }
                    WorkInfo.State.SUCCEEDED -> {
                        val outputData = workInfo.outputData
                        val random = outputData.getInt("random", -1)
                        Log.e(TAG, "SUCCEEDED:$random")
                    }
                    WorkInfo.State.FAILED -> {
                        val outputData = workInfo.outputData
                        val random = outputData.getInt("random", -1)
                        Log.e(TAG, "FAILED:$random")
                    }
                    WorkInfo.State.BLOCKED -> {
                        Log.e(TAG, "BLOCKED")
                    }
                    WorkInfo.State.CANCELLED -> {
                        Log.e(TAG, "CANCELLED")
                    }
                }
            }
    }

    fun buildOneTimeRequest(): WorkRequest {
        return OneTimeWorkRequestBuilder<OneTimeWorker>()
            .build()
    }

    /**
     * input data
     */
    fun buildInputDataRequest(): WorkRequest {
        val data = Data.Builder()
            .putString("userName", "Bugra")
            .build()

        return OneTimeWorkRequestBuilder<OneTimeWorker>()
            .setInputData(data)
            .build()
    }

    fun buildConstraintsRequest(): WorkRequest {
        val constraints = buildConstraints()
        return OneTimeWorkRequestBuilder<OneTimeWorker>()
            .setConstraints(constraints)
            .build()
    }

    /**
     * constraints
     */
    fun buildConstraints(): Constraints {
        return Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(false)
            .build()
    }
}