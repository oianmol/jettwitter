package com.mutualmobile.tweetify.ui.home.bottomnavigation

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mutualmobile.tweetify.ui.components.TweetifySurface

val bottomNavigationItems = listOf(
    BottomNavigationScreens.Home,
    BottomNavigationScreens.Search,
    BottomNavigationScreens.Notifications,
    BottomNavigationScreens.Messages
)

@Composable
fun TweetifyBottomAppBar(
    switchBottomTab: (String) -> Unit,
    navController: NavHostController
) {
    TweetifySurface(
        color = TweetifyTheme.colors.uiBackground,
        contentColor = TweetifyTheme.colors.accent,
        elevation = 8.dp
    ) {
        BottomNavigation(backgroundColor = TweetifyTheme.colors.uiBackground, elevation = 4.dp) {
            bottomNavigationItems.forEach { screen ->
                BottomNavigationTab(screen, switchBottomTab, navController)
            }
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
private fun RowScope.BottomNavigationTab(
    screen: BottomNavigationScreens,
    switchBottomTab: (String) -> Unit,
    navController: NavHostController
) {
    val currentRoute = currentRoute(navController)

    BottomNavigationItem(
        icon = {
            if (currentRoute == screen.route) {
                Icon(
                    painterResource(screen.icon),
                    contentDescription = null,
                )
            } else {
                Icon(
                    painterResource(screen.iconStroke),
                    contentDescription = null,
                )
            }

        },
        selected = currentRoute == screen.route,
        alwaysShowLabel = true,
        onClick = {
            switchBottomTab(screen.route)
        }
    )
}