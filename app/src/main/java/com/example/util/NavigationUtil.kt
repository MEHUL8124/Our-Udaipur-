package com.example.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.model.Place

object NavigationUtil {

  /**
   * Constructs a Google Maps directions URL with driving travel mode.
   * Format: https://www.google.com/maps/dir/?api=1&destination=LATITUDE,LONGITUDE&travelmode=driving
   * Returns null if coordinates are missing or invalid.
   */
  fun buildDirectionsUrl(place: Place): String? {
    val lat = place.latitude
    val lng = place.longitude
    return if (lat != null && lng != null) {
      "https://www.google.com/maps/dir/?api=1&destination=$lat,$lng&travelmode=driving"
    } else {
      null
    }
  }

  /**
   * Validates place coordinates and launches Google Maps navigation.
   * If Google Maps is installed, opens the Google Maps app with the selected place as destination.
   * If not installed, falls back gracefully to opening the directions URL in the browser.
   * Displays a toast error if coordinates are missing.
   */
  fun navigateToPlace(context: Context, place: Place): Boolean {
    val mapsUrl = buildDirectionsUrl(place)

    if (mapsUrl == null) {
      Toast.makeText(
        context,
        "Coordinates not available for ${place.name}",
        Toast.LENGTH_SHORT
      ).show()
      return false
    }

    val uri = Uri.parse(mapsUrl)

    // Attempt to open in Google Maps app first
    val mapsAppIntent = Intent(Intent.ACTION_VIEW, uri).apply {
      setPackage("com.google.android.apps.maps")
      if (context !is android.app.Activity) {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      }
    }

    return try {
      context.startActivity(mapsAppIntent)
      true
    } catch (e: ActivityNotFoundException) {
      // Fallback: Google Maps is not installed, open via web browser
      val browserIntent = Intent(Intent.ACTION_VIEW, uri).apply {
        if (context !is android.app.Activity) {
          addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
      }
      try {
        context.startActivity(browserIntent)
        true
      } catch (e2: Exception) {
        Toast.makeText(
          context,
          "Unable to open navigation in browser",
          Toast.LENGTH_SHORT
        ).show()
        false
      }
    }
  }
}
