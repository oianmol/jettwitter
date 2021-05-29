package com.mutualmobile.tweetify.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.mutualmobile.tweetify.R
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme

@Composable
fun TweetifyTopAppBar(function: () -> Unit) {
    TweetifySurface(
        color = TweetifyTheme.colors.uiBackground,
        contentColor = TweetifyTheme.colors.accent
    ) {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_twitter),
                        contentDescription = null
                    )
                }
            },
            backgroundColor = TweetifyTheme.colors.uiBackground,
            navigationIcon = {
                IconButton(onClick = {
                    function()
                }) {
                    Icon(Icons.Filled.Menu, contentDescription = null)
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        painterResource(id = R.drawable.ic_timeline),
                        contentDescription = null
                    )
                }
            }
        )
    }
}