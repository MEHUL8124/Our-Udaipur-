package com.example.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// --- Static Raw Palette Definitions ---

// Light Palette (Palace Marble & Warm Terracotta)
val LightMewarNavy = Color(0xFF031322)
val LightMewarNavyContainer = Color(0xFF182838)
val LightMewarOnPrimary = Color(0xFFFFFFFF)
val LightMewarPrimaryFixed = Color(0xFFD3E4FA)
val LightMewarPrimaryFixedDim = Color(0xFFB8C8DD)

val LightMewarTerracotta = Color(0xFF845326)
val LightMewarTerracottaContainer = Color(0xFFFEBC85)
val LightMewarSecondaryFixed = Color(0xFFFFDCC2)
val LightMewarSecondaryFixedDim = Color(0xFFFAB983)

val LightMewarSurface = Color(0xFFFFF8F5)
val LightMewarSurfaceBright = Color(0xFFFFF8F5)
val LightMewarSurfaceContainer = Color(0xFFF6ECE6)
val LightMewarSurfaceContainerLow = Color(0xFFFCF2EB)
val LightMewarSurfaceContainerHigh = Color(0xFFF0E6E0)
val LightMewarSurfaceContainerHighest = Color(0xFFEAE1DA)
val LightMewarSurfaceLowest = Color(0xFFFFFFFF)

val LightMewarOnSurface = Color(0xFF1F1B17)
val LightMewarOnSurfaceVariant = Color(0xFF44474C)
val LightMewarOutline = Color(0xFF74777D)
val LightMewarOutlineVariant = Color(0xFFC4C6CC)
val LightMewarButtonContainer = Color(0xFF182838)

// Dark Palette (Lake Pichola Midnight Navy & Warm Sandstone Glow)
val DarkMewarNavy = Color(0xFFE2EDF9)
val DarkMewarNavyContainer = Color(0xFF1E3146)
val DarkMewarOnPrimary = Color(0xFF07121C)
val DarkMewarPrimaryFixed = Color(0xFFD3E4FA)
val DarkMewarPrimaryFixedDim = Color(0xFF9CB1C9)

val DarkMewarTerracotta = Color(0xFFFEBC85) // Luminous warm terracotta for dark surfaces
val DarkMewarTerracottaContainer = Color(0xFF422815)
val DarkMewarSecondaryFixed = Color(0xFF382314)
val DarkMewarSecondaryFixedDim = Color(0xFFFFB679)

val DarkMewarSurface = Color(0xFF0A1118) // Deep midnight navy canvas
val DarkMewarSurfaceBright = Color(0xFF121B24)
val DarkMewarSurfaceContainer = Color(0xFF15202D)
val DarkMewarSurfaceContainerLow = Color(0xFF111A24) // Card surface
val DarkMewarSurfaceContainerHigh = Color(0xFF1D2B3B) // Elevated card surface
val DarkMewarSurfaceContainerHighest = Color(0xFF26374A) // Chips / borders
val DarkMewarSurfaceLowest = Color(0xFF060B10)

val DarkMewarOnSurface = Color(0xFFF0F4F8) // Clean soft off-white text
val DarkMewarOnSurfaceVariant = Color(0xFF9AAEC0) // Soft slate secondary text
val DarkMewarOutline = Color(0xFF3B4E63)
val DarkMewarOutlineVariant = Color(0xFF233243)
val DarkMewarButtonContainer = Color(0xFF243B53)

val GoldenAmber = Color(0xFFFFB300)
val HeritageRed = Color(0xFF9E2A2B)
val TealWater = Color(0xFF006D77)

data class MewarColors(
  val isDark: Boolean,
  val navy: Color,
  val navyContainer: Color,
  val onPrimary: Color,
  val primaryFixed: Color,
  val primaryFixedDim: Color,
  val terracotta: Color,
  val terracottaContainer: Color,
  val secondaryFixed: Color,
  val secondaryFixedDim: Color,
  val surface: Color,
  val surfaceBright: Color,
  val surfaceContainer: Color,
  val surfaceContainerLow: Color,
  val surfaceContainerHigh: Color,
  val surfaceContainerHighest: Color,
  val surfaceLowest: Color,
  val onSurface: Color,
  val onSurfaceVariant: Color,
  val outline: Color,
  val outlineVariant: Color,
  val buttonContainer: Color
)

