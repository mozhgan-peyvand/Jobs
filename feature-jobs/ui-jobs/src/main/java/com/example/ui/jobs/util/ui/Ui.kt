package com.example.ui.jobs.util.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.common.ui.view.theme.AppColors
import com.example.base.util.shape.Flat
import com.example.base.util.shape.LightSource
import com.example.base.util.shape.Oval
import com.example.base.util.shape.neu
import com.example.common.ui.view.theme.captionOnPrimary
import com.example.common.ui.view.theme.captionOnSurface
import com.example.common.ui.view.theme.h3Primary
import com.example.ui.jobs.R

@Composable
fun ImageButton(
    modifier: Modifier,
    @DrawableRes drawableResId: Int,
    contentDescription: String = "",
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .size(48.dp)
            .neu(
                lightShadowColor = AppColors.lightShadow(),
                darkShadowColor = AppColors.darkShadow(),
                shadowElevation = 4.dp,
                lightSource = LightSource.LEFT_TOP,
                shape = Flat(Oval),
            ),
        elevation = 0.dp,
        shape = RoundedCornerShape(24.dp),
    ) {
        Image(
            modifier = Modifier.clickable(true, onClick = onClick),
            painter = painterResource(id = drawableResId),
            contentDescription = contentDescription,
            contentScale = ContentScale.Inside,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
        )
    }
}

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

@Composable
fun EmptyJobList(modifier: Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.msg_empty_result_job_search),
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.h3Primary()
        )
    }

}