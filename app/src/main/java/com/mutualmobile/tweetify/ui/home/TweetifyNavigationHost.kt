package com.mutualmobile.tweetify.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.mutualmobile.tweetify.ui.home.DestinationsArguments.HASH_TAG_KEY
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
    shouldShowAppBar: (Boolean) -> Unit,
    navAction: MainActions,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.Home.route,
    ) {
        // Tweet Detail
        ComposeTweetDetailNavigation(navAction, navController, shouldShowAppBar)

        bottomTabs(navAction, padding)
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
            onBack = {
                actions.upPress(backStackEntry, shouldShowAppBar)
            }, hashTagNavigator = actions.navigateToSearch
        )
    }
}

private fun NavGraphBuilder.bottomTabs(
    actions: MainActions,
    padding: PaddingValues
) {
    composable(BottomNavigationScreens.Home.route) { backStack ->
        HomeScreen(
            navigateToTweet = { tweetId ->
                actions.navigateToTweet(tweetId, backStack)
            },
            modifierPadding = padding, navigateToHashTagSearch = { hashTagSearchParam ->
                actions.navigateToSearch(hashTagSearchParam)
            }
        )
    }

    composable(
        "${BottomNavigationScreens.Search.route}/{$HASH_TAG_KEY}",
        arguments = listOf(navArgument(HASH_TAG_KEY) { type = NavType.StringType })
    ) {
        SearchScreen(
            modifierPadding = padding,
            hashTagParam = it.arguments?.getString(HASH_TAG_KEY)
        )
    }

    composable(BottomNavigationScreens.Search.route) {
        SearchScreen(
            modifierPadding = padding,
            hashTagParam = it.arguments?.getString(HASH_TAG_KEY)
        )
    }

    composable(BottomNavigationScreens.Notifications.route) {
        NotificationScreen(modifierPadding = padding)
    }
    composable(BottomNavigationScreens.Messages.route) {
        MessagesScreen(modifierPadding = padding)
    }

}

object DestinationsArguments {
    const val TWEET_ID_KEY = "tweetId"
    const val HASH_TAG_KEY = "hashTagParam"

}

/**
 * Models the navigation actions in the app.
 */
class MainActions(
    private val navController: NavHostController,
    shouldShowAppBar: (Boolean) -> Unit
) {
    val navigateToTweet = { tweetId: String, from: NavBackStackEntry ->
        if (from.lifecycleIsResumed()) {
            shouldShowAppBar(false)
            navController.navigate(route = "${TwitterNavigationScreen.TweetDetailScreen.route}/$tweetId")
        }
    }

    val navigateToSearch = { hashTag: String ->
        navController.navigate(route = "${BottomNavigationScreens.Search.route}/$hashTag") {
            popUpTo(navController.graph.startDestinationId)
        }
    }

    val switchBottomTab = { tabRoute: String, currentRoute: String ->
        //shouldShowAppBar(true)
        if (tabRoute != currentRoute) {
            navController.navigate(route = tabRoute) {
                popUpTo(navController.graph.startDestinationId)
                // Avoid multiple copies of the same destination when re-selecting the same item
                launchSingleTop = true
            }
        }

    }

    fun upPress(from: NavBackStackEntry, shouldShowAppBar: (Boolean) -> Unit) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            shouldShowAppBar(true)
            navController.navigateUp()
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED