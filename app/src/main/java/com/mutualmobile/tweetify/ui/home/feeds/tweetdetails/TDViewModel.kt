package com.mutualmobile.tweetify.ui.home.feeds.tweetdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mutualmobile.tweetify.ui.home.feeds.data.TweetState
import com.mutualmobile.tweetify.ui.home.feeds.data.TweetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TDViewModel @Inject constructor(
    private val repository: TweetsRepository
) : ViewModel() {

    var tweetByIdState by mutableStateOf<TweetState>(TweetState.Loading)
        private set


    fun fetchTweetById(tweetId: String?) {
        tweetByIdState =
            repository.tweetById(tweetId)?.let { TweetState.SuccessTweet(it) } ?: TweetState.Failure
    }
}