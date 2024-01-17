package com.example.animecollection.core.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColorScheme(
    primary = AnimeCollectionViolet,
    secondary = AnimeCollectionYellow,
    onSecondary = AnimeCollectionBlack,
    onPrimary = AnimeCollectionWhite,
    background = AnimeCollectionBlack,
    onBackground = AnimeCollectionWhite,
    onSurface = AnimeCollectionWhite,
    surface = AnimeCollectionBlack,
    error = AnimeCollectionRed,
    onError = AnimeCollectionWhite,
)

private val LightColorScheme = lightColorScheme(
    primary = AnimeCollectionViolet,
    secondary = AnimeCollectionYellow,
    onPrimary = AnimeCollectionWhite,
    onSecondary = AnimeCollectionBlack,
    background = AnimeCollectionWhite,
    error = AnimeCollectionRed,
    onError = AnimeCollectionWhite,
    surface = AnimeCollectionWhite,
    onSurface = AnimeCollectionBlack,
    onBackground = AnimeCollectionBlack
)

@Composable
fun AnimeCollectionTheme(
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
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}