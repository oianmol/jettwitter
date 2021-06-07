package com.mutualmobile.tweetify.ui.home.feeds.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TweetsViewModel @Inject constructor(
    private val repository: TweetsRepository
) : ViewModel() {
    var tweetsState by mutableStateOf<TweetState>(TweetState.Loading)
        private set

    var tweets: List<Tweet>? = null

    init {
        fetchTweets()
        Timber.e("fetch tweets")
    }

    private fun fetchTweets() {
        viewModelScope.launch {
            tweetsState = TweetState.Loading
            tweets = repository.fetchAsync()
            delay(500)
            tweetsState = TweetState.Success(tweets!!)
        }
    }

    fun loadMetadata(tweet: Tweet, url: String) {
        viewModelScope.launch {
            val meta = repository.fetchUrlMatadata(url)
            tweets?.firstOrNull { it.tUid == tweet.tUid }?.metadata =
                meta
            tweetsState = TweetState.Success(tweets!!)
        }
    }

    fun fetchLatest() {
        viewModelScope.launch {
            tweetsState = TweetState.Loading
            tweets = repository.fetchLatestAsync()
            delay(500)
            tweetsState = TweetState.Success(tweets!!)
        }
    }
}