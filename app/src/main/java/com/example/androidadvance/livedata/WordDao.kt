package com.example.androidadvance.livedata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * @author caoshen
 * @date 2020/12/3
 */
@Dao
interface WordDao {
    // Flow notify
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizeWords(): Flow<List<Word>>

    /**
     * ignore 如果有冲突就不插入
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}