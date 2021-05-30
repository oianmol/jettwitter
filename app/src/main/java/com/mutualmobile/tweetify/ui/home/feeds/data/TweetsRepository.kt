package com.mutualmobile.tweetify.ui.home.feeds.data

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
        tweetsList.add(
            Tweet(
                tUName = "Supabase",
                tUImage = "https://pbs.twimg.com/profile_images/1397471927132844033/jN-wuufb_400x400.jpg",
                tUText = "Supabase Storage is now available! https://supabase.io/blog/2021/03/30/supabase-storage #supalaunchweek",
                tUTime = 1622370333814L,
                tUHandler = "@supabase_io",
                tCommentCount = 33465,
                tLikeCount = 18000,
                tRTCount = 78
            )
        )
    }

    fun fetch(): List<Tweet> {
        return tweetsList
    }

    suspend fun printUrlMatadata(url: String?) {
        withContext(Dispatchers.IO){
            val doc: Document = Jsoup.connect("http://www.example.com").get()
            try{
                val keywords: String = doc.select("meta[name=keywords]").first().attr("content")
                println("Meta keyword : $keywords")
                val description: String = doc.select("meta[name=description]").get(0).attr("content")
                println("Meta description : $description")
            }catch (ex:Exception){

            }

        }
    }
}