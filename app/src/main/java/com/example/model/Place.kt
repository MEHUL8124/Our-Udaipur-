package com.example.model

import androidx.annotation.DrawableRes

data class Place(
  val id: String,
  val name: String,
  val category: String,
  val tag: String,
  val rating: Double,
  val reviewCount: String = "",
  val distance: String,
  val subtitle: String = "",
  val description: String,
  val badge: String = "",
  val meta: String = "",
  @DrawableRes val imageRes: Int,
  val timing: String = "",
  val audioGuideIncluded: Boolean = false,
  val audioTracks: List<AudioTrack> = emptyList(),
  val insiderTip: String = "",
  val coordinates: String = "",
  val latitude: Double? = null,
  val longitude: Double? = null,
  val isFeatured: Boolean = false
) {
  fun hasCoordinates(): Boolean = latitude != null && longitude != null
}

data class AudioTrack(
  val title: String,
  val duration: String,
  val description: String
)

data class ItineraryCategory(
  val id: String,
  val name: String,
  val icon: String,
  val count: Int? = null
)

data class OnboardingStep(
  val stepNumber: String,
  val stepLabel: String,
  val title: String,
  val description: String,
  val locationPill: String,
  val moodPill: String,
  val nextHint: String,
  @DrawableRes val imageRes: Int
)
