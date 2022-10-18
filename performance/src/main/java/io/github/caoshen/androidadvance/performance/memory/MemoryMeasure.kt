package io.github.caoshen.androidadvance.performance.memory

import android.app.ActivityManager
import android.content.Context
import android.util.Log

const val M = 1024 * 1024
const val TAG = "XMemory"

/**
 * https://juejin.cn/post/7098257692828893220
 * Android 内存基础知识
 */

//E/XMemory: Dalvik MaxMemory:256M
//E/XMemory: Dalvik MemoryClass:256
//E/XMemory: Dalvik LargeMemoryClass:512
//E/XMemory: system total memory:7157M
//E/XMemory: system available memory:2544M
//E/XMemory: system whether low memory status:false
//E/XMemory: system low memory threshold:216M
fun getMaxMemoryInfo(appContext: Context) {
    val runtime = Runtime.getRuntime()
    val maxMemory = runtime.maxMemory()
    Log.e(TAG, "Dalvik MaxMemory:${maxMemory / M}M")

    val activityManager = appContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    // 系统为每个app分配的内存，调用了 dalvik.vm.heapsize
    Log.e(TAG, "Dalvik MemoryClass:${activityManager.memoryClass}")
    // 系统为每个app分配的最大内存，超过就导致OOM
    Log.e(TAG, "Dalvik LargeMemoryClass:${activityManager.largeMemoryClass}")

    val info = ActivityManager.MemoryInfo()
    activityManager.getMemoryInfo(info)
    Log.e(TAG, "system total memory:${info.totalMem / M}M")
    Log.e(TAG, "system available memory:${info.availMem / M}M")
    Log.e(TAG, "system whether low memory status:${info.lowMemory}")
    Log.e(TAG, "system low memory threshold:${info.threshold / M}M")
}