package com.mutualmobile.tweetify.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.mutualmobile.tweetify.ui.home.feeds.ComposeTime

@Composable
fun ComposeSearchHeaderFooter(modifier: Modifier, data: SearchHeader) {
    Column(modifier = modifier.background(Color.Black.copy(alpha = 0.4F))) {
        Row(modifier = Modifier.padding(horizontal = 16.dp,vertical = 4.dp)) {
            Text(
                text = data.category,
                color = Color.White,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            ComposeTime(
                data.time,
                color = Color.White
            )
        }
        Text(
            text = data.title,
            modifier = Modifier.padding(horizontal = 16.dp,vertical = 4.dp),
            fontSize = 16.sp,

            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            color = Color.White
        )
        Text(
            text = data.subtitle,
            modifier = Modifier.padding(horizontal = 16.dp,vertical = 4.dp),
            fontSize = 14.sp,

            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White
        )
    }
}