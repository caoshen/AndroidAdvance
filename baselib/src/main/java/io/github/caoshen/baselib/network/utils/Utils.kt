package io.github.caoshen.baselib.network.utils

import com.jeremyliao.liveeventbus.LiveEventBus

const val SHOW_TOAST = "show_toast"

fun toast(message: String) {
    LiveEventBus.get<String>(SHOW_TOAST).post(message)
}