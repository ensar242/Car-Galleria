package com.example.cargalleria.View.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
object ColorDarkTokens {
    val Primary = Color(0xFF395A7E)
    val OnPrimary = Color(0xFF000000)
    val PrimaryContainer = Color(0xFF23302D)
    val OnPrimaryContainer = Color(0xFFFFFFFF)
    val InversePrimary = Color(0xFF000000)
    val Secondary = Color(0xFFCBE4E1)
    val OnSecondary = Color(0xFF000000)
    val SecondaryContainer = Color(0xFFB9E2E2)
    val OnSecondaryContainer = Color(0xFFFFFFFF)
    val Tertiary = Color(0xFFBB86FC)
    val OnTertiary = Color(0xFF000000)
    val TertiaryContainer = Color(0xFF6200EE)
    val OnTertiaryContainer = Color(0xFFFFFFFF)
    val Background = Color(0xFF121212)
    val OnBackground = Color(0xFFFFFFFF)
    val Surface = Color(0xFF121212)
    val OnSurface = Color(0xFFFFFFFF)
    val SurfaceVariant = Color(0xFF7A98A7)
    val OnSurfaceVariant = Color(0xFFB0BEC5)
    val InverseSurface = Color(0xFFFFFFFF)
    val InverseOnSurface = Color(0xFF000000)
    val Error = Color(0xFFCF6679)
    val OnError = Color(0xFF000000)
    val ErrorContainer = Color(0xFFB00020)
    val OnErrorContainer = Color(0xFFFFFFFF)
    val Outline = Color(0xFFB0BEC5)
    val OutlineVariant = Color(0xFF37474F)
    val Scrim = Color(0xFF000000)
}

object ColorLightTokens {
    val Primary = Color(0xFF6200EE)
    val OnPrimary = Color(0xFFFFFFFF)
    val PrimaryContainer = Color(0xFFF1EFF3)
    val OnPrimaryContainer = Color(0xFF000000)
    val InversePrimary = Color(0xFFBB86FC)
    val Secondary = Color(0xFF03DAC6)
    val OnSecondary = Color(0xFF000000)
    val SecondaryContainer = Color(0xFFE2BDE9)
    val OnSecondaryContainer = Color(0xFFFFFFFF)
    val Tertiary = Color(0xFF6200EE)
    val OnTertiary = Color(0xFFFFFFFF)
    val TertiaryContainer = Color(0xFFBB86FC)
    val OnTertiaryContainer = Color(0xFF000000)
    val Background = Color(0xFFFFFFFF)
    val OnBackground = Color(0xFF000000)
    val Surface = Color(0xFFFFFFFF)
    val OnSurface = Color(0xFF000000)
    val SurfaceVariant = Color(0xFFE0E0E0)
    val OnSurfaceVariant = Color(0xFF000000)
    val InverseSurface = Color(0xFF000000)
    val InverseOnSurface = Color(0xFFFFFFFF)
    val Error = Color(0xFFB00020)
    val OnError = Color(0xFFFFFFFF)
    val ErrorContainer = Color(0xFFCF6679)
    val OnErrorContainer = Color(0xFF000000)
    val Outline = Color(0xFF6200EE)
    val OutlineVariant = Color(0xFFE0E0E0)
    val Scrim = Color(0xFF000000)
}
private val DarkColorScheme = darkColorScheme(
    primary = ColorDarkTokens.Primary,
    onPrimary = ColorDarkTokens.OnPrimary,
    primaryContainer = ColorDarkTokens.PrimaryContainer,
    onPrimaryContainer = ColorDarkTokens.OnPrimaryContainer,
    inversePrimary = ColorDarkTokens.InversePrimary,
    secondary = ColorDarkTokens.Secondary,
    onSecondary = ColorDarkTokens.OnSecondary,
    secondaryContainer = ColorDarkTokens.SecondaryContainer,
    onSecondaryContainer = ColorDarkTokens.OnSecondaryContainer,
    tertiary = ColorDarkTokens.Tertiary,
    onTertiary = ColorDarkTokens.OnTertiary,
    tertiaryContainer = ColorDarkTokens.TertiaryContainer,
    onTertiaryContainer = ColorDarkTokens.OnTertiaryContainer,
    background = ColorDarkTokens.Background,
    onBackground = ColorDarkTokens.OnBackground,
    surface = ColorDarkTokens.Surface,
    onSurface = ColorDarkTokens.OnSurface,
    surfaceVariant = ColorDarkTokens.SurfaceVariant,
    onSurfaceVariant = ColorDarkTokens.OnSurfaceVariant,
    surfaceTint = ColorDarkTokens.Primary,
    inverseSurface = ColorDarkTokens.InverseSurface,
    inverseOnSurface = ColorDarkTokens.InverseOnSurface,
    error = ColorDarkTokens.Error,
    onError = ColorDarkTokens.OnError,
    errorContainer = ColorDarkTokens.ErrorContainer,
    onErrorContainer = ColorDarkTokens.OnErrorContainer,
    outline = ColorDarkTokens.Outline,
    outlineVariant = ColorDarkTokens.OutlineVariant,
    scrim = ColorDarkTokens.Scrim
)

private val LightColorScheme = lightColorScheme(
    primary = ColorLightTokens.Primary,
    onPrimary = ColorLightTokens.OnPrimary,
    primaryContainer = ColorLightTokens.PrimaryContainer,
    onPrimaryContainer = ColorLightTokens.OnPrimaryContainer,
    inversePrimary = ColorLightTokens.InversePrimary,
    secondary = ColorLightTokens.Secondary,
    onSecondary = ColorLightTokens.OnSecondary,
    secondaryContainer = ColorLightTokens.SecondaryContainer,
    onSecondaryContainer = ColorLightTokens.OnSecondaryContainer,
    tertiary = ColorLightTokens.Tertiary,
    onTertiary = ColorLightTokens.OnTertiary,
    tertiaryContainer = ColorLightTokens.TertiaryContainer,
    onTertiaryContainer = ColorLightTokens.OnTertiaryContainer,
    background = ColorLightTokens.Background,
    onBackground = ColorLightTokens.OnBackground,
    surface = ColorLightTokens.Surface,
    onSurface = ColorLightTokens.OnSurface,
    surfaceVariant = ColorLightTokens.SurfaceVariant,
    onSurfaceVariant = ColorLightTokens.OnSurfaceVariant,
    surfaceTint = ColorLightTokens.Primary,
    inverseSurface = ColorLightTokens.InverseSurface,
    inverseOnSurface = ColorLightTokens.InverseOnSurface,
    error = ColorLightTokens.Error,
    onError = ColorLightTokens.OnError,
    errorContainer = ColorLightTokens.ErrorContainer,
    onErrorContainer = ColorLightTokens.OnErrorContainer,
    outline = ColorLightTokens.Outline,
    outlineVariant = ColorLightTokens.OutlineVariant,
    scrim = ColorLightTokens.Scrim
)


@Composable
fun CarGalleriaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}