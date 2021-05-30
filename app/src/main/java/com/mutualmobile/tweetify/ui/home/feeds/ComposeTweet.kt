package com.mutualmobile.tweetify.ui.home.feeds

import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import com.mutualmobile.tweetify.ui.home.feeds.data.TweetUrlMeta
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
                ComposeTweetColumn(tweet, tweetsViewModel)
            }
            Divider(color = Color.Gray.copy(AlphaNearOpaque), thickness = 0.5.dp)
        }
    }
}

@Composable
private fun ComposeTweetColumn(tweet: Tweet, tweetsViewModel: TweetsViewModel) {
    Column {
        ComposeNameHandlerOverflow(tweet)
        ComposeTime(tweet)
        Spacer(modifier = Modifier.height(8.dp))
        ComposeTweetifyFeedText(tweet.tUText, urlRecognizer = { url ->
            if (tweet.metadata == null) {
                tweetsViewModel.loadMetadata(tweet, url)
            }
        })
        Spacer(modifier = Modifier.height(8.dp))
        ComposeTweetMetadata(tweet)
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
                ComposeMetadataFooter(tweetUrlMeta, modifier = Modifier
                    .constrainAs(footer) {
                        bottom.linkTo(image.bottom, margin = 2.dp)
                    }
                    .fillMaxWidth(), tweet)
            }
        }
    }
}

@Composable
fun ComposeMetadataFooter(tweetUrlMeta: TweetUrlMeta, modifier: Modifier, tweet: Tweet) {
    TweetifySurface(
        color = TweetifyTheme.colors.uiBackground.copy(AlphaNearOpaque),
        modifier = modifier
    ) {
        Column {
            Text(
                tweetUrlMeta.title ?: tweet.tUName,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                tweetUrlMeta.desc ?: tweet.tUText,
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