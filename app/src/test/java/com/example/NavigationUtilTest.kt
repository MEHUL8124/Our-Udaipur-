package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.MewarData
import com.example.model.Place
import com.example.util.NavigationUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class NavigationUtilTest {

  private val context: Context
    get() = ApplicationProvider.getApplicationContext()

  @Test
  fun testCityPalaceNavigationUrl() {
    val cityPalace = MewarData.allPlaces.first { it.id == "city_palace" }
    assertEquals("City Palace, Udaipur", cityPalace.name)
    assertEquals(24.5764, cityPalace.latitude!!, 0.0001)
    assertEquals(73.6835, cityPalace.longitude!!, 0.0001)

    val expectedUrl = "https://www.google.com/maps/dir/?api=1&destination=24.5764,73.6835&travelmode=driving"
    val actualUrl = NavigationUtil.buildDirectionsUrl(cityPalace)
    assertEquals(expectedUrl, actualUrl)

    val navigated = NavigationUtil.navigateToPlace(context, cityPalace)
    assertTrue("Should successfully trigger navigation intent for City Palace", navigated)
  }

  @Test
  fun testLakePicholaNavigationUrl() {
    val lakePichola = MewarData.allPlaces.first { it.id == "lake_pichola" }
    assertEquals("Lake Pichola", lakePichola.name)
    assertEquals(24.5760, lakePichola.latitude!!, 0.0001)
    assertEquals(73.6738, lakePichola.longitude!!, 0.0001)

    val expectedUrl = "https://www.google.com/maps/dir/?api=1&destination=24.576,73.6738&travelmode=driving"
    val actualUrl = NavigationUtil.buildDirectionsUrl(lakePichola)
    assertEquals(expectedUrl, actualUrl)

    val navigated = NavigationUtil.navigateToPlace(context, lakePichola)
    assertTrue("Should successfully trigger navigation intent for Lake Pichola", navigated)
  }

  @Test
  fun testFatehSagarLakeNavigationUrl() {
    val fatehSagar = MewarData.allPlaces.first { it.id == "fateh_sagar" }
    assertEquals("Fateh Sagar Lake", fatehSagar.name)
    assertEquals(24.6033, fatehSagar.latitude!!, 0.0001)
    assertEquals(73.6739, fatehSagar.longitude!!, 0.0001)

    val expectedUrl = "https://www.google.com/maps/dir/?api=1&destination=24.6033,73.6739&travelmode=driving"
    val actualUrl = NavigationUtil.buildDirectionsUrl(fatehSagar)
    assertEquals(expectedUrl, actualUrl)

    val navigated = NavigationUtil.navigateToPlace(context, fatehSagar)
    assertTrue("Should successfully trigger navigation intent for Fateh Sagar Lake", navigated)
  }

  @Test
  fun testMissingCoordinatesHandling() {
    val placeWithoutCoords = Place(
      id = "unknown_place",
      name = "Hidden Chhatri",
      category = "Heritage",
      tag = "Secret",
      rating = 4.5,
      distance = "2 km",
      description = "Mysterious spot without GPS data",
      imageRes = R.drawable.hero_lake_pichola,
      latitude = null,
      longitude = null
    )

    assertNull("Directions URL must be null when coordinates are missing", NavigationUtil.buildDirectionsUrl(placeWithoutCoords))

    val navigated = NavigationUtil.navigateToPlace(context, placeWithoutCoords)
    assertFalse("Navigation must fail safely without crashing when coordinates are null", navigated)

    val latestToast = ShadowToast.getTextOfLatestToast()
    assertNotNull(latestToast)
    assertTrue("Toast should inform user about unavailable coordinates", latestToast.contains("Coordinates not available"))
  }

  @Test
  fun testAllPlacesHaveValidCoordinates() {
    for (place in MewarData.allPlaces) {
      assertNotNull("Place ${place.name} (${place.id}) must have latitude", place.latitude)
      assertNotNull("Place ${place.name} (${place.id}) must have longitude", place.longitude)
      assertTrue("Place ${place.name} latitude must be in Udaipur area (~24°N)", place.latitude!! in 24.0..25.0)
      assertTrue("Place ${place.name} longitude must be in Udaipur area (~73°E)", place.longitude!! in 73.0..74.0)

      val url = NavigationUtil.buildDirectionsUrl(place)
      assertNotNull(url)
      assertTrue(url!!.startsWith("https://www.google.com/maps/dir/?api=1&destination="))
      assertTrue(url.endsWith("&travelmode=driving"))
    }
  }
}
