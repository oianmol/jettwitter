package com.mutualmobile.tweetify.ui.home.feeds.tweetdetails

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun TwitterDetailsScreen(tweetId: String?, onBack: Unit) {
    Text("$tweetId")
}