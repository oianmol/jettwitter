package com.mutualmobile.tweetify.ui.home.feeds.data

data class Tweet(
    val tUName: String,
    val tUImage: String,
    val tUText: String,
    val tUTime: Long,
    val tUHandler: String,
    val tCommentCount: Long,
    val tLikeCount: Long,
    val tRTCount: Long
)