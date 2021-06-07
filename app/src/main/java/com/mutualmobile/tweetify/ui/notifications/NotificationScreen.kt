package com.mutualmobile.tweetify.ui.notifications

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.search.textColor
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme

@Composable
fun NotificationScreen(modifierPadding: PaddingValues) {
    val notificationVM : NotificationsViewModel = hiltViewModel()
    TweetifySurface(
        modifier = Modifier.padding(modifierPadding),
    ) {
        val selectedTab = remember {
            mutableStateOf(NotificationsTab.All)
        }
        val tabTitles = NotificationsTab.values().map { it.title }
        Column {
            NotificationsTab(selectedTab, tabTitles)
            NotificationsResults(notificationVM)
        }
    }
}



@Composable
fun NotificationsTab(selectedTab: MutableState<NotificationsTab>, tabTitles: List<String>) {
    TabRow(
        selectedTabIndex = selectedTab.value.ordinal,
        backgroundColor = TweetifyTheme.colors.uiBackground,
        contentColor = TweetifyTheme.colors.accent
    ) {
        tabTitles.forEachIndexed { index, title ->
            Tab(
                selected = isSelected(index, selectedTab),
                onClick = {
                    selectedTab.value = NotificationsTab.values().first { it.title == title }
                },
                text = {
                    Text(
                        title,

                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = textColor(isSelected(index, selectedTab))
                    )
                })
        }
    }
}

@Composable
private fun isSelected(
    index: Int,
    selectedTab: MutableState<NotificationsTab>
) = index == selectedTab.value.ordinal