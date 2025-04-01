package com.example.linux_logic_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.linux_logic_app.R

/**
 * LiloFontFamily und LiloFontFamily2 definieren zwei unterschiedliche Schriftfamilien,
 * die in der App verwendet werden.
 */
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

/**
 * Die LiloTypography definiert die Typographie der App.
 * Es werden unterschiedliche TextStyles für Überschriften, Fließtext, Unterpunkte
 * und Labels festgelegt. Jeder Style weist eine spezifische Schriftfamilie, Gewicht,
 * Schriftgröße und Zeilenhöhe auf, um eine konsistente visuelle Gestaltung zu gewährleisten.
 */
val LiloTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = LiloFontFamily2,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp // Optimierte Zeilenhöhe für bessere Lesbarkeit
    ),
    headlineMedium = TextStyle(
        fontFamily = LiloFontFamily2,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 34.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = LiloFontFamily2,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 22.sp,
        lineHeight = 30.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = LiloFontFamily2,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 19.sp
    ),
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