package com.example.androidadvance.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.util.Log
import com.example.androidadvance.utils.DeviceUtils

class DeviceProvider : ContentProvider() {
    companion object {
        private const val TAG = "DeviceProvider"
        private const val PROVIDER_AUTHORITY = "com.example.androidadvance.provider.device"
        private const val PATH_DEVICE_ID = "path_device_id"
        private const val CODE_DEVICE_ID = 1
    }

    private lateinit var mUriMatcher: UriMatcher

    override fun onCreate(): Boolean {
        Log.d(TAG, "onCreate")
        mUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        mUriMatcher.addURI(PROVIDER_AUTHORITY, PATH_DEVICE_ID, CODE_DEVICE_ID)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        Log.d(TAG, "query")
        val code = mUriMatcher.match(uri)
        var deviceId = ""
        when (code) {
            CODE_DEVICE_ID -> {
                deviceId = DeviceUtils.getDeviceId()
            }
            else -> {
                Log.e(TAG, "unknown code:$code")
            }
        }
        return ResultCursor(deviceId)
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    class ResultCursor(value: String) : MatrixCursor(COLUMN_NAME, 1) {
        companion object {
            private const val TAG = "ResultCursor"
            private val COLUMN_NAME: Array<String> = arrayOf("device_id")
        }

        init {
            addRow(arrayOf(value))
        }
    }
}