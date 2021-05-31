package com.mutualmobile.tweetify.ui.home.feeds.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class TweetsRepository {
    private val tweetsList = mutableListOf<Tweet>()
    private val cacheUrlMap = hashMapOf<String, TweetUrlMeta>()

    init {
        prepareTweets()
    }

    private fun prepareTweets() {
        tweetsList.add(
            Tweet(
                tUid = "1",
                tUName = "Sean Cummings",
                tUImage = "https://pbs.twimg.com/profile_images/1209802752819261440/jvir-xNm_400x400.jpg",
                tUText = "Aggressive Ponytail #freebandnames",
                tUTime = 1622360101468L,
                tUHandler = "@sean_cummings",
                tCommentCount = 44,
                tLikeCount = 45,
                tRTCount = 44
            )
        )
        tweetsList.add(
            Tweet(
                tUid = "2",
                tUName = "Sean Cummings",
                tUImage = "https://pbs.twimg.com/profile_images/1367145689910710279/S9GtuoFo_400x400.jpg",
                tUText = " As checked the refund of INR 3000 is under process it will reflect to the mode of payment in 5-7 working days so, request you to please wait for the same as it is already escalated to the team to process the refund on priority. Your patience is highly appreciated.\n",
                tUTime = 1622360101468L,
                tUHandler = "@sean_cummings",
                tCommentCount = 44,
                tLikeCount = 45,
                tRTCount = 5
            )
        )
        tweetsList.add(
            Tweet(
                tUid = "3",
                tUName = "Supabase",
                tUImage = "https://pbs.twimg.com/profile_images/1397471927132844033/jN-wuufb_400x400.jpg",
                tUText = "Supabase Storage is now available! https://supabase.io/blog/2021/03/30/supabase-storage #supalaunchweek",
                tUTime = 1622370333814L,
                tUHandler = "@supabase_io",
                tCommentCount = 44,
                tLikeCount = 45,
                tRTCount = 5
            )
        )
        tweetsList.add(
            Tweet(
                tUid = "4",
                tUName = "Apple Podcasts",
                tUImage = "https://pbs.twimg.com/profile_images/1009097162104246272/07rvgUrd_400x400.jpg",
                tUText = "Celebrate the diversity of Asian American and Pacific Islander communities with this collection of podcasts about identity, history, culture, food and more. http://apple.co/NeverVoiceless #APAHM\n",
                tUTime = 1622360101568L,
                tUHandler = "@ApplePodcasts",
                tCommentCount = 44,
                tLikeCount = 45,
                tRTCount = 5
            )
        )
        tweetsList.add(
            Tweet(
                tUid = "4",
                tUName = "Sean Cummings",
                tUImage = "https://pbs.twimg.com/profile_images/1209802752819261440/jvir-xNm_400x400.jpg",
                tUText = "Aggressive Ponytail #freebandnames",
                tUTime = 1622360101468L,
                tUHandler = "@sean_cummings",
                tCommentCount = 44,
                tLikeCount = 45,
                tRTCount = 5
            )
        )
        tweetsList.add(
            Tweet(
                tUid = "5",
                tUName = "Sean Cummings",
                tUImage = "https://pbs.twimg.com/profile_images/1367145689910710279/S9GtuoFo_400x400.jpg",
                tUText = " As checked the refund of INR 3000 is under process it will reflect to the mode of payment in 5-7 working days so, request you to please wait for the same as it is already escalated to the team to process the refund on priority. Your patience is highly appreciated.\n",
                tUTime = 1622360101468L,
                tUHandler = "@sean_cummings",
                tCommentCount = 44,
                tLikeCount = 45,
                tRTCount = 5
            )
        )
        tweetsList.add(
            Tweet(
                tUid = "6",
                tUName = "Supabase",
                tUImage = "https://pbs.twimg.com/profile_images/1397471927132844033/jN-wuufb_400x400.jpg",
                tUText = "Supabase Storage is now available! https://supabase.io/blog/2021/03/30/supabase-storage #supalaunchweek",
                tUTime = 1622370333814L,
                tUHandler = "@supabase_io",
                tCommentCount = 44,
                tLikeCount = 45,
                tRTCount = 5
            )
        )
        tweetsList.add(
            Tweet(
                tUid = "7",
                tUName = "Apple Podcasts",
                tUImage = "https://pbs.twimg.com/profile_images/1009097162104246272/07rvgUrd_400x400.jpg",
                tUText = "Celebrate the diversity of Asian American and Pacific Islander communities with this collection of podcasts about identity, history, culture, food and more. http://apple.co/NeverVoiceless #APAHM\n",
                tUTime = 1622360101568L,
                tUHandler = "@ApplePodcasts",
                tCommentCount = 44,
                tLikeCount = 45,
                tRTCount = 5
            )
        )
    }

    suspend fun fetchAsync(): List<Tweet> {
        return tweetsList
    }

    suspend fun fetchUrlMatadata(url: String?): TweetUrlMeta? {
        val tweetUrlMeta = TweetUrlMeta()
        if (cacheUrlMap.containsKey(url)) return cacheUrlMap[url!!]

        withContext(Dispatchers.IO) {
            val con = Jsoup.connect(url)
            val doc = con.userAgent("Mozilla").get()
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
            cacheUrlMap[url!!] = tweetUrlMeta
            Log.e("tweet meta $url", tweetUrlMeta.toString())
        }
        return tweetUrlMeta
    }

}