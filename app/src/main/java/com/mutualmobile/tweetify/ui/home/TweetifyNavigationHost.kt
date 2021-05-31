package com.mutualmobile.tweetify.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.mutualmobile.tweetify.TweetifyApp
import com.mutualmobile.tweetify.ui.home.DestinationsArguments.MAIN_ROUTE
import com.mutualmobile.tweetify.ui.home.DestinationsArguments.TWEET_ID_KEY
import com.mutualmobile.tweetify.ui.home.bottomnavigation.BottomNavigationScreens
import com.mutualmobile.tweetify.ui.home.bottomnavigation.TwitterNavigationScreen
import com.mutualmobile.tweetify.ui.home.feeds.tweetdetails.TwitterDetailsScreen
import com.mutualmobile.tweetify.ui.messages.MessagesScreen
import com.mutualmobile.tweetify.ui.notifications.NotificationScreen
import com.mutualmobile.tweetify.ui.search.SearchScreen

@Composable
fun TweetifyNavigationHost(
    navController: NavHostController,
    padding: PaddingValues,
    startDestination: String = MAIN_ROUTE,
    shouldShowAppBar: (Boolean) -> Unit,
    navAction: MainActions,
    finishActivity: () -> Unit = {},
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(padding)
    ) {
        composable(
            route = MAIN_ROUTE
        ) {
            TweetifyApp { finishActivity() }
        }

        // Tweet Detail
        ComposeTweetDetailNavigation(navAction, navController, shouldShowAppBar)

        /*
        * Construct a nested NavGraph with app bottom tabs
        */
        navigation(
            route = MAIN_ROUTE,
            startDestination = BottomNavigationScreens.Home.route
        ) {
            bottomTabs(navAction)
        }

    }
}

private fun NavGraphBuilder.ComposeTweetDetailNavigation(
    actions: MainActions,
    navController: NavHostController,
    shouldShowAppBar: (Boolean) -> Unit
) {
    composable(
        "${TwitterNavigationScreen.TweetDetailScreen.route}/{$TWEET_ID_KEY}",
        arguments = listOf(navArgument(TWEET_ID_KEY) { type = NavType.StringType })
    ) { backStackEntry: NavBackStackEntry ->
        BackHandler {
            shouldShowAppBar(true)
            navController.navigateUp()
        }
        TwitterDetailsScreen(
            tweetId = backStackEntry.arguments?.getString(TWEET_ID_KEY),
            onBack = actions.upPress(backStackEntry),
        )
    }
}

private fun NavGraphBuilder.bottomTabs(
    actions: MainActions
) {
    composable(BottomNavigationScreens.Home.route) {
        HomeScreen(navigateToTweet = { tweetId ->
            actions.navigateToTweet(tweetId, it)
        })
    }
    composable(BottomNavigationScreens.Search.route) {
        SearchScreen()
    }
    composable(BottomNavigationScreens.Notifications.route) {
        NotificationScreen()
    }
    composable(BottomNavigationScreens.Messages.route) {
        MessagesScreen()
    }

}

object DestinationsArguments {
    const val TWEET_ID_KEY = "tweetId"
    const val MAIN_ROUTE = "main"
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController, shouldShowAppBar: (Boolean) -> Unit) {
    val navigateToTweet = { tweetId: String, from: NavBackStackEntry ->
        if (from.lifecycleIsResumed()) {
            shouldShowAppBar(false)
            navController.navigate(route = "${TwitterNavigationScreen.TweetDetailScreen.route}/$tweetId")
        }
    }

    val switchBottomTab = { tabRoute: String ->
        //shouldShowAppBar(true)
        navController.navigate(route = tabRoute){
            popUpTo(navController.graph.startDestinationId)
            // Avoid multiple copies of the same destination when re-selecting the same item
            launchSingleTop = true
        }
    }

    val upPress: (rom: NavBackStackEntry) -> Unit = { from ->
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            shouldShowAppBar(true)
            navController.navigateUp()
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED