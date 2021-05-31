package com.mutualmobile.tweetify.ui.home

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.tweetify.ui.home.bottomnavigation.TweetifyHomeBottomAppBar
import com.mutualmobile.tweetify.ui.home.drawer.TweetifyHomeDrawer
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme
import kotlinx.coroutines.launch

@Composable
fun TweetifyScaffold() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val (shouldShowAppBar, updateAppBarVisibility) = remember { mutableStateOf(true) }
    val navActions = remember(navController) { MainActions(navController, updateAppBarVisibility) }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = { TweetifyHomeTopBar(scaffoldState, shouldShowAppBar) },
        drawerContent = { TweetifyHomeDrawer() },
        bottomBar = {
            TweetifyHomeBottomAppBar(
                navActions.switchBottomTab,
                navController,
                shouldShowAppBar
            )
        },
        drawerShape = MaterialTheme.shapes.medium,
        drawerElevation = DrawerDefaults.Elevation,
        drawerBackgroundColor = TweetifyTheme.colors.uiBackground,
        drawerContentColor = TweetifyTheme.colors.textSecondary,
        drawerScrimColor = TweetifyTheme.colors.uiBorder,
        backgroundColor = TweetifyTheme.colors.uiBackground,
        contentColor = TweetifyTheme.colors.textSecondary,
    ) { padding ->
        TweetifyNavigationHost(
            navController,
            padding,
            shouldShowAppBar = updateAppBarVisibility,
            navAction = navActions
        )
    }
}


@Composable
private fun TweetifyHomeTopBar(
    scaffoldState: ScaffoldState,
    shouldShowAppBar: Boolean
) {
    if (shouldShowAppBar) {
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

}
