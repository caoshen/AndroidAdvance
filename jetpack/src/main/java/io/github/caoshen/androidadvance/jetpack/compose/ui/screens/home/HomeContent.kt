package io.github.caoshen.androidadvance.jetpack.compose.ui.screens.home

import androidx.compose.runtime.Composable
import io.github.caoshen.androidadvance.jetpack.compose.data.entity.Task
import io.github.caoshen.androidadvance.jetpack.compose.util.RequestState

@Composable
fun HomeContent(
    allTasks: RequestState<List<Task>>,
    onSwipeToDelete: (Task) -> Unit,
    gotoTaskDetail: (taskId: Int) -> Unit,
    onUpdateTask: (Task) -> Unit
) {
    if (allTasks is RequestState.Success && allTasks.data.isNotEmpty()) {
        HomeTasksColumn(

        )
    } else {
        HomeEmptyContent()
    }
}

@Composable
fun HomeTasksColumn() {

}

@Composable
fun HomeEmptyContent() {

}