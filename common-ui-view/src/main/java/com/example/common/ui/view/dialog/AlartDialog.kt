package com.example.common.ui.view.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.common.ui.view.R
import com.example.common.ui.view.theme.captionOnPrimary
import com.example.common.ui.view.theme.captionOnSurface
import com.example.common.ui.view.theme.h3Primary


@Composable
fun AlertDialogSample(value: Boolean, function: () -> Unit, actioner: () -> Unit) {
    if (value) {
        AlertDialog(
            onDismissRequest = {
                function.invoke()
            },
            title = {
                Text(
                    text = stringResource(id = R.string.label_title_error_dialog),
                    style = MaterialTheme.typography.h3Primary()
                )
            },
            text = {
                Text(
                    stringResource(id = R.string.msg_error_dialog),
                    style = MaterialTheme.typography.captionOnSurface()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        actioner.invoke()
                    }) {
                    Text(
                        stringResource(id = R.string.label_dialog_retry),
                        style = MaterialTheme.typography.captionOnPrimary()
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        function.invoke()
                    }) {
                    Text(
                        stringResource(id = R.string.label_dialog_dismiss),
                        style = MaterialTheme.typography.captionOnPrimary()
                    )
                }
            }
        )
    }
}