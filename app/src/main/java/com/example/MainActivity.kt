package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AppThemeMode
import com.example.data.MewarData
import com.example.data.ThemePreferences
import com.example.model.Place
import com.example.ui.screens.ExploreScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.OnboardingScreen
import com.example.ui.screens.PlaceDetailBottomSheet
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.SavedScreen
import com.example.ui.screens.WelcomeScreen
import com.example.ui.theme.MewarNavy
import com.example.ui.theme.MewarNavyContainer
import com.example.ui.theme.MewarOnSurfaceVariant
import com.example.ui.theme.MewarSecondaryFixed
import com.example.ui.theme.MewarSurface
import com.example.ui.theme.MewarSurfaceContainerHigh
import com.example.ui.theme.MewarTerracotta
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

enum class AppScreen {
  WELCOME,
  ONBOARDING,
  MAIN
}

enum class MainTab {
  HOME,
  EXPLORE,
  SAVED,
  PROFILE
}

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val context = LocalContext.current
      val themePreferences = remember { ThemePreferences(context) }
      var currentThemeMode by remember { mutableStateOf(themePreferences.getThemeMode()) }

      val isDarkTheme = when (currentThemeMode) {
        AppThemeMode.SYSTEM -> isSystemInDarkTheme()
        AppThemeMode.LIGHT -> false
        AppThemeMode.DARK -> true
      }

      MyApplicationTheme(darkTheme = isDarkTheme) {
        MewarApp(
          currentThemeMode = currentThemeMode,
          onThemeModeChange = { newMode ->
            currentThemeMode = newMode
            themePreferences.setThemeMode(newMode)
          }
        )
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MewarApp(
  currentThemeMode: AppThemeMode = AppThemeMode.SYSTEM,
  onThemeModeChange: (AppThemeMode) -> Unit = {}
) {
  var currentScreen by remember { mutableStateOf(AppScreen.WELCOME) }
  var selectedTab by remember { mutableStateOf(MainTab.HOME) }
  var selectedPlaceForDetail by remember { mutableStateOf<Place?>(null) }
  var savedPlaceIds by remember {
    mutableStateOf(setOf("city_palace", "bagore_ki_haveli", "sajjangarh"))
  }

  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  val coroutineScope = rememberCoroutineScope()

  BackHandler(enabled = currentScreen != AppScreen.WELCOME) {
    if (selectedPlaceForDetail != null) {
      coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
        selectedPlaceForDetail = null
      }
    } else if (selectedTab != MainTab.HOME) {
      selectedTab = MainTab.HOME
    } else if (currentScreen == AppScreen.MAIN) {
      currentScreen = AppScreen.ONBOARDING
    } else if (currentScreen == AppScreen.ONBOARDING) {
      currentScreen = AppScreen.WELCOME
    }
  }

  Box(modifier = Modifier.fillMaxSize()) {
    AnimatedContent(
      targetState = currentScreen,
      transitionSpec = {
        val direction = if (targetState.ordinal > initialState.ordinal) 1 else -1
        (slideInHorizontally(
          initialOffsetX = { width -> direction * width },
          animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
        ) + fadeIn(
          animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
        )).togetherWith(
          slideOutHorizontally(
            targetOffsetX = { width -> -direction * width },
            animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
          ) + fadeOut(
            animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing)
          )
        )
      },
      label = "app_navigation"
    ) { screen ->
      when (screen) {
        AppScreen.WELCOME -> {
          WelcomeScreen(
            onBegin = { currentScreen = AppScreen.ONBOARDING }
          )
        }

        AppScreen.ONBOARDING -> {
          OnboardingScreen(
            onComplete = {
              selectedTab = MainTab.HOME
              currentScreen = AppScreen.MAIN
            },
            onJumpToMap = {
              selectedTab = MainTab.EXPLORE
              currentScreen = AppScreen.MAIN
            }
          )
        }

        AppScreen.MAIN -> {
          Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MewarSurface,
            bottomBar = {
              NavigationBar(
                containerColor = MewarSurface,
                tonalElevation = 8.dp,
                modifier = Modifier.navigationBarsPadding()
              ) {
                // Home
                NavigationBarItem(
                  selected = selectedTab == MainTab.HOME,
                  onClick = { selectedTab = MainTab.HOME },
                  icon = {
                    Icon(
                      imageVector = if (selectedTab == MainTab.HOME) Icons.Filled.Home else Icons.Outlined.Home,
                      contentDescription = "Home",
                      modifier = Modifier.size(24.dp)
                    )
                  },
                  label = {
                    Text(
                      text = "Home",
                      fontSize = 11.sp,
                      fontWeight = if (selectedTab == MainTab.HOME) FontWeight.Bold else FontWeight.Medium
                    )
                  },
                  colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MewarTerracotta,
                    selectedTextColor = MewarTerracotta,
                    indicatorColor = MewarSecondaryFixed,
                    unselectedIconColor = MewarOnSurfaceVariant,
                    unselectedTextColor = MewarOnSurfaceVariant
                  ),
                  modifier = Modifier.testTag("nav_tab_home")
                )

                // Explore
                NavigationBarItem(
                  selected = selectedTab == MainTab.EXPLORE,
                  onClick = { selectedTab = MainTab.EXPLORE },
                  icon = {
                    Icon(
                      imageVector = if (selectedTab == MainTab.EXPLORE) Icons.Filled.Explore else Icons.Outlined.Explore,
                      contentDescription = "Explore",
                      modifier = Modifier.size(24.dp)
                    )
                  },
                  label = {
                    Text(
                      text = "Explore",
                      fontSize = 11.sp,
                      fontWeight = if (selectedTab == MainTab.EXPLORE) FontWeight.Bold else FontWeight.Medium
                    )
                  },
                  colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MewarTerracotta,
                    selectedTextColor = MewarTerracotta,
                    indicatorColor = MewarSecondaryFixed,
                    unselectedIconColor = MewarOnSurfaceVariant,
                    unselectedTextColor = MewarOnSurfaceVariant
                  ),
                  modifier = Modifier.testTag("nav_tab_explore")
                )

                // Saved
                NavigationBarItem(
                  selected = selectedTab == MainTab.SAVED,
                  onClick = { selectedTab = MainTab.SAVED },
                  icon = {
                    BadgedBox(
                      badge = {
                        if (savedPlaceIds.isNotEmpty()) {
                          Text(
                            text = "${savedPlaceIds.size}",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = MewarTerracotta
                          )
                        }
                      }
                    ) {
                      Icon(
                        imageVector = if (selectedTab == MainTab.SAVED) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                        contentDescription = "Saved",
                        modifier = Modifier.size(24.dp)
                      )
                    }
                  },
                  label = {
                    Text(
                      text = "Saved",
                      fontSize = 11.sp,
                      fontWeight = if (selectedTab == MainTab.SAVED) FontWeight.Bold else FontWeight.Medium
                    )
                  },
                  colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MewarTerracotta,
                    selectedTextColor = MewarTerracotta,
                    indicatorColor = MewarSecondaryFixed,
                    unselectedIconColor = MewarOnSurfaceVariant,
                    unselectedTextColor = MewarOnSurfaceVariant
                  ),
                  modifier = Modifier.testTag("nav_tab_saved")
                )

                // Profile
                NavigationBarItem(
                  selected = selectedTab == MainTab.PROFILE,
                  onClick = { selectedTab = MainTab.PROFILE },
                  icon = {
                    Icon(
                      imageVector = if (selectedTab == MainTab.PROFILE) Icons.Filled.Person else Icons.Outlined.Person,
                      contentDescription = "Profile",
                      modifier = Modifier.size(24.dp)
                    )
                  },
                  label = {
                    Text(
                      text = "Profile",
                      fontSize = 11.sp,
                      fontWeight = if (selectedTab == MainTab.PROFILE) FontWeight.Bold else FontWeight.Medium
                    )
                  },
                  colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MewarTerracotta,
                    selectedTextColor = MewarTerracotta,
                    indicatorColor = MewarSecondaryFixed,
                    unselectedIconColor = MewarOnSurfaceVariant,
                    unselectedTextColor = MewarOnSurfaceVariant
                  ),
                  modifier = Modifier.testTag("nav_tab_profile")
                )
              }
            }
          ) { paddingValues ->
            Box(
              modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
            ) {
              AnimatedContent(
                targetState = selectedTab,
                transitionSpec = {
                  val direction = if (targetState.ordinal > initialState.ordinal) 1 else -1
                  (slideInHorizontally(
                    initialOffsetX = { fullWidth -> direction * fullWidth },
                    animationSpec = tween(durationMillis = 360, easing = FastOutSlowInEasing)
                  ) + fadeIn(
                    animationSpec = tween(durationMillis = 360, easing = FastOutSlowInEasing)
                  )).togetherWith(
                    slideOutHorizontally(
                      targetOffsetX = { fullWidth -> -direction * fullWidth },
                      animationSpec = tween(durationMillis = 360, easing = FastOutSlowInEasing)
                    ) + fadeOut(
                      animationSpec = tween(durationMillis = 280, easing = FastOutSlowInEasing)
                    )
                  )
                },
                label = "tab_navigation",
                modifier = Modifier.fillMaxSize()
              ) { tab ->
                when (tab) {
                  MainTab.HOME -> {
                    HomeScreen(
                      onPlaceClick = { place -> selectedPlaceForDetail = place },
                      onProfileClick = { selectedTab = MainTab.PROFILE },
                      onExploreAll = { selectedTab = MainTab.EXPLORE },
                      savedPlaceIds = savedPlaceIds,
                      onToggleSave = { id ->
                        savedPlaceIds = if (savedPlaceIds.contains(id)) {
                          savedPlaceIds - id
                        } else {
                          savedPlaceIds + id
                        }
                      }
                    )
                  }

                  MainTab.EXPLORE -> {
                    ExploreScreen(
                      onPlaceClick = { place -> selectedPlaceForDetail = place }
                    )
                  }

                  MainTab.SAVED -> {
                    SavedScreen(
                      savedPlaceIds = savedPlaceIds,
                      onPlaceClick = { place -> selectedPlaceForDetail = place },
                      onToggleSave = { id ->
                        savedPlaceIds = if (savedPlaceIds.contains(id)) {
                          savedPlaceIds - id
                        } else {
                          savedPlaceIds + id
                        }
                      }
                    )
                  }

                  MainTab.PROFILE -> {
                    ProfileScreen(
                      currentThemeMode = currentThemeMode,
                      onThemeModeChange = onThemeModeChange,
                      onRevisitWelcome = { currentScreen = AppScreen.WELCOME }
                    )
                  }
                }
              }
            }
          }
        }
      }
    }

    // Place Detail Bottom Sheet
    if (selectedPlaceForDetail != null) {
      val place = selectedPlaceForDetail!!
      PlaceDetailBottomSheet(
        place = place,
        isSaved = savedPlaceIds.contains(place.id),
        onToggleSave = { id ->
          savedPlaceIds = if (savedPlaceIds.contains(id)) {
            savedPlaceIds - id
          } else {
            savedPlaceIds + id
          }
        },
        sheetState = sheetState,
        onDismiss = {
          coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
            selectedPlaceForDetail = null
          }
        }
      )
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  MyApplicationTheme { Greeting("Mewār") }
}
