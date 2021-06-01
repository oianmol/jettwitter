package com.mutualmobile.tweetify.ui.home.feeds.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TweetsViewModel(var tweetId: String? = null) : ViewModel() {

    var tweetsState by mutableStateOf<TweetState>(TweetState.Loading)
        private set

    var tweetByIdState by mutableStateOf<TweetState>(TweetState.Loading)
        private set

    private val repository = TweetsRepository()

    init {
        tweetsState = TweetState.Loading
        fetchTweets()
        Log.e("vm","fetch tweets")
    }

    private fun fetchTweets() {
        viewModelScope.launch {
            val tweets = repository.fetchAsync()
            tweetId?.let { tweetId ->
                tweets.firstOrNull { it.tUid == tweetId }?.let {
                    tweetByIdState = TweetState.SuccessTweet(it)
                }
            }
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