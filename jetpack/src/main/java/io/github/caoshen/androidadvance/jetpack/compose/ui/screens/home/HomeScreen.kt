package io.github.caoshen.androidadvance.jetpack.compose.ui.screens.home

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.androidadvance.jetpack.compose.ui.viewmodels.HomeViewModel
import io.github.caoshen.androidadvance.jetpack.compose.util.showToast

@Composable
fun HomeScreen(
    gotoTaskDetail: (Int) -> Unit,
    viewModel: HomeViewModel
) {
    val allTasks = viewModel.allTasks.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeAppBar(
                onDeleteAllConfirmed = {
                    viewModel.deleteAllTasks()
                    showToast(context, context.getString(R.string.all_tasks_deleted))
                }
            )
        },

        content = {
        },

        floatingActionButton = {
            HomeFab(onFabClicked = gotoTaskDetail)
        }
    )
}
