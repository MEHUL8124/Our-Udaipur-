package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val MewarLightColorScheme = lightColorScheme(
  primary = LightMewarNavy,
  onPrimary = LightMewarOnPrimary,
  primaryContainer = LightMewarNavyContainer,
  onPrimaryContainer = LightMewarPrimaryFixed,
  secondary = LightMewarTerracotta,
  onSecondary = Color.White,
  secondaryContainer = LightMewarTerracottaContainer,
  onSecondaryContainer = LightMewarNavy,
  background = LightMewarSurface,
  onBackground = LightMewarOnSurface,
  surface = LightMewarSurface,
  onSurface = LightMewarOnSurface,
  surfaceVariant = LightMewarSurfaceContainerHighest,
  onSurfaceVariant = LightMewarOnSurfaceVariant,
  outline = LightMewarOutline,
  outlineVariant = LightMewarOutlineVariant
)

private val MewarDarkColorScheme = darkColorScheme(
  primary = DarkMewarTerracotta,
  onPrimary = DarkMewarSurface,
  primaryContainer = DarkMewarNavyContainer,
  onPrimaryContainer = DarkMewarOnSurface,
  secondary = DarkMewarSecondaryFixedDim,
  onSecondary = DarkMewarSurface,
  secondaryContainer = DarkMewarTerracottaContainer,
  onSecondaryContainer = DarkMewarSecondaryFixedDim,
  background = DarkMewarSurface,
  onBackground = DarkMewarOnSurface,
  surface = DarkMewarSurface,
  onSurface = DarkMewarOnSurface,
  surfaceVariant = DarkMewarSurfaceContainerHighest,
  onSurfaceVariant = DarkMewarOnSurfaceVariant,
  outline = DarkMewarOutline,
  outlineVariant = DarkMewarOutlineVariant
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) MewarDarkColorScheme else MewarLightColorScheme
  val mewarColors = if (darkTheme) DarkColors else LightColors

  CompositionLocalProvider(
    LocalMewarColors provides mewarColors
  ) {
    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
  }
}

