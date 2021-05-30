package com.mutualmobile.tweetify.ui.home.drawer

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mutualmobile.tweetify.chirpFontFamily
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme


val drawerNavigationItems = listOf(
    DrawerNavigationScreens.Profile,
    DrawerNavigationScreens.Lists,
    DrawerNavigationScreens.Topics,
    DrawerNavigationScreens.Bookmarks,
    DrawerNavigationScreens.Moments,
)

@Composable
fun DrawerMenuOptions() {
    TweetifySurface(
        color = TweetifyTheme.colors.uiBackground,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            drawerNavigationItems.forEachIndexed { index, screen ->
                Row(modifier = Modifier.padding(14.dp),verticalAlignment = Alignment.CenterVertically) {
                    NavigationIcon(screen)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(screen.route, fontFamily = chirpFontFamily,fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
private fun NavigationIcon(
    screen: DrawerNavigationScreens
) {
    Icon(
        painterResource(screen.icon),
        contentDescription = null,
        modifier = Modifier.size(28.dp),
    )
}


@Preview("Light")
@Composable
fun DrawerMenuOptionsPreview() {
    TweetifyTheme {
        DrawerMenuOptions()
    }
}

@Preview("Light+Dark")
@Composable
fun DrawerMenuOptionsPreviewDark() {
    TweetifyTheme(darkTheme = true) {
        DrawerMenuOptions()
    }
}