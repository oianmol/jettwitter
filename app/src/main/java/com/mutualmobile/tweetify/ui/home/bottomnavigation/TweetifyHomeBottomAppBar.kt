package com.mutualmobile.tweetify.ui.home.bottomnavigation

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
fun TweetifyHomeBottomAppBar(
    switchBottomTab: (String, String) -> Unit,
    navController: NavHostController,
    shouldShowAppBar: Boolean
) {
    val currentRoute = currentRoute(navController)

    if (shouldShowAppBar) {
        TweetifySurface(
            color = TweetifyTheme.colors.uiBackground,
            contentColor = TweetifyTheme.colors.accent,
            elevation = 8.dp
        ) {
            BottomNavigation(
                backgroundColor = TweetifyTheme.colors.uiBackground,
                elevation = 4.dp
            ) {
                bottomNavigationItems.forEach { screen ->
                    BottomNavigationTab(screen, switchBottomTab, currentRoute)
                }
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
private fun RowScope.BottomNavigationTab(
    screen: BottomNavigationScreens,
    switchBottomTab: (String, String) -> Unit,
    currentRoute: String?
) {
    BottomNavigationItem(
        icon = {
            ComposeBottomNavIconState(currentRoute, screen)
        },
        selected = isSelected(currentRoute, screen),
        alwaysShowLabel = true,
        onClick = {
            currentRoute?.let { switchBottomTab(screen.route, it) }
        }
    )
}

@Composable
private fun isSelected(
    currentRoute: String?,
    screen: BottomNavigationScreens
) = currentRoute == screen.route || currentRoute?.contains(screen.route) == true

@Composable
private fun ComposeBottomNavIconState(
    currentRoute: String?,
    screen: BottomNavigationScreens
) {
    when  {
        isSelected(currentRoute = currentRoute, screen = screen) -> {
            Icon(
                painterResource(screen.icon),
                contentDescription = null,
            )
        }
        else -> {
            Icon(
                painterResource(screen.iconStroke),
                contentDescription = null,
            )
        }
    }
}