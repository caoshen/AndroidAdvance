package io.github.caoshen.androidadvance.jetpack.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.github.caoshen.androidadvance.jetpack.compose.ui.screens.splash.Splash
import io.github.caoshen.androidadvance.jetpack.compose.ui.theme.AndroidAdvanceTheme
import io.github.caoshen.androidadvance.jetpack.compose.ui.viewmodels.HomeViewModel
import io.github.caoshen.baselib.network.utils.SHOW_TOAST
import io.github.caoshen.baselib.network.utils.toast

class ComposeMainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAdvanceTheme {
                // remember
                navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                // Surface container
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//                    Greeting("World")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidAdvanceTheme {
        Greeting("World")
    }
}