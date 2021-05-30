package com.mutualmobile.tweetify.ui.home.feeds.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class TweetsRepository {
    val tweetsList = mutableListOf<Tweet>()

    init {
        prepareTweets()
    }

    private fun prepareTweets() {
        tweetsList.add(
            Tweet(
                id = 3,
                tUName = "Sean Cummings",
                tUImage = "https://pbs.twimg.com/profile_images/1367145689910710279/S9GtuoFo_400x400.jpg",
                tUText = "Aggressive Ponytail #freebandnames",
                tUTime = 1622360101468L,
                tUHandler = "@sean_cummings",
                tCommentCount = 3335465,
                tLikeCount = 45,
                tRTCount = 5678
            )
        )
        tweetsList.add(
            Tweet(
                id = 2,
                tUName = "Supabase",
                tUImage = "https://pbs.twimg.com/profile_images/1397471927132844033/jN-wuufb_400x400.jpg",
                tUText = "Supabase Storage is now available! https://supabase.io/blog/2021/03/30/supabase-storage #supalaunchweek",
                tUTime = 1622370333814L,
                tUHandler = "@supabase_io",
                tCommentCount = 33465,
                tLikeCount = 18000,
                tRTCount = 78,
            )
        )
        tweetsList.add(
            Tweet(
                id = 1,
                tUName = "Apple Podcasts",
                tUImage = "https://pbs.twimg.com/profile_images/1009097162104246272/07rvgUrd_400x400.jpg",
                tUText = "Celebrate the diversity of Asian American and Pacific Islander communities with this collection of podcasts about identity, history, culture, food and more. http://apple.co/NeverVoiceless #APAHM\n",
                tUTime = 1622360101568L,
                tUHandler = "@ApplePodcasts",
                tCommentCount = 3335465,
                tLikeCount = 1800000,
                tRTCount = 5678
            )
        )
    }

    fun fetch(): List<Tweet> {
        return tweetsList
    }

    suspend fun fetchUrlMatadata(url: String?): TweetUrlMeta? {
        val tweetUrlMeta = TweetUrlMeta()

        withContext(Dispatchers.IO) {
            val con = Jsoup.connect(url)
            val doc = con.userAgent("Safari").get()
            val ogTags = doc.select("meta[property^=og:]")
            when {
                ogTags.size > 0 ->
                    ogTags.forEachIndexed { index, element ->
                        val tag = ogTags[index]
                        val text = tag.attr("property")
                        when (text) {
                            "og:image" -> tweetUrlMeta.image = tag.attr("content")
                            "og:description" -> tweetUrlMeta.desc = tag.attr("content")
                            "og:url" -> tweetUrlMeta.url = tag.attr("content")
                            "og:title" -> tweetUrlMeta.title = tag.attr("content")
                        }
                    }
            }
            Log.e("tweet meta $url",tweetUrlMeta.toString())
        }
        return tweetUrlMeta
    }

    fun setMetaForTweet(meta: TweetUrlMeta?, tweet: Tweet) {
        tweetsList.firstOrNull { it.id == tweet.id }?.metadata = meta
    }
}