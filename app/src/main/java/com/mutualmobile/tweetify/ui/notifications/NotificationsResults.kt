package com.mutualmobile.tweetify.ui.notifications

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.mutualmobile.tweetify.R
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.theme.AlphaNearOpaque
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme
import com.mutualmobile.tweetify.utils.PHOTO_URL

@Composable
fun NotificationsResults(notificationVM: NotificationsViewModel) {
    val notificationState = notificationVM.notificationsListState

    LazyColumn {
        if (notificationState is NotificationTweetState.Success) {
            items(notificationState.data.size) {
                notificationState.data.forEach { notificationTweet ->
                    ComposeNotificationTweet(notificationTweet)
                }
            }
        }
    }
}

@Composable
fun ComposeNotificationTweet(notificationTweet: NotificationTweet) {
    Column {
        ComposeNotificationTweetInternal(notificationTweet)
        Divider(color = Color.DarkGray.copy(AlphaNearOpaque), thickness = 0.5.dp)
    }

}

@Composable
private fun ComposeNotificationTweetInternal(notificationTweet: NotificationTweet) {
    Row(modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 4.dp, end = 8.dp)) {
        Icon(
            painterResource(id = R.drawable.ic_notifications_bottom),
            contentDescription = null,
            tint = TweetifyTheme.colors.accent,
            modifier = Modifier
                .requiredSize(50.dp)
                .padding(12.dp),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            TweetifySurface(
                shape = CircleShape,
                modifier = Modifier
                    .requiredSize(60.dp),
                contentColor = TweetifyTheme.colors.uiBackground
            ) {
                Image(
                    rememberCoilPainter(notificationTweet.usrImgUrl),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            ComposeTextWithBoldUserName(
                notificationTweet.title,
                username = notificationTweet.userName
            )
            Spacer(modifier = Modifier.height(8.dp))
            notificationTweet.subtitle?.let {
                ComposeTextWithBoldUserName(
                    notificationTweet.subtitle!!,
                    username = notificationTweet.userName
                )
            }

        }
    }
}

@Composable
fun ComposeTextWithBoldUserName(text: String, username: String) {
    Text(text)
}
