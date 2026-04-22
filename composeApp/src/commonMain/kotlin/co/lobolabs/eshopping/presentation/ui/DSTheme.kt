package co.lobolabs.eshopping.presentation.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = DSColor.blackColor,
    onPrimary = DSColor.whiteColor,
    secondary = DSColor.blackColor,
    onSecondary = DSColor.whiteColor,
    background = DSColor.whiteColor,
    onBackground = DSColor.blackColor,
    surface = DSColor.whiteColor,
    onSurface = DSColor.blackColor,
    onSurfaceVariant = DSColor.whiteColor,
    tertiary = DSColor.blackColor,
    error = DSColor.appErrorColor
)

private val DarkColorScheme = darkColorScheme(
    primary = DSColor.whiteColor,
    onPrimary = DSColor.appGreenDarkColor,
    secondary = DSColor.Green,
    onSecondary = DSColor.whiteColor,
    background = DSColor.appGreenDarkColor,
    onBackground = DSColor.whiteColor,
    surface = DSColor.appGrayDarkestColor,
    onSurface = DSColor.appGrayDarkColor,
    onSurfaceVariant = DSColor.appGrayOnDarkestColor,
    tertiary = DSColor.appGreenLighterColor,
    error = DSColor.appErrorColor
)

@Composable
fun EShoppingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        // TODO typography = Typography, // Defina sua tipografia com a fonte 'nexa' aqui
        content = content
    )
}