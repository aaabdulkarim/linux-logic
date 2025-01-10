package com.example.linux_logic_app.navigation

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle


/*
Github: https://gist.github.com/stevdza-san/ff9dbec0e072d8090e1e6d16e6b73c91
Stack-Overflow wegen der ClickableText deprecated: https://stackoverflow.com/questions/65567412/jetpack-compose-text-hyperlink-some-section-of-the-text
 */
@Composable
fun HyperlinkText(
    fullText: String,
    linkText: String,
    linkUrl: String,
    onLinkClickLogMessage: String,
    textColor: Color
) {
    val context = LocalContext.current

    val annotatedString = buildAnnotatedString {
        val startIndex = fullText.indexOf(linkText)
        val endIndex = startIndex + linkText.length

        append(fullText)

        // Stil für den klickbaren Text hinzufügen
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            ),
            start = startIndex,
            end = endIndex
        )

        // Annotation für den Link hinzufügen
        addStringAnnotation(
            tag = "URL",
            annotation = linkUrl,
            start = startIndex,
            end = endIndex
        )
    }

    Text(
        text = annotatedString,
        style = MaterialTheme.typography.bodyLarge, // Nutzt die gleiche Schriftart wie `Text`
        modifier = Modifier.clickable {
            annotatedString.getStringAnnotations("URL", 0, annotatedString.length).firstOrNull()?.let { annotation ->
                // Öffnet den Link
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                context.startActivity(intent)

                // Log-Ereignis hinzufügen
                Log.i("HyperlinkText Linux Logic", onLinkClickLogMessage)
            }
        },
        color = textColor
    )
}
