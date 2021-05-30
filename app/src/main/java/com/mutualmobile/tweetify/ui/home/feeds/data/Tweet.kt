package com.mutualmobile.tweetify.ui.home.feeds.data

data class Tweet(
    val tUName: String,
    val tUImage: String,
    val tUText: String,
    val tUTime: Long,
    val tUHandler: String,
    val tCommentCount: Long,
    val tLikeCount: Long,
    val tRTCount: Long,
    var metadata: TweetUrlMeta? = null
)

sealed class TweetState {
    object Loading : TweetState()
    object Failure : TweetState()
    class Success(var data: List<Tweet>) : TweetState()
}

data class TweetUrlMeta(
    var title: String? = null,
    var desc: String? = null,
    var image: String? = null,
    var url: String? = null
)