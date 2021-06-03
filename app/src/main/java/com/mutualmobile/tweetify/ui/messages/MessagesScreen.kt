package com.mutualmobile.tweetify.ui.messages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mutualmobile.tweetify.ui.components.ComposeTweetifyFeedText
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.home.feeds.ComposeNameHandlerOverflow
import com.mutualmobile.tweetify.ui.home.feeds.ComposeTime
import com.mutualmobile.tweetify.ui.home.stories.RoundedUserImage
import com.mutualmobile.tweetify.ui.theme.AlphaNearOpaque

@Composable
fun MessagesScreen(
    modifierPadding: PaddingValues,
    messagesViewModel: MessagesViewModel = viewModel(),
    onClickMessage: (TwitterMessage) -> Unit
) {
    val messagesList = messagesViewModel.messagesListState

    TweetifySurface(Modifier.padding(modifierPadding)) {
        LazyColumn {
            item {
                if (messagesList is MessagesState.Success) {
                    messagesList.data.forEach { twitterMessage ->
                        MessageItem(twitterMessage, onClickMessage)
                    }
                }
            }
        }
    }
}

@Composable
fun MessageItem(twitterMessage: TwitterMessage, onClickMessage: (TwitterMessage) -> Unit) {
    Column {
        Row(modifier = Modifier.padding(12.dp)) {
            RoundedUserImage(url = twitterMessage.imgUrl)
            Spacer(modifier = Modifier.width(14.dp))
            ComposeMessageColumn(
                twitterMessage,
                onClickMessage
            )
        }
        Divider(color = Color.Gray.copy(AlphaNearOpaque), thickness = 0.5.dp)
    }
}

@Composable
fun ComposeMessageColumn(twitterMessage: TwitterMessage, onClickMessage: (TwitterMessage) -> Unit) {
    Column {
        ComposeNameHandlerOverflow(twitterMessage.fullName, twitterMessage.userName)
        ComposeTime(twitterMessage.dateTime)
        Spacer(modifier = Modifier.height(8.dp))
        ComposeTweetifyFeedText(
            twitterMessage.lastMessage,
            urlRecognizer = { url ->

            },
            hashTagNavigator = {

            },
            textClick = {
                onClickMessage.invoke(twitterMessage)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
