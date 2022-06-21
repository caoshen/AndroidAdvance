package io.github.caoshen.androidadvance.jetpack.compose.data.entity

import androidx.room.RoomDatabase
import io.github.caoshen.androidadvance.jetpack.compose.data.TodoDao

abstract class TodoDatabase: RoomDatabase() {

    abstract fun todoDao(): TodoDao
}