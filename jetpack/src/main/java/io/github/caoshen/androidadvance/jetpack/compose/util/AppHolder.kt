package io.github.caoshen.androidadvance.jetpack.compose.util

import android.app.Application

object AppHolder {
    // time
    const val SPLASH_DELAY = 3000L

    // route
    const val SPLASH_SCREEN = "splash"
    const val HOME_SCREEN = "home"
    const val TASK_DETAIL_BASE: String = "task_detail/"
    const val TASK_ARG_KEY = "taskId"
    const val TASK_DETAIL_SCREEN = "${TASK_DETAIL_BASE}{${TASK_ARG_KEY}}"

    // database
    const val DB_NAME = "todo_db"
    const val DB_TABLE = "todo_table"

    lateinit var appContext: Application
}
