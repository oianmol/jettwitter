package com.mutualmobile.tweetify.ui.home.feeds.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TweetsViewModel : ViewModel() {

    var tweetsState by mutableStateOf<TweetState>(TweetState.Loading)
        private set

    private val repository = TweetsRepository()

    init {
        tweetsState = TweetState.Loading
        fetchTweets()
    }

    private fun fetchTweets() {
        viewModelScope.launch {
            val tweets = repository.fetchAsync()
            tweetsState = TweetState.Success(tweets)
        }
    }

    fun loadMetadata(tweet: Tweet, url: String) {
        viewModelScope.launch {
            val meta = repository.fetchUrlMatadata(url)
            tweet.metadata = meta
            fetchTweets()
        }
    }
}