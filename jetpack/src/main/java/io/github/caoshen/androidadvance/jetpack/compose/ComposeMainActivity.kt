package io.github.caoshen.androidadvance.jetpack.compose

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.github.caoshen.androidadvance.jetpack.compose.navigation.SetupNavigation
import io.github.caoshen.androidadvance.jetpack.compose.ui.theme.AndroidAdvanceTheme
import io.github.caoshen.androidadvance.jetpack.compose.ui.viewmodels.HomeViewModel
import io.github.caoshen.androidadvance.jetpack.compose.util.goXiaomiSetting
import io.github.caoshen.androidadvance.jetpack.compose.util.isXiaomi

class ComposeMainActivity : ComponentActivity() {
    companion object {
        const val TAG = "ComposeMainActivity"
    }

    private lateinit var navController: NavHostController

    private val viewModel by viewModels<HomeViewModel>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAdvanceTheme {
                // remember
                navController = rememberNavController()

                SetupNavigation(navController = navController, viewModel = viewModel)
            }
        }

        if (isXiaomi()) {
            goXiaomiSetting()
        } else {
            Log.d(TAG, "onCreate: ${Build.BRAND}")
        }
    }

}
