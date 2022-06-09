package io.github.caoshen.androidadvance.jetpack.compose.ui.screens.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.androidadvance.jetpack.compose.ui.theme.splashBackground
import io.github.caoshen.androidadvance.jetpack.compose.ui.theme.splashText
import kotlinx.coroutines.delay

val LOGO_HEIGHT = Dp(200F)

@Composable
fun Splash(offsetState: Dp, alphaState: Float) {
    // 1 FrameLayout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.splashBackground),
        contentAlignment = Alignment.Center
    ) {
        // 2 vertical LinearLayout
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 3 ImageView
            Image(
                modifier = Modifier
                    .size(LOGO_HEIGHT)
                    .alpha(alpha = alphaState),
                painter = painterResource(id = getLogo()),
                contentDescription = stringResource(id = R.string.app_name)
            )
            // 4 TextView
            Text(
                modifier = Modifier
                    .offset(offsetState)
                    .alpha(alphaState),
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colors.splashText,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }
    }
}

@Composable
fun Splash(gotoHomeScreen: () -> Unit) {
    // 1 start 标记动画状态
    var start by remember {
        mutableStateOf(false)
    }
    // 2 设置 offset 和 alpha
    val offset by animateDpAsState(
        targetValue = if (start) 0.dp else 100.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    val alpha by animateFloatAsState(targetValue = if (start) 1f else 0f,
    animationSpec = tween(
        durationMillis = 2000
    ))

    LaunchedEffect(key1 = Unit) {
        start = true
        delay(1000)
        gotoHomeScreen()
    }

    Splash(offsetState = offset, alphaState = alpha)
}

fun getLogo(): Int {
    return R.drawable.ic_launcher_foreground
}

