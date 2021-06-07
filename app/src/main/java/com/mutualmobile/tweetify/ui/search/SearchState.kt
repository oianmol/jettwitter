package com.mutualmobile.tweetify.ui.search

sealed class SearchState {
    object Loading : SearchState()
    class Success(val searchData: List<SearchTwitter>) : SearchState()
}
