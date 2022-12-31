package com.example.builder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class HomeActivity : ComponentActivity() {

    companion object {
        var appId = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(text = "hi builder")
            appNavigation()
        }

    }

    @Composable
    fun appNavigation() {

    }
}