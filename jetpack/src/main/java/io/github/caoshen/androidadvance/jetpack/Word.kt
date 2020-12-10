package io.github.caoshen.androidadvance.jetpack

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author caoshen
 * @date 2020/12/3
 */
@Entity(tableName = "word_table")
data class Word(
        @PrimaryKey
        @ColumnInfo(name = "word")
        val word: String)
