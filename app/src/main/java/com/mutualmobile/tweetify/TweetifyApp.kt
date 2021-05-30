package com.mutualmobile.tweetify

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.home.*
import com.mutualmobile.tweetify.ui.theme.AlphaNearOpaque
import com.mutualmobile.tweetify.ui.theme.AlphaNearTransparent

val chirpFontFamily = FontFamily(
    Font(R.font.chirp_bold, FontWeight.Bold),
    Font(R.font.chirp_heavy, FontWeight.ExtraBold),
    Font(R.font.chirp_light, FontWeight.Light),
    Font(R.font.chirp_medium, FontWeight.SemiBold),
    Font(R.font.chirp_regular, FontWeight.Normal),
    Font(R.font.chirp_thin, FontWeight.Thin)
)

@Composable
fun TweetifyApp(finishActivity: () -> Unit) {

    val navController = rememberNavController()

    ProvideWindowInsets {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        TweetifySurface(
            color = TweetifyTheme.colors.statusBarColor.copy(alpha = AlphaNearTransparent),
            modifier = Modifier.fillMaxSize()
        ) {
            TweetifyScaffold(scaffoldState, navController)
        }
    }
}









