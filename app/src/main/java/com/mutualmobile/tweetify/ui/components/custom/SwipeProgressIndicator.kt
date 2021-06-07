package com.mutualmobile.tweetify.ui.components.custom

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.mutualmobile.tweetify.R
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme

@Composable
fun SwipeProgressIndicator(
    swipeRefreshState: SwipeRefreshState,
    refreshTriggerDistance: Dp,
) {
    if (swipeRefreshState.isSwipeInProgress) {
        val trigger = with(LocalDensity.current) { refreshTriggerDistance.toPx() }
        val progress = (swipeRefreshState.indicatorOffset / trigger).coerceIn(0f, 2f)
        val progressPadding = (swipeRefreshState.indicatorOffset / trigger) * 32f
        val angle: Float = progress * 360F
        if(progressPadding>0){
            TweetifySurface(
                shape = CircleShape,
                modifier = Modifier.padding(top = progressPadding.dp),
                contentColor = TweetifyTheme.colors.accent
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_refresh_24),
                    contentDescription = null,
                    modifier = Modifier
                        .rotate(angle)
                        .size(36.dp)
                )
            }
        }

    }
    if (swipeRefreshState.isRefreshing) {
        TweetifySurface(
            shape = CircleShape,
            modifier = Modifier.padding(top = 32.dp),
            contentColor = TweetifyTheme.colors.accent
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(4.dp)
                    .size(36.dp), color = TweetifyTheme.colors.accent
            )
        }
    }

}