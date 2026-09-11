package com.example.data

import android.content.Context
import android.content.SharedPreferences

enum class AppThemeMode(
  val storageKey: String,
  val displayName: String,
  val description: String
) {
  SYSTEM("system", "System", "Matches device setting"),
  LIGHT("light", "Light", "Daylight palace marble"),
  DARK("dark", "Dark", "Midnight Pichola waters");

  companion object {
    fun fromKey(key: String?): AppThemeMode {
      return entries.find { it.storageKey == key } ?: SYSTEM
    }
  }
}

class ThemePreferences(context: Context) {
  private val prefs: SharedPreferences =
    context.getSharedPreferences("mewar_theme_prefs", Context.MODE_PRIVATE)

  fun getThemeMode(): AppThemeMode {
    val key = prefs.getString(KEY_THEME_MODE, AppThemeMode.SYSTEM.storageKey)
    return AppThemeMode.fromKey(key)
  }

  fun setThemeMode(mode: AppThemeMode) {
    prefs.edit().putString(KEY_THEME_MODE, mode.storageKey).apply()
  }

  companion object {
    private const val KEY_THEME_MODE = "pref_theme_mode"
  }
}
