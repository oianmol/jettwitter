package com.mutualmobile.tweetify.ui.home.bottomnavigation

import com.mutualmobile.tweetify.R

sealed class BottomNavigationScreens(
    val route: String,
    val icon: Int,
    val iconStroke: Int
) {
    object Home :
        BottomNavigationScreens(
            "Home",
            R.drawable.ic_home_bottom_filled,
            R.drawable.ic_home_bottom_stroke
        )

    object Search :
        BottomNavigationScreens(
            "Search",
            R.drawable.ic_search_bottom,
            R.drawable.ic_search_bottom_stroke
        )

    object Notifications :
        BottomNavigationScreens(
            "Notifications",
            R.drawable.ic_notifications_bottom,
            R.drawable.ic_notifications_bottom_stroke
        )

    object Messages :
        BottomNavigationScreens(
            "Messages",
            R.drawable.ic_messages_bottom,
            R.drawable.ic_messages_bottom_stroke
        )
}