package com.mutualmobile.tweetify.ui.messages

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MessagesViewModel : ViewModel() {
    var messagesListState by mutableStateOf<MessagesState>(MessagesState.Loading)
        private set
}


sealed class MessagesState {
    object Loading : MessagesState()
    object Failure : MessagesState()
    class Success(var data: List<TwitterMessage>) : MessagesState()
}