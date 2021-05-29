package com.mutualmobile.tweetify.ui.home.drawer

import com.mutualmobile.tweetify.R

sealed class DrawerNavigationScreens(
    val route: String,
    val icon: Int,
) {
    object Profile :
        DrawerNavigationScreens(
            "Profile",
            R.drawable.ic_profile,
        )

    object Lists :
        DrawerNavigationScreens(
            "Lists",
            R.drawable.ic_lists,
        )

    object Topics :
        DrawerNavigationScreens(
            "Topics",
            R.drawable.ic_topics_stroke
        )

    object Bookmarks :
        DrawerNavigationScreens(
            "Bookmarks",
            R.drawable.ic_vector_bookmark_stroke,
        )

    object Moments :
        DrawerNavigationScreens(
            "Moments",
            R.drawable.ic_moments,
        )
}