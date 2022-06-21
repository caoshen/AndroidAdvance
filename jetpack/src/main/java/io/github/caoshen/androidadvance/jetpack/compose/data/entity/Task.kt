package io.github.caoshen.androidadvance.jetpack.compose.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.caoshen.androidadvance.jetpack.compose.util.AppHolder.DB_TABLE

@Entity(tableName = DB_TABLE)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val desc: String,
    val isDone: Boolean
)