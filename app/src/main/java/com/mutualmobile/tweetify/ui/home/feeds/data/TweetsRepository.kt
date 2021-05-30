package com.mutualmobile.tweetify.ui.home.feeds.data

object TweetsRepository {
    fun fetch(): List<Tweet> {
        val tweetsList = mutableListOf<Tweet>()
        val tweet = Tweet(
            tUName = "Apple Podcasts",
            tUImage = "https://pbs.twimg.com/profile_images/1009097162104246272/07rvgUrd_400x400.jpg",
            tUText = "Celebrate the diversity of Asian American and Pacific Islander communities with this collection of podcasts about identity, history, culture, food and more. http://apple.co/NeverVoiceless #APAHM\n",
            tUTime = 1622360101568L,
            tUHandler = "@ApplePodcasts",
            tCommentCount = 3335465,
            tLikeCount = 1800000,
            tRTCount = 5678
        )
        tweetsList.add(tweet)
        return tweetsList
    }
}