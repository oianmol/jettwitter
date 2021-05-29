package com.mutualmobile.tweetify.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mutualmobile.tweetify.ui.components.TweetifySurface

@Composable
fun HomeScreen(
) {
    TweetifySurface(Modifier.fillMaxSize()) {
        Text(text = "Home")
    }
}