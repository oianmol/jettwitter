package com.mutualmobile.tweetify.ui.notifications

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor() : ViewModel() {

    var notificationsListState by mutableStateOf<NotificationTweetState>(NotificationTweetState.Loading)
        private set

    init {
        val notifications = prepareNotifiations()
        notificationsListState = NotificationTweetState.Success(notifications)
    }

    private fun prepareNotifiations(): List<NotificationTweet> {
        val notifications = mutableListOf<NotificationTweet>()
        notifications.add(
            NotificationTweet(
                usrImgUrl = "https://pbs.twimg.com/profile_images/1356202394791665665/tKbHYU-a_400x400.jpg",
                title = "New Tweet notifications for Srishti Verma",
                userName = "Srishti Verma",
                notificationType = NotificationType.NEW_TWEET
            )
        )
        notifications.add(
            NotificationTweet(
                usrImgUrl = "https://pbs.twimg.com/profile_images/1395014671459966979/FNtinLtU_400x400.jpg",
                title = "Abhishek Verma followed you",
                userName = "Abhishek Verma",
                notificationType = NotificationType.FOLLOWED
            )
        )
        notifications.add(
            NotificationTweet(
                usrImgUrl = "https://pbs.twimg.com/profile_images/1395014671459966979/FNtinLtU_400x400.jpg",
                title = "Abhishek Verma liked your reply",
                subtitle = "Google Robot\nLato\nyou can use these!",
                userName = "Abhishek Verma",
                notificationType = NotificationType.LIKED_REPLY,
            )
        )
        return notifications
    }
}
