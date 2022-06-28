package io.github.caoshen.androidadvance.jetpack.compose.util

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.toLowerCase
import java.util.*

/**
 * Android 后台运行白名单，优雅实现保活
 * https://www.jianshu.com/p/4b5b9ca2b631
 */

/**
 * 是否忽略电池优化
 */
@RequiresApi(api = Build.VERSION_CODES.M)
fun Activity.isIgnoringBatteryOptimizations(): Boolean {
    val powerManager: PowerManager? = getSystemService(Context.POWER_SERVICE) as PowerManager?
    return powerManager?.isIgnoringBatteryOptimizations(packageName) ?: false
}

/**
 * 打开电池优化设置
 */
@RequiresApi(api = Build.VERSION_CODES.M)
fun Activity.requestIgnoreBatteryOptimizations() {
    try {
        startActivity(
            Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                .setData(Uri.parse("package:$packageName"))
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * 跳转到指定应用的首页
 */
fun Activity.showActivity(packageName: String) {
    startActivity(packageManager.getLaunchIntentForPackage(packageName))
}

/**
 * 跳转到指定应用的指定界面
 */
fun Activity.showActivity(packageName: String, activityDir: String) {
    val intent = Intent().setComponent(ComponentName(packageName, activityDir))
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    startActivity(intent)
}

/**
 * 华为
 */
fun isHuawei(): Boolean {
    return if (Build.BRAND == null) {
        false
    } else {
        Build.BRAND.toLowerCase() == "huawei" || Build.BRAND.toLowerCase() == "honor"
    }
}

private fun Activity.goHuaweiSetting() {
    try {
        showActivity(
            "com.huawei.systemmanager",
            "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"
        )
    } catch (e: java.lang.Exception) {
        showActivity(
            "com.huawei.systemmanager",
            "com.huawei.systemmanager.optimize.bootstart.BootStartActivity"
        )
    }
}

/**
 * 小米
 */
fun isXiaomi(): Boolean {
    return Build.BRAND != null && (Build.BRAND.toLowerCase() == "xiaomi"
            || Build.BRAND.lowercase(Locale.getDefault()) == "redmi")
}

fun Activity.goXiaomiSetting() {
    showActivity(
        "com.miui.securitycenter",
        "com.miui.permcenter.autostart.AutoStartManagementActivity"
    )
}

/**
 * OPPO
 */
fun isOPPO(): Boolean {
    return Build.BRAND != null && Build.BRAND.toLowerCase() == "oppo"
}

private fun Activity.goOPPOSetting() {
    try {
        showActivity("com.coloros.phonemanager")
    } catch (e1: java.lang.Exception) {
        try {
            showActivity("com.oppo.safe")
        } catch (e2: java.lang.Exception) {
            try {
                showActivity("com.coloros.oppoguardelf")
            } catch (e3: java.lang.Exception) {
                showActivity("com.coloros.safecenter")
            }
        }
    }
}

/**
 * VIVO
 */
fun isVIVO(): Boolean {
    return Build.BRAND != null && Build.BRAND.toLowerCase() == "vivo"
}

private fun Activity.goVIVOSetting() {
    showActivity("com.iqoo.secure")
}

/**
 * 魅族
 */
fun isMeizu(): Boolean {
    return Build.BRAND != null && Build.BRAND.toLowerCase() == "meizu"
}

private fun Activity.goMeizuSetting() {
    showActivity("com.meizu.safe")
}

/**
 * 三星
 */
fun isSamsung(): Boolean {
    return Build.BRAND != null && Build.BRAND.toLowerCase() == "samsung"
}

private fun Activity.goSamsungSetting() {
    try {
        showActivity("com.samsung.android.sm_cn")
    } catch (e: java.lang.Exception) {
        showActivity("com.samsung.android.sm")
    }
}

/**
 * 乐视
 */
fun isLeTV(): Boolean {
    return Build.BRAND != null && Build.BRAND.toLowerCase() == "letv"
}

private fun Activity.goLetvSetting() {
    showActivity(
        "com.letv.android.letvsafe",
        "com.letv.android.letvsafe.AutobootManageActivity"
    )
}

/**
 * 锤子
 */
fun isSmartisan(): Boolean {
    return Build.BRAND != null && Build.BRAND.toLowerCase() == "smartisan"
}

private fun Activity.goSmartisanSetting() {
    showActivity("com.smartisanos.security")
}
