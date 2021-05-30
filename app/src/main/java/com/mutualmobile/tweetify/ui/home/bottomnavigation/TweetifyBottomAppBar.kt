package com.mutualmobile.tweetify.ui.home.bottomnavigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mutualmobile.tweetify.ui.components.TweetifySurface

val bottomNavigationItems = listOf(
    BottomNavigationScreens.Home,
    BottomNavigationScreens.Search,
    BottomNavigationScreens.Notifications,
    BottomNavigationScreens.Messages
)

@Composable
fun TweetifyBottomAppBar(
    navController: NavHostController
) {
    TweetifySurface(
        color = TweetifyTheme.colors.uiBackground,
        contentColor = TweetifyTheme.colors.accent,
        elevation = 8.dp
    ) {
        var selectedIndex by remember { mutableStateOf(0) }

        BottomNavigation(backgroundColor = TweetifyTheme.colors.uiBackground, elevation = 4.dp) {
            bottomNavigationItems.forEachIndexed { index, screen ->
                BottomNavigationTab(selectedIndex, index, screen, navController)
            }
        }
    }
}

@Composable
private fun RowScope.BottomNavigationTab(
    selectedIndex: Int,
    index: Int,
    screen: BottomNavigationScreens,
    navController: NavHostController
) {
    var selectedIndex1 = selectedIndex
    BottomNavigationItem(
        icon = {
            if (selectedIndex1 == index) {
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
        selected = selectedIndex1 == index,
        alwaysShowLabel = false,
        onClick = {
            // This if check gives us a "singleTop" behavior where we do not create a
            // second instance of the composable if we are already on that destination
            if (selectedIndex1 != index) {
                navController.navigate(screen.route)
                selectedIndex1 = index
            }
        }
    )
}