val LightColors = MewarColors(
  isDark = false,
  navy = LightMewarNavy,
  navyContainer = LightMewarNavyContainer,
  onPrimary = LightMewarOnPrimary,
  primaryFixed = LightMewarPrimaryFixed,
  primaryFixedDim = LightMewarPrimaryFixedDim,
  terracotta = LightMewarTerracotta,
  terracottaContainer = LightMewarTerracottaContainer,
  secondaryFixed = LightMewarSecondaryFixed,
  secondaryFixedDim = LightMewarSecondaryFixedDim,
  surface = LightMewarSurface,
  surfaceBright = LightMewarSurfaceBright,
  surfaceContainer = LightMewarSurfaceContainer,
  surfaceContainerLow = LightMewarSurfaceContainerLow,
  surfaceContainerHigh = LightMewarSurfaceContainerHigh,
  surfaceContainerHighest = LightMewarSurfaceContainerHighest,
  surfaceLowest = LightMewarSurfaceLowest,
  onSurface = LightMewarOnSurface,
  onSurfaceVariant = LightMewarOnSurfaceVariant,
  outline = LightMewarOutline,
  outlineVariant = LightMewarOutlineVariant,
  buttonContainer = LightMewarButtonContainer
)

val DarkColors = MewarColors(
  isDark = true,
  navy = DarkMewarNavy,
  navyContainer = DarkMewarNavyContainer,
  onPrimary = DarkMewarOnPrimary,
  primaryFixed = DarkMewarPrimaryFixed,
  primaryFixedDim = DarkMewarPrimaryFixedDim,
  terracotta = DarkMewarTerracotta,
  terracottaContainer = DarkMewarTerracottaContainer,
  secondaryFixed = DarkMewarSecondaryFixed,
  secondaryFixedDim = DarkMewarSecondaryFixedDim,
  surface = DarkMewarSurface,
  surfaceBright = DarkMewarSurfaceBright,
  surfaceContainer = DarkMewarSurfaceContainer,
  surfaceContainerLow = DarkMewarSurfaceContainerLow,
  surfaceContainerHigh = DarkMewarSurfaceContainerHigh,
  surfaceContainerHighest = DarkMewarSurfaceContainerHighest,
  surfaceLowest = DarkMewarSurfaceLowest,
  onSurface = DarkMewarOnSurface,
  onSurfaceVariant = DarkMewarOnSurfaceVariant,
  outline = DarkMewarOutline,
  outlineVariant = DarkMewarOutlineVariant,
  buttonContainer = DarkMewarButtonContainer
)

val LocalMewarColors = staticCompositionLocalOf { LightColors }

// Dynamic Composable accessors for seamless theme adaptation across existing UI
val MewarNavy: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.navy

val MewarNavyContainer: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.navyContainer

val MewarOnPrimary: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.onPrimary

val MewarPrimaryFixed: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.primaryFixed

val MewarPrimaryFixedDim: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.primaryFixedDim

val MewarTerracotta: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.terracotta

val MewarTerracottaContainer: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.terracottaContainer

val MewarSecondaryFixed: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.secondaryFixed

val MewarSecondaryFixedDim: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.secondaryFixedDim

val MewarSurface: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.surface

val MewarSurfaceBright: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.surfaceBright

val MewarSurfaceContainer: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.surfaceContainer

val MewarSurfaceContainerLow: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.surfaceContainerLow

val MewarSurfaceContainerHigh: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.surfaceContainerHigh

val MewarSurfaceContainerHighest: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.surfaceContainerHighest

val MewarSurfaceLowest: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.surfaceLowest

val MewarOnSurface: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.onSurface

val MewarOnSurfaceVariant: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.onSurfaceVariant

val MewarOutline: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.outline

val MewarOutlineVariant: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.outlineVariant

val MewarButtonContainer: Color
  @Composable
  @ReadOnlyComposable
  get() = LocalMewarColors.current.buttonContainer

