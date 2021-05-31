package com.mutualmobile.tweetify.ui.home.drawer

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.theme.AlphaNearOpaque
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme

@Composable
fun TweetifyHomeDrawer() {
    TweetifySurface(
        color = TweetifyTheme.colors.uiBackground,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column {
            DrawerHeader()
            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color.Gray.copy(AlphaNearOpaque), thickness = 0.5.dp)
            DrawerMenuOptions()
            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color.Gray.copy(AlphaNearOpaque), thickness = 0.5.dp)
            Spacer(modifier = Modifier.fillMaxHeight())
            DrawerFooter()
        }
    }
}

@Composable
fun DrawerFooter() {

}


@Preview
@Composable
fun PreviewDrawer(){
   TweetifyTheme {
       TweetifyHomeDrawer()
   }
}



@Preview("Dark")
@Composable
fun PreviewDrawerDark(){
    TweetifyTheme(darkTheme = true) {
        TweetifyHomeDrawer()
    }
}


