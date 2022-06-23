package io.github.caoshen.androidadvance.jetpack.compose.ui.screens.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Colors
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
import io.github.caoshen.androidadvance.jetpack.compose.util.AppHolder.SPLASH_DELAY
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
                contentDescription = stringResource(id = R.string.compose)
            )
            // 4 TextView
            Text(
                modifier = Modifier
                    .offset(y = offsetState)
                    .alpha(alpha = alphaState),
                text = stringResource(id = R.string.compose),
                color = MaterialTheme.colors.splashText,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }
    }
}

/**
 * 动画
 */
@Composable
fun Splash(gotoHomeScreen: () -> Unit) {
    // 1 start 标记动画状态
    var start by remember {
        mutableStateOf(false)
    }
    // 2 设置 offset 和 alpha
    // 平移100dp到0dp
    val offset by animateDpAsState(
        targetValue = if (start) 0.dp else 100.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    // 透明度0到1
    val alpha by animateFloatAsState(targetValue = if (start) 1f else 0f,
    animationSpec = tween(
        durationMillis = 2000
    ))

    // 启动协程
    LaunchedEffect(key1 = Unit) {
        start = true
        delay(SPLASH_DELAY)
        gotoHomeScreen()
    }

    Splash(offsetState = offset, alphaState = alpha)
}

@Composable
fun getLogo(): Int {
    return if (isSystemInDarkTheme()) {
        R.drawable.to_do_logo
    } else {
        R.drawable.to_do_logo
    }
}

