package io.github.caoshen.androidadvance.jetpack.compose.dialog

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import io.github.caoshen.androidadvance.jetpack.R

@Composable
fun TodoAlertDialog(
    title: String,
    msg: String,
    isShowDialog: Boolean,
    onNoClicked: () -> Unit,
    onYesClicked: () -> Unit
) {
    if (isShowDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = msg,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onYesClicked()
                        onNoClicked()
                    }
                ) {
                    Text(text = stringResource(id = R.string.yes))
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { onNoClicked() }) {
                    Text(
                        text = stringResource(id = R.string.no)
                    )
                }
            },
            onDismissRequest = {
                onNoClicked()
            }
        )
    }
}