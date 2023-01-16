package com.example.ui.user.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer

@Composable
fun UserScreen() {
    Column(modifier = Modifier
        .fillMaxSize())
    {
        Text("im user screen")
    }
}