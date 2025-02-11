package com.example.linux_logic_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.linux_logic_app.R

val LiloFontFamily = FontFamily(
    Font(R.font.ubuntumono_regular, FontWeight.Normal),
    Font(R.font.ubuntumono_bold, FontWeight.Bold),
    Font(R.font.ubuntumono_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.ubuntumono_bolditalic, FontWeight.Bold, FontStyle.Italic)
)

val LiloFontFamily2 = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_italic, FontWeight.Normal, FontStyle.Italic),
)

// Definieren der Typographie von Linux Logic
val LiloTypography = Typography(
    // Große Überschriften
    headlineLarge = TextStyle(
        fontFamily = LiloFontFamily2,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp // Zeilenhöhe für bessere Lesbarkeit
    ),
    // Mittlere Überschriften
    headlineMedium = TextStyle(
        fontFamily = LiloFontFamily2,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 34.sp
    ),
    // Kleine Unterüberschriften
    headlineSmall = TextStyle(
        fontFamily = LiloFontFamily2,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 22.sp,
        lineHeight = 30.sp
    ),
    // Normaler Fließtext
    bodyLarge = TextStyle(
        fontFamily = LiloFontFamily2,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 19.sp
    ),
    // Unterpunkte
    bodyMedium = TextStyle(
        fontFamily = LiloFontFamily2,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 13.sp,
        lineHeight = 17.sp
    ),
    bodySmall = TextStyle(
        fontFamily = LiloFontFamily2,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 17.sp
    ),
    // Beschriftungen, z. B. für Buttons oder kleine Labels
    labelLarge = TextStyle(
        fontFamily = LiloFontFamily2,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 30.sp
    ),
    labelMedium = TextStyle(
        fontFamily = LiloFontFamily2,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),
    labelSmall = TextStyle(
        fontFamily = LiloFontFamily2,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 26.sp
    )
)

// Set of Material typography styles to start with
/* val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    */
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */