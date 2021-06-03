package com.mutualmobile.tweetify.ui.messages

data class TwitterMessage(
    val imgUrl: String,
    val fullName: String,
    val userName: String,
    val dateTime: Long,
    val lastMessage: String
)
