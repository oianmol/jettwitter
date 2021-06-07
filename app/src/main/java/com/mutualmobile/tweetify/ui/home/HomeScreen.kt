package com.mutualmobile.tweetify.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.components.custom.SwipeProgressIndicator
import com.mutualmobile.tweetify.ui.home.feeds.ComposeTweetAdvertisementBanner
import com.mutualmobile.tweetify.ui.home.feeds.ComposeTweet
import com.mutualmobile.tweetify.ui.home.feeds.data.TweetState
import com.mutualmobile.tweetify.ui.home.feeds.data.TweetsViewModel
import com.mutualmobile.tweetify.ui.home.stories.ComposeStoriesHome
import com.mutualmobile.tweetify.ui.home.stories.UserStoriesRepository
import com.mutualmobile.tweetify.ui.theme.AlphaNearTransparent
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme
import com.mutualmobile.tweetify.ui.home.feeds.data.Tweet

@Composable
fun HomeScreen(
    navigateToTweet: (String) -> Unit?,
    modifierPadding: PaddingValues,
    navigateToHashTagSearch: (String) -> Unit?,
    tweetsViewModel: TweetsViewModel
) {
    val tweetState = tweetsViewModel.tweetsState
    val swipeRefreshState = rememberSwipeRefreshState(tweetState is TweetState.Loading)

    TweetifySurface(
        modifier = Modifier
            .fillMaxSize()
            .padding(modifierPadding)
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            swipeEnabled = tweetState !is TweetState.Loading,
            indicator = { state, trigger ->
                SwipeProgressIndicator(
                    swipeRefreshState = state,
                    refreshTriggerDistance = trigger
                )
            },
            onRefresh = {
                tweetsViewModel.fetchLatest()
            }, refreshTriggerDistance = 180.dp
        ) {
            LazyColumn {
                item {
                    ComposeStoriesWithSpacing()
                }
                item {
                    ComposeTweetADBanner()
                }
                when (tweetState) {

                    is TweetState.Success -> {
                        item {
                            tweetState.data.forEach {
                                ComposeTweet(
                                    tweet = it, onClickTweet =
                                    { tweet ->
                                        navigateToTweet(tweet.tUid)
                                    }, hashTagNavigator = { hashTag ->
                                        navigateToHashTagSearch.invoke(hashTag)
                                    }, onUrlRecognized = { tweet: Tweet, url: String ->
                                        tweetsViewModel.loadMetadata(tweet, url)
                                    }
                                )
                            }
                        }
                    }
                    else -> {
                    }
                }
            }
        }
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



