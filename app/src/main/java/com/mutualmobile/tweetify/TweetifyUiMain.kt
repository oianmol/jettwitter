package com.mutualmobile.tweetify

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.google.accompanist.insets.ProvideWindowInsets
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.home.*
import com.mutualmobile.tweetify.ui.theme.AlphaNearTransparent

@Composable
fun TweetifyUiMain() {
    ProvideWindowInsets {
        TweetifySurface(
            color = TweetifyTheme.colors.statusBarColor.copy(alpha = AlphaNearTransparent),
            modifier = Modifier.fillMaxSize()
        ) {
            TweetifyScaffold()
        }
    }
}









