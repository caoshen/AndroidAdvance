package io.github.caoshen.androidadvance.jetpack.compose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.caoshen.androidadvance.jetpack.compose.data.entity.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {

    abstract fun todoDao(): TodoDao
}