package com.mutualmobile.tweetify.ui.home.feeds

import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mutualmobile.tweetify.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.coil.rememberCoilPainter
import com.mutualmobile.tweetify.ui.components.ComposeTweetifyFeedText
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.home.feeds.data.Tweet
import com.mutualmobile.tweetify.ui.home.feeds.data.TweetsViewModel
import com.mutualmobile.tweetify.ui.home.stories.RoundedUserImage
import com.mutualmobile.tweetify.ui.theme.AlphaNearOpaque
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme

@Composable
fun ComposeTweet(
    tweet: Tweet,
    onUrlRecognized: (Tweet, String) -> Unit,
    onClickTweet: (Tweet) -> Unit,
    hashTagNavigator: (String) -> Unit
) {
    TweetifySurface(modifier = Modifier.clickable {
        onClickTweet.invoke(tweet)
    }) {
        Column {
            Row(modifier = Modifier.padding(12.dp)) {
                RoundedUserImage(url = tweet.tUImage)
                Spacer(modifier = Modifier.width(14.dp))
                ComposeTweetColumn(
                    tweet,
                    onUrlRecognized,
                    hashTagNavigator = hashTagNavigator,
                    onClickTweet
                )
            }
            Divider(color = Color.Gray.copy(AlphaNearOpaque), thickness = 0.5.dp)
        }
    }
}

@Composable
private fun ComposeTweetColumn(
    tweet: Tweet,
    onUrlRecognized: (Tweet, String) -> Unit,
    hashTagNavigator: (String) -> Unit,
    onClickTweet: (Tweet) -> Unit
) {
    Column {
        ComposeNameHandlerOverflow(tweet.tUName, tweet.tUHandler, true)
        ComposeTime(tweet.tUTime)
        Spacer(modifier = Modifier.height(8.dp))
        ComposeTweetifyFeedText(
            tweet.tUText,
            urlRecognizer = { url ->
                if (tweet.metadata == null) {
                    onUrlRecognized.invoke(tweet, url)
                }
            },
            hashTagNavigator = hashTagNavigator,
            textClick = {
                onClickTweet.invoke(tweet)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ComposeTweetMetadata(tweet)
        ComposeFooter(tweet)
    }
}

@Composable
fun ComposeFooter(tweet: Tweet) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_vector_reply),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = tweet.tCommentCount.toString(),
                modifier = Modifier.padding(start = 4.dp),
                fontSize = 14.sp
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_vector_retweet_stroke),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = tweet.tRTCount.toString(), modifier = Modifier.padding(start = 4.dp),
                fontSize = 14.sp
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_vector_heart_stroke), contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = tweet.tLikeCount.toString(), modifier = Modifier.padding(start = 4.dp),
                fontSize = 14.sp
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_vector_share_android),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

    }
}

@Composable
fun ComposeTweetMetadata(tweet: Tweet) {
    val uriHandler = LocalUriHandler.current
    if (tweet.metadata != null && tweet.metadata?.title != null) {
        val tweetUrlMeta = tweet.metadata!!
        TweetifySurface(
            shape = RoundedCornerShape(12.dp),
            modifier = surfaceModifier(tweet, uriHandler)
        ) {
            ConstraintLayout {
                val (image, footer) = createRefs()
                Image(
                    painter = rememberCoilPainter(request = tweetUrlMeta.image ?: tweet.tUImage),
                    contentDescription = null, modifier = Modifier
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .height(180.dp)
                        .fillMaxWidth(), contentScale = ContentScale.Crop
                )
                ComposeMetadataFooter(tweetUrlMeta.title!!,
                    tweetUrlMeta.desc!!,
                    modifier = Modifier
                        .constrainAs(footer) {
                            bottom.linkTo(parent.bottom)
                        }
                        .fillMaxWidth())
            }
        }
    }
}

@Composable
private fun surfaceModifier(
    tweet: Tweet,
    uriHandler: UriHandler
) = Modifier
    .clickable {
        tweet.metadata?.url?.let { uriHandler.openUri(it) }
    }
    .border(
        2.dp,
        TweetifyTheme.colors.uiBorder,
        RoundedCornerShape(12.dp)
    )

@Composable
fun ComposeMetadataFooter(title: String, desc: String, modifier: Modifier) {
    TweetifySurface(
        color = TweetifyTheme.colors.uiBackground.copy(AlphaNearOpaque),
        modifier = modifier
    ) {
        Column {
            Text(
                title,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                desc,
                fontSize = 10.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun ComposeTime(time: Long, color: Color? = null) {
    Text(
        DateUtils.getRelativeTimeSpanString(
            time,
            System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS
        ).toString(),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 14.sp,
        color = color ?: TweetifyTheme.colors.textPrimary
    )
}

@Composable
fun ComposeNameHandlerOverflow(name: String, tUHandler: String, showOverflow: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.fillMaxWidth(0.9f)) {
            Text(
                name, fontWeight = FontWeight.Bold,
                color = TweetifyTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                tUHandler,
                color = TweetifyTheme.colors.textPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (showOverflow) {
            Icon(
                painterResource(id = R.drawable.ic_vector_overflow),
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}