package com.mutualmobile.tweetify.ui.home.feeds.tweetdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.home.feeds.ComposeTweet
import com.mutualmobile.tweetify.ui.home.feeds.data.TweetState
import com.mutualmobile.tweetify.ui.home.feeds.data.TweetsViewModel
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme

@Composable
fun TwitterDetailsScreen(
    tweetId: String?,
    onBack: () -> Unit,
    viewModel: TweetsViewModel = TweetsViewModel(),
    hashTagNavigator: (String) -> Unit
) {
    viewModel.fetchById(tweetId)
    val tweetState = viewModel.tweetByIdState
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = { TwitterDetailsTopBar(onBack) },
        backgroundColor = TweetifyTheme.colors.uiBackground,
        contentColor = TweetifyTheme.colors.textSecondary,
    ) { paddingExtras ->
        if (tweetState is TweetState.SuccessTweet) {
            TweetifySurface(modifier = Modifier
                .clickable {
                    onBack.invoke()
                }
                .padding(paddingExtras)) {
                ComposeTweet(
                    tweet = tweetState.data, tweetsViewModel = viewModel,
                    onClickTweet = {

                    },hashTagNavigator =hashTagNavigator
                )
            }
        }
    }
}

@Composable
fun TwitterDetailsTopBar(onBack: () -> Unit) {
    TweetifySurface(
        color = TweetifyTheme.colors.uiBackground,
        contentColor = TweetifyTheme.colors.accent,
        elevation = 4.dp
    ) {
        TopAppBar(
            title = {
                Text(text = "Tweet")
            },
            backgroundColor = TweetifyTheme.colors.uiBackground,
            navigationIcon = {
                IconButton(onClick = {
                    onBack()
                }) {
                    Icon(
                        Icons.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = TweetifyTheme.colors.accent
                    )
                }
            }, elevation = 4.dp
        )
    }
}
