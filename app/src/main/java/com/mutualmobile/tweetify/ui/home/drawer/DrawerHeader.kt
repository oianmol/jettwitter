package com.mutualmobile.tweetify.ui.home.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import com.mutualmobile.tweetify.R
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter

import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme
import com.mutualmobile.tweetify.utils.PHOTO_URL

@Composable
fun DrawerHeader() {
    TweetifySurface(
        color = TweetifyTheme.colors.uiBackground
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            ProfileImage()
            NameDropDown()
            Spacer(modifier = Modifier.height(4.dp))
            UserName()
            Spacer(modifier = Modifier.height(12.dp))
            FollowersFollowing()
        }
    }
}

/**
 *  1. websites par immigration nomination program of different provinces
 *  SINP Ontario, Alberta, NOC, clb benchmark
 */
@Composable
fun FollowersFollowing() {
    Text(buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = TweetifyTheme.colors.textPrimary
            )
        ) {
            append("433")
        }
        append(" Following")
        append("   ")
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = TweetifyTheme.colors.textPrimary
            )
        ) {
            append("52")
        }
        append(" Followers ")
    })
}

@Composable
private fun UserName() {
    Text(
        text = "@_AnmolVerma_",

        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    )
}

@Composable
private fun NameDropDown() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Anmol Verma",

            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = TweetifyTheme.colors.textPrimary
        )
        Icon(
            painterResource(id = R.drawable.ic_vector_arrow_down),
            contentDescription = null,
            tint = TweetifyTheme.colors.accent
        )
    }
}

@Composable
private fun ProfileImage() {
    TweetifySurface(
        shape = CircleShape,
        modifier = Modifier
            .requiredSize(80.dp)
            .padding(12.dp),
        contentColor = TweetifyTheme.colors.uiBackground
    ) {
        Image(
            rememberCoilPainter(PHOTO_URL),
            contentDescription = null
        )
    }
}

@Preview(name = "Light")
@Composable
fun previewHeader() {
    TweetifyTheme {
        DrawerHeader()
    }
}

@Preview(name = "Light + Dark")
@Composable
fun previewHeaderDark() {
    TweetifyTheme(darkTheme = true) {
        DrawerHeader()
    }
}