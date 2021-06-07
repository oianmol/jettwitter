package com.mutualmobile.tweetify.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.mutualmobile.tweetify.R
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme

@Composable
fun SearchResults(searchVM: SearchTabViewModel) {
    val searchItems = searchVM.searchTabState
    val searchHeader = searchVM.searchHeaderState

    LazyColumn {
        if (searchHeader != null) {
            item {
                ComposeSearchHeader(searchHeader)
            }
        }
        if (searchItems is SearchState.Success) {
            items(searchItems.searchData.size) {
                searchItems.searchData.forEach { searchTwitter ->
                    ComposeSearchItem(searchTwitter)
                }
            }
        }
    }
}

@Composable
fun ComposeSearchItem(searchTwitter: SearchTwitter) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)) {
            Text(
                searchTwitter.searchCategory, fontWeight = FontWeight.Bold,
                color = TweetifyTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                searchTwitter.hashTagTitle,
                color = TweetifyTheme.colors.textPrimary,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                searchTwitter.totalTweets,
                color = TweetifyTheme.colors.textPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        OtherEnd(searchTwitter)
    }
}

@Composable
private fun OtherEnd(searchTwitter: SearchTwitter) {
    if (searchTwitter.imageUrl == null) {
        Icon(
            painterResource(id = R.drawable.ic_vector_overflow),
            modifier = Modifier
                .padding(12.dp),
            contentDescription = null,
            tint = Color.Gray
        )
    } else {
        TweetifySurface(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .requiredSize(80.dp),
            contentColor = TweetifyTheme.colors.uiBackground
        ) {
            Image(
                rememberCoilPainter(searchTwitter.imageUrl),
                contentDescription = null
            )
        }
    }
}


