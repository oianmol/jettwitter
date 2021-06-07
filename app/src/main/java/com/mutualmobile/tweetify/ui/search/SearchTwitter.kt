package com.mutualmobile.tweetify.ui.search

data class SearchTwitter(
    var searchCategory: String,
    var hashTagTitle: String,
    var totalTweets: String,
    var imageUrl: String?
)

data class SearchHeader(
    var category: String,
    var time: Long,
    var title: String,
    var subtitle: String,
    var imageUrl: String
)
