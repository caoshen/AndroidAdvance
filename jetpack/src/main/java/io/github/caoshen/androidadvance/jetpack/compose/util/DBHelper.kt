package io.github.caoshen.androidadvance.jetpack.compose.util

import androidx.room.Room
import io.github.caoshen.androidadvance.jetpack.compose.data.TodoDao
import io.github.caoshen.androidadvance.jetpack.compose.data.entity.TodoDatabase

object DBHelper {

    private fun provideRoomDb(): TodoDatabase = Room.databaseBuilder(
        AppHolder.appContext,
        TodoDatabase::class.java,
        AppHolder.DB_NAME
    ).build()

    fun provideDao(todoDatabase: TodoDatabase = provideRoomDb()): TodoDao = todoDatabase.todoDao()
}