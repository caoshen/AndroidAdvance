package io.github.caoshen.androidadvance.jetpack.compose.ui.screens.home

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import io.github.caoshen.androidadvance.jetpack.compose.ui.viewmodels.HomeViewModel

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
                }
            )
        },

        content = {
            HomeContent()
        },

        floatingActionButton = {
            HomeFab(onFabClicked = gotoTaskDetail)
        }
    )
}

@Composable
fun HomeContent() {

}

