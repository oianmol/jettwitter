package com.mutualmobile.tweetify.ui.home.feeds

import android.icu.text.RelativeDateTimeFormatter
import android.text.format.DateUtils
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.home.feeds.data.Tweet
import com.mutualmobile.tweetify.ui.home.feeds.data.TweetsRepository
import com.mutualmobile.tweetify.ui.home.stories.RoundedUserImage
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme

@Composable
fun ComposeTweet(tweet: Tweet) {
    TweetifySurface {
        Row(modifier = Modifier.padding(8.dp)) {
            RoundedUserImage(url = tweet.tUImage)
            Spacer(modifier = Modifier.width(8.dp))
            Column() {
                Row {
                    Text(buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = TweetifyTheme.colors.textPrimary
                            )
                        ) {
                            append(tweet.tUName)
                        }
                        append(" ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = TweetifyTheme.colors.textPrimary
                            )
                        ) {
                            append(tweet.tUHandler)
                        }
                        append(" ")
                        append(
                            DateUtils.getRelativeTimeSpanString(
                                tweet.tUTime,
                                System.currentTimeMillis(),
                                DateUtils.HOUR_IN_MILLIS
                            ).toString())
                    })
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewComposeTweets() {
    TweetifyTheme {
        ComposeTweet(TweetsRepository.fetch().first())
    }
}

@Preview("Dark")
@Composable
fun PreviewDarkComposeTweets() {
    TweetifyTheme(darkTheme = true) {
        ComposeTweet(TweetsRepository.fetch().first())
    }
}