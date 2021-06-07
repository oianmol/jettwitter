package com.mutualmobile.tweetify.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mutualmobile.tweetify.chirpFontFamily
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme

@Composable
fun SearchScreen(modifierPadding: PaddingValues, hashTagParam: String?) {
    TweetifySurface(
        modifier = Modifier.padding(modifierPadding),
    ) {
        val selectedTab = remember {
            mutableStateOf(SearchTabs.ForYou)
        }
        val tabTitles = SearchTabs.values().map { it.title }
        Column {
            scrollableTab(selectedTab, tabTitles)
            Text("Search ${hashTagParam ?: ""}")
        }
    }
}

@Composable
private fun scrollableTab(
    selectedTab: MutableState<SearchTabs>,
    tabTitles: List<String>
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTab.value.ordinal,
        backgroundColor = TweetifyTheme.colors.uiBackground, edgePadding = 0.dp,
        contentColor = TweetifyTheme.colors.accent
    ) {
        tabTitles.forEachIndexed { index, title ->
            Tab(
                selected = isSelected(index, selectedTab),
                onClick = {
                    selectedTab.value = SearchTabs.values().first { it.title == title }
                },
                text = {
                    Text(
                        title,
                        fontFamily = chirpFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = textColor(isSelected(index, selectedTab))
                    )
                })
        }
    }
}

@Composable
fun textColor(selected: Boolean): Color {
    return if (selected) {
        TweetifyTheme.colors.accent
    } else {
        TweetifyTheme.colors.textSecondary
    }
}

@Composable
private fun isSelected(
    index: Int,
    selectedTab: MutableState<SearchTabs>
) = index == selectedTab.value.ordinal