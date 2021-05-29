package com.mutualmobile.tweetify.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mutualmobile.tweetify.ui.home.bottomnavigation.BottomNavigationScreens

@Composable
fun TweetifyNavigationHost(
    navController: NavHostController
) {
    NavHost(navController, startDestination = BottomNavigationScreens.Home.route) {
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