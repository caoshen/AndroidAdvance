package io.github.caoshen.androidadvance.jetpack.compose.data.repo

import io.github.caoshen.androidadvance.jetpack.compose.data.TodoDao
import io.github.caoshen.androidadvance.jetpack.compose.data.entity.Task
import io.github.caoshen.androidadvance.jetpack.compose.util.DBHelper
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val dao: TodoDao = DBHelper.provideDao()) {

    val getAllTasks: Flow<List<Task>> = dao.loadAllTasks()

    fun searchTask(taskId: Int): Flow<Task> = dao.searchTask(taskId)

    suspend fun insertTask(task: Task) = dao.insertTask(task)

    suspend fun updateTask(task: Task) = dao.updateTask(task)

    suspend fun deleteTask(task: Task) = dao.deleteTask(task)

    suspend fun deleteAllTasks() = dao.deleteAllTasks()

}