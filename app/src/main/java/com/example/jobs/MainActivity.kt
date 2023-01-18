package com.example.jobs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.builder.Jobs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val builder = Jobs.Builder()
                .setContext(this)
                .setAppId(BuildConfig.APPLICATION_ID)
                .build()
            builder.start()

            finish()
        }
    }
}
