package com.mutualmobile.tweetify.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SearchTabViewModel @Inject constructor() : ViewModel() {

    var searchTabState by mutableStateOf<SearchState>(SearchState.Loading)
        private set

    var searchHeaderState by mutableStateOf<SearchHeader?>(null)
        private set

    init {
        headerData()
        searchData()
    }

    private fun searchData() {
        val searchDataSet = fetchSearchData()
        searchTabState = SearchState.Success(searchDataSet)
    }

    private fun fetchSearchData(): List<SearchTwitter> {
        val dataSet = mutableListOf<SearchTwitter>()
        val withoutImage =
            SearchTwitter(
                searchCategory = "Technology . Tending",
                hashTagTitle = "#WeLoveYouElon",
                totalTweets = "44.1K Tweets",
                imageUrl = null
            )
        val withImage =
            SearchTwitter(
                searchCategory = "Business Insider India",
                hashTagTitle = "At least 30 killed in train collision in Ghotki, Pakistan",
                totalTweets = "1672 Tweets",
                imageUrl = "https://pbs.twimg.com/semantic_core_img/1401796939469840384/YTjgK_3e?format=jpg&name=small"
            )
        dataSet.add(withoutImage)
        dataSet.add(withImage)
        dataSet.add(withoutImage)
        dataSet.add(withImage)
        dataSet.add(withoutImage)
        dataSet.add(withImage)
        dataSet.add(withoutImage)
        dataSet.add(withImage)
        return dataSet
    }

    private fun headerData() {
        searchHeaderState =
            SearchHeader(
                time = System.currentTimeMillis().minus(TimeUnit.DAYS.toMillis(1)),
                imageUrl = "https://pbs.twimg.com/semantic_core_img/1401576574424469507/I9WPgx1H?format=jpg&name=small",
                category = "News",
                title = "Prince Harry and Meghan Markle announce the birth of their daughter Lilibet ‘Lili’ Diana Mountbatten-Windsor",
                subtitle = "The Duke and Duchess of Sussex on Sunday announced the birth of their daughter Lilibet \"Lili\" Diana Mountbatten-Windsor. The name pays tribute to Harry’s mother, Diana, Princess of Wales, and grandmother, Queen Elizabeth II. Meghan gave birth to a 7lb 11oz daughter in California and “both mother and child are healthy and well,” Meghan’s press secretary said."
            )
    }
}