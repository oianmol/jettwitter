package com.mutualmobile.tweetify.ui.home.bottomnavigation

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme
import androidx.compose.ui.res.painterResource
import com.mutualmobile.tweetify.ui.components.TweetifySurface

val bottomNavigationItems = listOf(
    BottomNavigationScreens.Home,
    BottomNavigationScreens.Search,
    BottomNavigationScreens.Notifications,
    BottomNavigationScreens.Messages
)

@Composable
fun TweetifyBottomAppBar(
    navController: NavHostController) {
    TweetifySurface(
        color = TweetifyTheme.colors.uiBackground,
        contentColor = TweetifyTheme.colors.accent
    ) {
        var selectedIndex by remember { mutableStateOf(0) }

        BottomNavigation(backgroundColor = TweetifyTheme.colors.uiBackground) {
            bottomNavigationItems.forEachIndexed { index, screen ->
                BottomNavigationItem(
                    icon = {
                        if (selectedIndex == index) {
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
                    selected = selectedIndex == index,
                    alwaysShowLabel = false,
                    onClick = {
                        // This if check gives us a "singleTop" behavior where we do not create a
                        // second instance of the composable if we are already on that destination
                        if (selectedIndex != index) {
                            navController.navigate(screen.route)
                            selectedIndex = index
                        }
                    }
                )
            }
        }
    }
}