package com.mutualmobile.tweetify.ui.home

import androidx.compose.material.DrawerDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.mutualmobile.tweetify.ui.home.bottomnavigation.TweetifyBottomAppBar
import com.mutualmobile.tweetify.ui.home.drawer.TweetifyDrawer
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme
import kotlinx.coroutines.launch

@Composable
fun TweetifyScaffold(
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    finishActivity: () -> Unit
) {

    val (shouldShowAppBar, updateAppBarVisibility) = remember { mutableStateOf(true) }
    val navActions = remember(navController) { MainActions(navController, updateAppBarVisibility) }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = { TweetifyTopBar(scaffoldState, shouldShowAppBar) },
        drawerContent = { TweetifyDrawer() },
        bottomBar = { TweetifyBottomAppBar(navActions.switchBottomTab,navController) },
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
            navAction = navActions,
            finishActivity = finishActivity
        )
    }
}


@Composable
private fun TweetifyTopBar(
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
