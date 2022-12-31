package com.example.jobs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.builder.Jobs
import com.example.jobs.ui.theme.JobsTheme

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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JobsTheme {
    }
}