package io.github.caoshen.androidadvance.jetpack.compose.navigation

import androidx.navigation.NavController
import io.github.caoshen.androidadvance.jetpack.compose.util.AppHolder
import io.github.caoshen.androidadvance.jetpack.compose.util.AppHolder.HOME_SCREEN
import io.github.caoshen.androidadvance.jetpack.compose.util.AppHolder.SPLASH_SCREEN

class Screens(navController: NavController) {

    val gotoHomeFromSplash: () -> Unit = {
        navController.navigate(route = HOME_SCREEN) {
            // 路由之前先弹出到 splash
            popUpTo(SPLASH_SCREEN) {
                // splash 自身是否也需要弹出
                inclusive = true
            }
        }
    }

    val gotoTaskDetail: (Int) -> Unit = { taskId ->
        navController.navigate(route = "${AppHolder.TASK_DETAIL_BASE}$taskId")
    }

    val gotoHomeScreen: () -> Unit = {
        navController.navigate(route = HOME_SCREEN) {
            popUpTo(HOME_SCREEN) {
                inclusive = true
            }
        }
    }
}