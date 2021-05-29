package com.mutualmobile.tweetify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme
import com.mutualmobile.tweetify.utils.LocalSysUiController
import com.mutualmobile.tweetify.utils.SystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            TweetifyApp { finish() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TweetifyTheme {
        TweetifyApp {

        }
    }
}