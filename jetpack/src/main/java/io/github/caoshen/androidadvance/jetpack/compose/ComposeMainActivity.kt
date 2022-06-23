package io.github.caoshen.androidadvance.jetpack.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.github.caoshen.androidadvance.jetpack.compose.navigation.SetupNavigation
import io.github.caoshen.androidadvance.jetpack.compose.ui.theme.AndroidAdvanceTheme
import io.github.caoshen.androidadvance.jetpack.compose.ui.viewmodels.HomeViewModel

class ComposeMainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAdvanceTheme {
                // remember
                navController = rememberNavController()

                SetupNavigation(navController = navController, viewModel = viewModel)
            }
        }
    }
}
