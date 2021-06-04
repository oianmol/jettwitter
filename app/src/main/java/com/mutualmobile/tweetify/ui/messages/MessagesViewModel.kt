package com.mutualmobile.tweetify.ui.messages

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MessagesViewModel : ViewModel() {
    var messagesListState by mutableStateOf<MessagesState>(MessagesState.Loading)
        private set

    init {
        messagesListState = MessagesState.Success(prepareMessages())
    }

    private fun prepareMessages(): List<TwitterMessage> {
        val messages = mutableListOf<TwitterMessage>()
        messages.add(
            TwitterMessage(
                fullName = "Sean Cummings",
                imgUrl = "https://pbs.twimg.com/profile_images/1209802752819261440/jvir-xNm_400x400.jpg",
                lastMessage = "Aggressive Ponytail",
                dateTime = 1622360101468L,
                userName = "@sean_cummings",
            )
        )
        messages.add(
            TwitterMessage(
                fullName = "Sean Cummings",
                imgUrl = "https://pbs.twimg.com/profile_images/1367145689910710279/S9GtuoFo_400x400.jpg",
                lastMessage = "Aggressive Ponytail",
                dateTime = 1622360101468L,
                userName = "@sean_cummings",
            )
        )
        messages.add(
            TwitterMessage(
                fullName = "Supabase",
                imgUrl = "https://pbs.twimg.com/profile_images/1397471927132844033/jN-wuufb_400x400.jpg",
                lastMessage = "Supabase Storage is now available!",
                dateTime = 1622370333814L,
                userName = "@supabase_io",
            )
        )
        messages.add(
            TwitterMessage(
                fullName = "Apple Podcasts",
                imgUrl = "https://pbs.twimg.com/profile_images/1009097162104246272/07rvgUrd_400x400.jpg",
                lastMessage = "Celebrate the diversity of Asian American and Pacific Islander communities with this collection of podcasts about identity, history, culture, food and more",
                dateTime = 1622360101568L,
                userName = "@ApplePodcasts",
            )
        )
        messages.add(
            TwitterMessage(
                fullName = "Sean Cummings",
                imgUrl = "https://pbs.twimg.com/profile_images/1209802752819261440/jvir-xNm_400x400.jpg",
                lastMessage = "Aggressive Ponytail",
                dateTime = 1622360101468L,
                userName = "@sean_cummings",
            )
        )
        messages.add(
            TwitterMessage(
                fullName = "Sean Cummings",
                imgUrl = "https://pbs.twimg.com/profile_images/1209802752819261440/jvir-xNm_400x400.jpg",
                lastMessage = "Aggressive Ponytail",
                dateTime = 1622360101468L,
                userName = "@sean_cummings",
            )
        )
        messages.add(
            TwitterMessage(
                fullName = "Sean Cummings",
                imgUrl = "https://pbs.twimg.com/profile_images/1367145689910710279/S9GtuoFo_400x400.jpg",
                lastMessage = "Aggressive Ponytail",
                dateTime = 1622360101468L,
                userName = "@sean_cummings",
            )
        )
        messages.add(
            TwitterMessage(
                fullName = "Supabase",
                imgUrl = "https://pbs.twimg.com/profile_images/1397471927132844033/jN-wuufb_400x400.jpg",
                lastMessage = "Supabase Storage is now available!",
                dateTime = 1622370333814L,
                userName = "@supabase_io",
            )
        )
        messages.add(
            TwitterMessage(
                fullName = "Apple Podcasts",
                imgUrl = "https://pbs.twimg.com/profile_images/1009097162104246272/07rvgUrd_400x400.jpg",
                lastMessage = "Celebrate the diversity of Asian American and Pacific Islander communities with this collection of podcasts about identity, history, culture, food and more",
                dateTime = 1622360101568L,
                userName = "@ApplePodcasts",
            )
        )
        messages.add(
            TwitterMessage(
                fullName = "Sean Cummings",
                imgUrl = "https://pbs.twimg.com/profile_images/1209802752819261440/jvir-xNm_400x400.jpg",
                lastMessage = "Aggressive Ponytail",
                dateTime = 1622360101468L,
                userName = "@sean_cummings",
            )
        )
        return messages
    }
}


sealed class MessagesState {
    object Loading : MessagesState()
    object Failure : MessagesState()
    class Success(var data: List<TwitterMessage>) : MessagesState()
}