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

    init {
        tweetsState = TweetState.Loading
        fetchTweets()
        Timber.e("fetch tweets")
    }

    private fun fetchTweets() {
        viewModelScope.launch {
            val tweets = repository.fetchAsync()
            delay(3000)
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

    fun fetchLatest() {
        fetchTweets()
    }
}