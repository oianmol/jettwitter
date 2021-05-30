package com.mutualmobile.tweetify.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mutualmobile.tweetify.ui.home.bottomnavigation.BottomNavigationScreens

@Composable
fun TweetifyNavigationHost(
    navController: NavHostController
) {
    NavHost(
        navController,
        startDestination = BottomNavigationScreens.Home.route,
        modifier = Modifier.padding(
            PaddingValues(bottom = 46.dp)//fix this padding
        )
    ) {
        composable(BottomNavigationScreens.Home.route) {
            HomeScreen()
        }
        composable(BottomNavigationScreens.Search.route) {
            HomeScreen()
        }
        composable(BottomNavigationScreens.Notifications.route) {
            HomeScreen()
        }
        composable(BottomNavigationScreens.Messages.route) {
            HomeScreen()
        }
    }
}