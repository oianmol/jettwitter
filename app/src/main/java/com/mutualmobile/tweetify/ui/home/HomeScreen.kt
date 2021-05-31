package com.mutualmobile.tweetify.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.home.bottomnavigation.TwitterNavigationScreen
import com.mutualmobile.tweetify.ui.home.feeds.ComposeTweetAdvertisementBanner
import com.mutualmobile.tweetify.ui.home.feeds.ComposeTweet
import com.mutualmobile.tweetify.ui.home.feeds.data.TweetState
import com.mutualmobile.tweetify.ui.home.feeds.data.TweetsViewModel
import com.mutualmobile.tweetify.ui.home.stories.ComposeStoriesHome
import com.mutualmobile.tweetify.ui.home.stories.UserStoriesRepository
import com.mutualmobile.tweetify.ui.theme.AlphaNearTransparent
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme

@Composable
fun HomeScreen(
    tweetsViewModel: TweetsViewModel = TweetsViewModel(),
    navigateToTweet: (String) -> Unit?
) {
    val tweetState = tweetsViewModel.tweetsState
    TweetifySurface(Modifier.fillMaxSize()) {
        LazyColumn {
            item {
                ComposeStoriesWithSpacing()
            }
            item {
                ComposeTweetADBanner()
            }
            when (tweetState) {
                is TweetState.Loading -> {
                    item {
                        ComposeLoadingView()
                    }
                }
                is TweetState.Failure -> {

                }
                is TweetState.Success -> {
                    item {
                        tweetState.data.forEach {
                            ComposeTweet(it, tweetsViewModel) { tweet ->
                                navigateToTweet(tweet.tUid)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ComposeLoadingView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = TweetifyTheme.colors.accent)
    }
}

@Composable
private fun ComposeTweetADBanner() {
    Column {
        Divider(
            color = TweetifyTheme.colors.uiBorder.copy(AlphaNearTransparent),
            thickness = 5.dp
        )
        ComposeTweetAdvertisementBanner()
        Divider(
            color = TweetifyTheme.colors.uiBorder.copy(AlphaNearTransparent),
            thickness = 5.dp
        )
    }
}

@Composable
private fun ComposeStoriesWithSpacing() {
    Column {
        Spacer(modifier = Modifier.height(2.dp))
        ComposeStoriesHome(UserStoriesRepository.fetchStories())
        Spacer(modifier = Modifier.height(2.dp))
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    TweetifyTheme {
        HomeScreen(navigateToTweet = {

        })
    }
}

@Preview("Dark")
@Composable
fun HomeScreenPreviewDark() {
    TweetifyTheme(darkTheme = true) {
        HomeScreen(navigateToTweet = {

        })
    }
}



