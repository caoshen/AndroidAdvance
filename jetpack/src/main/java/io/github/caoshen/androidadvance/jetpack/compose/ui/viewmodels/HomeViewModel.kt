package io.github.caoshen.androidadvance.jetpack.compose.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.caoshen.androidadvance.jetpack.compose.data.entity.Task
import io.github.caoshen.androidadvance.jetpack.compose.data.repo.TodoRepository
import io.github.caoshen.androidadvance.jetpack.compose.util.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: TodoRepository = TodoRepository()) : ViewModel() {

    val taskId = mutableStateOf(0)

    val taskTitle = mutableStateOf("")

    val taskDesc = mutableStateOf("")

    val taskDone = mutableStateOf(false)

    private val _allTasks = MutableStateFlow<RequestState<List<Task>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<Task>>> = _allTasks

    private val _curTask: MutableStateFlow<Task?> = MutableStateFlow(null)

    val curTask: StateFlow<Task?> = _curTask

    init {
        loadAllTasks()
    }

    private fun loadAllTasks() {
        viewModelScope.launch {
            repository.getAllTasks
                .onStart { _allTasks.value = RequestState.Loading }
                .catch { _allTasks.value = RequestState.Error(it) }
                .collect { _allTasks.value = RequestState.Success(it) }
        }
    }

    fun searchTask(taskId: Int) {
        viewModelScope.launch {
            repository.searchTask(taskId)
                .catch { _curTask.value = null }
                .collect { _curTask.value = it }
        }
    }

    fun createTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val t = Task(
                id = taskId.value,
                title = taskTitle.value,
                desc = taskDesc.value,
                isDone = taskDone.value
            )

            repository.insertTask(t)
        }
    }

    fun updateTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val t = Task(
                id = taskId.value,
                title = taskTitle.value,
                desc = taskDesc.value,
                isDone = taskDone.value
            )
            repository.updateTask(t)
        }
    }

    fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val t = Task(
                id = taskId.value,
                title = taskTitle.value,
                desc = taskDesc.value,
                isDone = taskDone.value
            )
            repository.deleteTask(t)
        }
    }

    fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTasks()
        }
    }

    fun updateCurTaskFields(curTask: Task?) {
        if (curTask == null) {
            taskId.value = 0
            taskTitle.value = ""
            taskDesc.value = ""
            taskDone.value = false
        } else {
            taskId.value = curTask.id
            taskTitle.value = curTask.title
            taskDesc.value = curTask.desc
            taskDone.value = curTask.isDone
        }
    }

    fun updateCurTaskTitle(newTitle: String) {
        taskTitle.value = newTitle
    }

    fun isCurTaskValid(): Boolean {
        return taskTitle.value.isNotEmpty() && taskDesc.value.isNotEmpty()
    }

}