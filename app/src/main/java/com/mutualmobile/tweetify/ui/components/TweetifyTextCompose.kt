package com.mutualmobile.tweetify.ui.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme
import java.util.regex.Pattern

const val HASH_TAG = "HASH"
const val URL_TAG = "URL"

@Composable
fun ComposeTweetifyFeedText(
    text: String,
    modifier: Modifier = Modifier,
    urlRecognizer: (url: String) -> Unit
) {
    val uriHandler = LocalUriHandler.current
    val layoutResult = remember {
        mutableStateOf<TextLayoutResult?>(null)
    }
    val linksList = extractSpans(text, listOf<Pattern>(urlPattern, hashTagPattern))
    val annotatedString = buildAnnotatedString {
        append(text)
        if (linksList.isNotEmpty() && linksList.first().tag == URL_TAG) {
            urlRecognizer.invoke(linksList.first().spanText)
        }
        linksList.forEach {
            addStyle(
                style = SpanStyle(
                    color = TweetifyTheme.colors.accent,
                    textDecoration = TextDecoration.Underline
                ),
                start = it.start,
                end = it.end
            )
            addStringAnnotation(
                tag = it.tag,
                annotation = it.spanText,
                start = it.start,
                end = it.end
            )
        }
    }
    Text(text = annotatedString,
        style = MaterialTheme.typography.body1,
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures { offsetPosition ->
                layoutResult.value?.let {
                    val position = it.getOffsetForPosition(offsetPosition)
                    annotatedString.getStringAnnotations(position, position).firstOrNull()
                        ?.let { result ->
                            if (result.tag == URL_TAG) {
                                uriHandler.openUri(result.item)
                            } else {

                            }
                        }
                }
            }
        },
        onTextLayout = { layoutResult.value = it }
    )
}

private val urlPattern: Pattern = Pattern.compile(
    "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
            + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
            + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
    Pattern.CASE_INSENSITIVE or Pattern.MULTILINE or Pattern.DOTALL
)

private val hashTagPattern = Pattern.compile(".*?\\s(#\\w+).*?")

fun extractSpans(text: String, patterns: List<Pattern>): List<SpanInfos> {
    val links = arrayListOf<SpanInfos>()
    patterns.forEach { pattern ->
        val matcher = pattern.matcher(text)
        var matchStart: Int
        var matchEnd: Int

        while (matcher.find()) {
            matchStart = matcher.start(1)
            matchEnd = matcher.end()

            var checkText = text.substring(matchStart, matchEnd)

            if (checkText.startsWith("#")) {
                links.add(SpanInfos(checkText, matchStart, matchEnd, HASH_TAG))
            } else {
                if (!checkText.startsWith("http://") && !checkText.startsWith("https://")) {
                    checkText = "https://$checkText"
                }
                links.add(SpanInfos(checkText, matchStart, matchEnd, URL_TAG))
            }
        }
    }

    return links
}

data class SpanInfos(
    val spanText: String,
    val start: Int,
    val end: Int,
    val tag: String,
)

