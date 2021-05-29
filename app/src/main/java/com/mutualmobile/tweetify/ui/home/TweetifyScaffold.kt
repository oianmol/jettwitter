package com.mutualmobile.tweetify.ui.home

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.tweetify.ui.home.bottomnavigation.TweetifyBottomAppBar
import com.mutualmobile.tweetify.ui.home.drawer.TweetifyDrawer
import kotlinx.coroutines.launch

@Composable
fun TweetifyScaffold(
    scaffoldState: ScaffoldState,
    navController: NavHostController
) {
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = { TweetifyTopBar(scaffoldState) },
        drawerContent = { TweetifyDrawer() },
        content = { TweetifyNavigationHost(navController) },
        bottomBar = { TweetifyBottomAppBar(navController) }
    )
}



@Composable
private fun TweetifyTopBar(
    scaffoldState: ScaffoldState
) {
    val scope = rememberCoroutineScope()
    TweetifyTopAppBar {
        scope.launch {
            if (scaffoldState.drawerState.isOpen) {
                scaffoldState.drawerState.close()
            } else {
                scaffoldState.drawerState.open()
            }
        }
    }
}
