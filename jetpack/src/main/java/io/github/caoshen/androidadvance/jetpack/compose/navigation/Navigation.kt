package io.github.caoshen.androidadvance.jetpack.compose.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.caoshen.androidadvance.jetpack.compose.ui.screens.home.HomeScreen
import io.github.caoshen.androidadvance.jetpack.compose.ui.screens.splash.Splash
import io.github.caoshen.androidadvance.jetpack.compose.ui.viewmodels.HomeViewModel
import io.github.caoshen.androidadvance.jetpack.compose.util.AppHolder
import io.github.caoshen.androidadvance.jetpack.compose.util.AppHolder.HOME_SCREEN
import io.github.caoshen.androidadvance.jetpack.compose.util.AppHolder.SPLASH_SCREEN
import io.github.caoshen.androidadvance.jetpack.compose.util.AppHolder.TASK_DETAIL_SCREEN

@Composable
fun SetupNavigation(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {

        // splash
        composable(
            route = SPLASH_SCREEN
        ) {
            Splash(gotoHomeScreen = screen.gotoHomeScreen)
        }

        // home
        composable(
            route = HOME_SCREEN
        ) {
            HomeScreen(gotoTaskDetail = screen.gotoTaskDetail, viewModel = viewModel)
        }

        // detail
        composable(
            route = TASK_DETAIL_SCREEN,
            // id
            arguments = listOf(navArgument(AppHolder.TASK_ARG_KEY) {
                type = NavType.IntType
            })
        ) {
            val taskId = it.arguments?.getInt(AppHolder.TASK_ARG_KEY) ?: -1
            // 1. taskId -> flow
            LaunchedEffect(key1 = taskId) {
                viewModel.searchTask(taskId)
            }

            // 2. flow -> task
            val currentTask by viewModel.curTask.collectAsState()
            LaunchedEffect(key1 = currentTask) {
                if (currentTask != null || taskId == -1) {
                    viewModel.updateCurTaskFields(currentTask)
                }
            }



        }

    }
}