package com.example.ui.user.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UserScreen(viewModel: UserViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Text("im user screen")
    }
}