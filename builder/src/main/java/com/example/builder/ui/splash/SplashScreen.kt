package com.example.builder.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay


@Composable
fun SplashScreen( navigateToJobScreen : () -> Unit){
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Image(
            painter = painterResource(
                com.example.common.ui.view.R.drawable.ic_splash
            ),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.8f)
                .aspectRatio(1f)
        )
    }

    LaunchedEffect(coroutineScope)
    {
        delay(1000)
        navigateToJobScreen.invoke()
    }
}