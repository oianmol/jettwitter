package com.mutualmobile.tweetify.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mutualmobile.tweetify.ui.components.TweetifySurface
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
    tweetsViewModel: TweetsViewModel = viewModel(),
    navigateToTweet: (String) -> Unit?,
    modifierPadding: PaddingValues,
    navigateToHashTagSearch: (String) -> Unit?
) {
    val tweetState = tweetsViewModel.tweetsState

    TweetifySurface(
        modifier = Modifier
            .fillMaxSize()
            .padding(modifierPadding)
    ) {
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
                            ComposeTweet(
                                tweet = it, onClickTweet =
                                { tweet ->
                                    navigateToTweet(tweet.tUid)
                                }, hashTagNavigator = { hashTag ->
                                    navigateToHashTagSearch.invoke(hashTag)
                                }
                            )
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



