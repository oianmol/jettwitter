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
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
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
    tweetsViewModel: TweetsViewModel,
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
                    tweetsViewModel,
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
    tweetsViewModel: TweetsViewModel,
    hashTagNavigator: (String) -> Unit,
    onClickTweet: (Tweet) -> Unit
) {
    Column {
        ComposeNameHandlerOverflow(tweet)
        ComposeTime(tweet)
        Spacer(modifier = Modifier.height(8.dp))
        ComposeTweetifyFeedText(
            tweet.tUText,
            urlRecognizer = { url ->
                if (tweet.metadata == null) {
                    tweetsViewModel.loadMetadata(tweet, url)
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
            Icon(painterResource(id = R.drawable.ic_vector_reply), contentDescription = null)
            Text(text = tweet.tCommentCount.toString(), modifier = Modifier.padding(start = 4.dp))
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_vector_retweet_stroke),
                contentDescription = null
            )
            Text(text = tweet.tRTCount.toString(), modifier = Modifier.padding(start = 4.dp))
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painterResource(id = R.drawable.ic_vector_heart_stroke), contentDescription = null)
            Text(text = tweet.tLikeCount.toString(), modifier = Modifier.padding(start = 4.dp))
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_vector_share_android),
                contentDescription = null
            )
        }

    }
}

@Composable
fun ComposeTweetMetadata(tweet: Tweet) {
    tweet.metadata?.let { tweetUrlMeta ->
        TweetifySurface(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.border(
                2.dp,
                TweetifyTheme.colors.uiBorder,
                RoundedCornerShape(12.dp)
            )
        ) {
            ConstraintLayout {
                val (image, footer) = createRefs()
                Image(
                    painter = rememberCoilPainter(request = tweetUrlMeta.image ?: tweet.tUImage),
                    contentDescription = null, modifier = Modifier
                        .constrainAs(image) {
                            top.linkTo(parent.top, margin = 0.dp)
                        }
                        .height(180.dp)
                        .fillMaxWidth(), contentScale = ContentScale.Crop
                )
                ComposeMetadataFooter(tweetUrlMeta.title ?: tweet.tUName,
                    tweetUrlMeta.desc ?: tweet.tUText,
                    modifier = Modifier
                        .constrainAs(footer) {
                            bottom.linkTo(image.bottom, margin = 0.dp)
                        }
                        .fillMaxWidth())
            }
        }
    }
}

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
private fun ComposeTime(tweet: Tweet) {
    Text(
        DateUtils.getRelativeTimeSpanString(
            tweet.tUTime,
            System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS
        ).toString(),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 14.sp
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