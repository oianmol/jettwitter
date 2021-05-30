package com.mutualmobile.tweetify.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.home.feeds.ComposeTweets
import com.mutualmobile.tweetify.ui.home.stories.ComposeStoriesHome
import com.mutualmobile.tweetify.ui.home.stories.UserStoriesRepository
import com.mutualmobile.tweetify.ui.theme.AlphaNearOpaque
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme

@Composable
fun HomeScreen(
) {
    TweetifySurface(Modifier.fillMaxSize()) {
        LazyColumn {
            item {
               Column() {
                   Divider(color = Color.Gray.copy(AlphaNearOpaque), thickness = 0.5.dp)
                   Spacer(modifier = Modifier.height(2.dp))
                   ComposeStoriesHome(UserStoriesRepository.fetchStories())
               }
            }
            item{
                Column() {
                    Divider(color = Color.Gray.copy(AlphaNearOpaque), thickness = 0.5.dp)
                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
            item {
                ComposeTweets()
            }
        }

    }
}




