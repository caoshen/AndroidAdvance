package io.github.caoshen.androidadvance.jetpack.compose.ui.screens.splash

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val alpha by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000
        )
    )

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

enum class Tabs(
    val title: String, @DrawableRes val icon: Int
) {
    ONE("One", R.drawable.ic_nav_news_normal),
    TWO("Two", R.drawable.ic_nav_tweet_normal),
    THREE("Three", R.drawable.ic_nav_discover_normal),
    FOUR("Fore", R.drawable.ic_nav_my_normal)
}

@Composable
fun One() {
    BaseDefault("One")
}

@Composable
fun Two() {
    BaseDefault("Two")
}

@Composable
fun Three() {
    BaseDefault("Three")
}

@Composable
fun Four() {
    BaseDefault("Four")
}

@Composable
fun BaseDefault(content: String) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement =
        Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) { Text(content, fontSize = 50.sp) }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun Preview() {
    // Tab数据
    val tabs = Tabs.values()
    // 使用remember记住State值
    var position by remember { mutableStateOf(Tabs.ONE) }
    // 脚手架，方便实现页面
    Scaffold(
        backgroundColor = Color.Yellow,
        // 定义bottomBar
        bottomBar = {
            BottomNavigation {
                tabs.forEach { tab ->
                    BottomNavigationItem(
                        modifier = Modifier.background(MaterialTheme.colors.primary),
                        icon = { Icon(painterResource(tab.icon), contentDescription = null) },
                        label = { Text(tab.title) },
                        selected = tab == position,
                        onClick = { position = tab },
                        alwaysShowLabel = false,
                    )
                }
            }
        }) {
        // 根据State值的变化来动态切换当前显示的页面
        when (position) {
            Tabs.ONE -> One()
            Tabs.TWO -> Two()
            Tabs.THREE -> Three()
            Tabs.FOUR -> Four()
        }
    }
}



