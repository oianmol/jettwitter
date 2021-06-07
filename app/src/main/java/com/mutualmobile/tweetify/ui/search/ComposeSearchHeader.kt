package com.mutualmobile.tweetify.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.coil.rememberCoilPainter
import com.mutualmobile.tweetify.ui.components.TweetifySurface

@Composable
fun ComposeSearchHeader(searchHeader: SearchHeader) {
    TweetifySurface {
        ConstraintLayout {
            val (image, footer) = createRefs()
            Image(
                painter = rememberCoilPainter(request = searchHeader.imageUrl),
                contentDescription = null, modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(280.dp)
                    .fillMaxWidth(), contentScale = ContentScale.Crop
            )
            ComposeSearchHeaderFooter(modifier = Modifier
                .constrainAs(footer) {
                   bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth(), data = searchHeader)
        }
    }

}


