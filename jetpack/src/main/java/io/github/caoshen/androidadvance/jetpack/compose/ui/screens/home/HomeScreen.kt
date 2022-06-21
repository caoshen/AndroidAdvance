package io.github.caoshen.androidadvance.jetpack.compose.ui.screens.home

import android.view.View
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import io.github.caoshen.androidadvance.jetpack.compose.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    gotoTaskDetail: (Int) -> Unit,
    viewModel: HomeViewModel
) {
    val allTasks = viewModel.allTasks.collectAsState()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeAppBar()
        },

        content = {
            HomeContent()
        },

        floatingActionButton = {
            HomeFab()
        }

    )
}

@Composable
fun HomeAppBar() {

}

@Composable
fun HomeContent() {

}

@Composable
fun HomeFab() {

}