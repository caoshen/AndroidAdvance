package io.github.caoshen.androidadvance.jetpack.compose.util

import android.app.Application

object AppHolder {
    const val DB_NAME = "todo_db"
    const val DB_TABLE = "todo_table"

    lateinit var appContext: Application
}
