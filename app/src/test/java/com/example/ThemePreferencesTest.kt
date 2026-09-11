package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.AppThemeMode
import com.example.data.ThemePreferences
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ThemePreferencesTest {

  private lateinit var context: Context
  private lateinit var preferences: ThemePreferences

  @Before
  fun setup() {
    context = ApplicationProvider.getApplicationContext()
    val sharedPrefs = context.getSharedPreferences("mewar_theme_prefs", Context.MODE_PRIVATE)
    sharedPrefs.edit().clear().commit()
    preferences = ThemePreferences(context)
  }

  @Test
  fun testDefaultThemeIsSystem() {
    assertEquals(AppThemeMode.SYSTEM, preferences.getThemeMode())
  }

  @Test
  fun testSetAndGetDarkMode() {
    preferences.setThemeMode(AppThemeMode.DARK)
    assertEquals(AppThemeMode.DARK, preferences.getThemeMode())
  }

  @Test
  fun testSetAndGetLightMode() {
    preferences.setThemeMode(AppThemeMode.LIGHT)
    assertEquals(AppThemeMode.LIGHT, preferences.getThemeMode())
  }

  @Test
  fun testThemePersistenceAcrossInstances() {
    preferences.setThemeMode(AppThemeMode.DARK)
    
    // Create fresh instance reading from SharedPreferences
    val newPrefsInstance = ThemePreferences(context)
    assertEquals(AppThemeMode.DARK, newPrefsInstance.getThemeMode())
  }

  @Test
  fun testAppThemeModeEnumValues() {
    assertEquals("system", AppThemeMode.SYSTEM.storageKey)
    assertEquals("light", AppThemeMode.LIGHT.storageKey)
    assertEquals("dark", AppThemeMode.DARK.storageKey)
  }
}
