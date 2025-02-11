package com.example.linux_logic_app.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Definition der Farbpalette jeweils für Light-Mode und Dark-Mode
private val DarkColorScheme = darkColorScheme(
    /*primary = LiloMain,
    secondary = LiloMainSec,
    tertiary = LiloOrange,
    background = LiloDark,
    surface = LiloDarkSec,
    onPrimary = LiloDarkText,
    onSecondary = LiloDarkText,
    onTertiary = LiloDarkText,
    onBackground = LiloDarkText,
    onSurface = LiloDarkText*/

    /* primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
    */
)

private val LightColorScheme = lightColorScheme(
    /*primary = LiloMain,
    secondary = LiloMainSec,
    tertiary = LiloOrange,
    background = LiloLight,
    surface = LiloLightSec,
    onPrimary = LiloLightText,
    onSecondary = LiloLightText,
    onTertiary = LiloLightText,
    onBackground = LiloLightText,
    onSurface = LiloLightText*/

    /* primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun Linux_logic_appTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        // Standard-Dark-Mode-Farbpalette
        darkTheme -> DarkColorScheme
        // Standard-Light-Mode-Farbpalette
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = LiloTypography,
        content = content
    )
}