package com.mutualmobile.tweetify.ui.home.feeds

import android.text.format.DateUtils
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mutualmobile.tweetify.R
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import com.mutualmobile.tweetify.ui.components.ComposeTweetifyFeedText
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.home.feeds.data.Tweet
import com.mutualmobile.tweetify.ui.home.feeds.data.TweetsViewModel
import com.mutualmobile.tweetify.ui.home.stories.RoundedUserImage
import com.mutualmobile.tweetify.ui.theme.AlphaNearOpaque
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme

@Composable
fun ComposeTweet(tweet: Tweet, tweetsViewModel: TweetsViewModel) {
    TweetifySurface {
        Column {
            Row(modifier = Modifier.padding(12.dp)) {
                RoundedUserImage(url = tweet.tUImage)
                Spacer(modifier = Modifier.width(14.dp))
                ComposeTweeterTime(tweet,tweetsViewModel)
            }
            Divider(color = Color.Gray.copy(AlphaNearOpaque), thickness = 0.5.dp)
        }
    }
}

@Composable
private fun ComposeTweeterTime(tweet: Tweet, tweetsViewModel: TweetsViewModel) {
    Column {
        ComposeNameHandlerOverflow(tweet)
        ComposeTime(tweet)
        Spacer(modifier = Modifier.height(8.dp))
        ComposeTweetifyFeedText(tweet.tUText, urlRecognizer = { url ->
            tweetsViewModel.loadMetadata(tweet,url)
        })
        ComposeTweetMetadata(tweet)
    }
}

@Composable
fun ComposeTweetMetadata(tweet: Tweet) {

}

@Composable
private fun ComposeTime(tweet: Tweet) {
    Text(
        DateUtils.getRelativeTimeSpanString(
            tweet.tUTime,
            System.currentTimeMillis(),
            DateUtils.HOUR_IN_MILLIS
        ).toString(),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun ComposeNameHandlerOverflow(tweet: Tweet) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.fillMaxWidth(0.9f)) {
            Text(
                tweet.tUName, fontWeight = FontWeight.Bold,
                color = TweetifyTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                tweet.tUHandler,
                color = TweetifyTheme.colors.textPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Icon(
            painterResource(id = R.drawable.ic_vector_overflow),
            contentDescription = null,
            tint = Color.Gray
        )
    }
}


@Preview
@Composable
fun PreviewComposeTweets(tweetsViewModel: TweetsViewModel = TweetsViewModel()) {

    val tweetsLiveData by tweetsViewModel.tweetsLiveData.observeAsState(listOf())
    TweetifyTheme {
        ComposeTweet(tweetsLiveData.first(), tweetsViewModel)
    }
}

@Preview("Dark")
@Composable
fun PreviewDarkComposeTweets(tweetsViewModel: TweetsViewModel = TweetsViewModel()) {
    val tweetsLiveData by tweetsViewModel.tweetsLiveData.observeAsState(listOf())
    TweetifyTheme {
        ComposeTweet(tweetsLiveData.first(), tweetsViewModel)
    }
}