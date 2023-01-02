package com.example.ui_jobs.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun JobScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {

        Text(text = "hi Im job graph")
    }

}