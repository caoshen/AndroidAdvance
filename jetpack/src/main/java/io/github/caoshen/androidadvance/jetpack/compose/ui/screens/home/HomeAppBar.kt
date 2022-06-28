package io.github.caoshen.androidadvance.jetpack.compose.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.androidadvance.jetpack.compose.dialog.TodoAlertDialog
import io.github.caoshen.androidadvance.jetpack.compose.ui.theme.MEDIUM_PADDING
import io.github.caoshen.androidadvance.jetpack.compose.ui.theme.topAppBarBackground
import io.github.caoshen.androidadvance.jetpack.compose.ui.theme.topAppBarContent

/**
 * 标题栏
 */
@Composable
fun HomeAppBar(
    onDeleteAllConfirmed: () -> Unit
) {
    HomeTopAppBar(
        onDeleteAllConfirmed = {
            onDeleteAllConfirmed()
        }
    )
}

@Composable
fun HomeTopAppBar(
    onDeleteAllConfirmed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.list_screen_title),
                color = MaterialTheme.colors.topAppBarContent
            )
        },
        actions = {
            HomeAppBarActions(onDeleteAllConfirmed)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackground
    )
}

@Composable
fun HomeAppBarActions(
    onDeleteAllConfirmed: () -> Unit
) {
    var isShowDialog by remember {
        mutableStateOf(false)
    }

    TodoAlertDialog(
        title = stringResource(id = R.string.delete_all_tasks),
        msg = stringResource(id = R.string.delete_all_tasks_confirmation),
        isShowDialog = isShowDialog,
        onNoClicked = {
            isShowDialog = false
        },
        onYesClicked = {
            onDeleteAllConfirmed()
        }
    )

    DeleteAllAction(
        onDeleteAllConfirmed = {
            isShowDialog = true
        })
}

@Composable
fun DeleteAllAction(
    onDeleteAllConfirmed: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_more),
            tint = MaterialTheme.colors.topAppBarContent,
            contentDescription = stringResource(id = R.string.delete_all_action)
        )
        DropdownMenu(expanded = expanded, onDismissRequest = {
            expanded = false
        }) {
            DropdownMenuItem(onClick = {
                // 关闭菜单
                expanded = false
                //
                onDeleteAllConfirmed()
            }) {
                Text(
                    modifier = Modifier.padding(start = MEDIUM_PADDING),
                    text = stringResource(id = R.string.delete_all_action)
                )
            }
        }
    }
}
