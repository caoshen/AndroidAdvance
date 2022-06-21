package io.github.caoshen.androidadvance.jetpack.compose.data

import androidx.room.*
import io.github.caoshen.androidadvance.jetpack.compose.data.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun loadAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM todo_table WHERE id = :taskId")
    fun searchTask(taskId: Int): Flow<Task>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTasks()
}