package com.mutualmobile.tweetify.ui.home.feeds.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TweetsViewModel : ViewModel() {

    private val _tweetsLive = MutableLiveData<List<Tweet>>()
    val tweetsLiveData: LiveData<List<Tweet>> = _tweetsLive

    private val repository = TweetsRepository()

    init {
        fetchTweets()
    }

    private fun fetchTweets() {
        viewModelScope.launch {
            val tweets = repository.fetch()
            _tweetsLive.postValue(tweets)
        }
    }

    fun loadMetadata(tweet: Tweet, url: String) {
        viewModelScope.launch {
            repository.printUrlMatadata(url)
        }
    }
}