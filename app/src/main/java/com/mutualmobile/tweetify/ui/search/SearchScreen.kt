package com.mutualmobile.tweetify.ui.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme

@Composable
fun SearchScreen(modifierPadding: PaddingValues, hashTagParam: String?) {
    TweetifySurface(modifier = Modifier.padding(modifierPadding)) {
        Text("Search ${hashTagParam?:""}")
    }
}