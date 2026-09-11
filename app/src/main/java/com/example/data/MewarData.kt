package com.example.data

import com.example.R
import com.example.model.AudioTrack
import com.example.model.ItineraryCategory
import com.example.model.OnboardingStep
import com.example.model.Place

object MewarData {

  val onboardingSteps = listOf(
    OnboardingStep(
      stepNumber = "01 / 03",
      stepLabel = "Curated Exploration",
      title = "Discover iconic palaces & serene lakes",
      description = "Experience Udaipur beyond typical tourist tracks — from majestic royal havelis to quiet morning ghats along Lake Pichola.",
      locationPill = "Iconic Heritage • Lake Pichola",
      moodPill = "Best visited at golden dawn",
      nextHint = "Next: Hidden Gems & Sunset Cafés",
      imageRes = R.drawable.city_palace_golden
    ),
    OnboardingStep(
      stepNumber = "02 / 03",
      stepLabel = "Architectural Heritage",
      title = "Wander centuries-old step alleys & havelis",
      description = "Stroll down winding whitewashed stone alleys, discovering hand-carved jharokhas, brass antique guilds, and quiet historic temples.",
      locationPill = "Secret Passages • Old City",
      moodPill = "Best visited at 4:30 PM",
      nextHint = "Next: Lakeside Cafés & Dining",
      imageRes = R.drawable.haveli_alleyway
    ),
    OnboardingStep(
      stepNumber = "03 / 03",
      stepLabel = "Culinary & Rooftops",
      title = "Sunset rooftop cafés & Mewari dining",
      description = "Unwind at twilight atop lantern-lit terraces, sipping saffron kulhad tea while listening to the gentle waters of Lake Pichola.",
      locationPill = "Gastronomy • Pichola Rooftops",
      moodPill = "Reserve before twilight",
      nextHint = "Start Exploring",
      imageRes = R.drawable.sunset_cafe
    )
  )

  val quickFilters = listOf(
    "All spots",
    "Sunset spots",
    "Quiet cafés",
    "Lake views",
    "Heritage walks",
    "Royal dining"
  )

  val categories = listOf(
    ItineraryCategory(id = "heritage", name = "Heritage", icon = "castle", count = 12),
    ItineraryCategory(id = "lakes", name = "Lakes", icon = "water", count = null),
    ItineraryCategory(id = "food", name = "Mewari Food", icon = "restaurant", count = null),
    ItineraryCategory(id = "sunset", name = "Sunset Points", icon = "wb_twilight", count = null)
  )

  val featuredExpedition = Place(
    id = "city_palace",
    name = "City Palace, Udaipur",
    category = "Heritage",
    tag = "Heritage",
    rating = 4.9,
    reviewCount = "2.4k",
    distance = "1.2 km away",
    subtitle = "Royal Mewar Residence",
    description = "450 years of Mewar architecture, intricate peacock mosaics, and serene jharokhas overlooking Lake Pichola. A sprawling granite and marble complex founded in 1559 by Maharana Udai Singh II.",
    imageRes = R.drawable.hero_lake_pichola,
    timing = "9:30 AM – 5:30 PM",
    audioGuideIncluded = true,
    audioTracks = listOf(
      AudioTrack("Mor Chowk: The Peacock Courtyard", "3:45", "Discover 5,000 glass mosaic tiles depicting Rajasthan's monsoon peacocks."),
      AudioTrack("Sheesh Mahal: Palace of Mirrors", "4:12", "Step into the private twilight sanctuary of mirrors and stained Belgian glass."),
      AudioTrack("Zenana Mahal & Jharokhas", "3:20", "Echoes of royal queens and private stone balconies overlooking Lake Pichola.")
    ),
    insiderTip = "Arrive around 4:00 PM to catch both interior museum halls and the dramatic golden glow setting over Pichola.",
    coordinates = "24.5764° N, 73.6835° E",
    latitude = 24.5764,
    longitude = 73.6835,
    isFeatured = true
  )

  val popularNearYou = listOf(
    Place(
      id = "lake_pichola",
      name = "Lake Pichola",
      category = "Lakes",
      tag = "Lakes",
      rating = 4.9,
      reviewCount = "3.2k",
      distance = "400m away",
      subtitle = "Sunset Boat Cruise",
      description = "Historic freshwater lake created in 1362 AD, famous for its picturesque islands like Jag Niwas (Lake Palace) and Jag Mandir, framed by the majestic Aravalli Hills.",
      imageRes = R.drawable.hero_lake_pichola,
      timing = "Boat rides: 9:00 AM – 6:00 PM",
      audioGuideIncluded = true,
      audioTracks = listOf(
        AudioTrack("Tales of Jag Mandir & Island Palaces", "3:15", "The island haven that inspired Shah Jahan's Taj Mahal architecture."),
        AudioTrack("The Royal Ghats at Dusk", "2:45", "Legends of Pichhu Banjara and the eternal lake reflections.")
      ),
      insiderTip = "Board the sunset cruise from Rameshwar Ghat for golden hour views of the City Palace facade.",
      coordinates = "24.5760° N, 73.6738° E",
      latitude = 24.5760,
      longitude = 73.6738
    ),
    Place(
      id = "bagore_ki_haveli",
      name = "Bagore Ki Haveli",
      category = "Heritage",
      tag = "Heritage",
      rating = 4.8,
      reviewCount = "1.8k",
      distance = "850m away",
      subtitle = "Evening Show",
      description = "Cultural Dharohar dance & sunset by the waterfront. Built in the late 18th century on the waterfront of Lake Pichola at Gangaur Ghat with over 100 rooms.",
      imageRes = R.drawable.bagore_ki_haveli,
      timing = "10:00 AM – 8:00 PM (Dharohar 7 PM)",
      audioGuideIncluded = true,
      audioTracks = listOf(
        AudioTrack("Waterfront Courtyard Secrets", "2:50", "History of Amar Chand Badwa and royal haveli architecture."),
        AudioTrack("Dharohar Folk Traditions", "3:30", "The famous Chari and Bhavai balance pot dances of Rajasthan.")
      ),
      insiderTip = "Tickets for the 7:00 PM cultural performance sell out fast; queue by 6:00 PM at Gangaur Ghat.",
      coordinates = "24.5797° N, 73.6803° E",
      latitude = 24.5797,
      longitude = 73.6803
    ),
    Place(
      id = "jagdish_temple",
      name = "Jagdish Temple",
      category = "Sacred",
      tag = "Sacred",
      rating = 4.9,
      reviewCount = "1.4k",
      distance = "1.1 km away",
      subtitle = "Morning Aarti",
      description = "Ornate Indo-Aryan architectural marvel completed in 1651 by Maharana Jagat Singh I. Features towering carved shikharas, sculpted elephants, and continuous devotional singing.",
      imageRes = R.drawable.jagdish_temple,
      timing = "4:15 AM – 1:00 PM, 5:00 PM – 10:30 PM",
      audioGuideIncluded = true,
      audioTracks = listOf(
        AudioTrack("Garuda Shrine & Stone Carvings", "3:10", "Symbolism of celestial dancers and sacred Hindu mythologies.")
      ),
      insiderTip = "Visit at 5:30 AM during morning Mangala Aarti for an unforgettable spiritual chorus.",
      coordinates = "24.5793° N, 73.6841° E",
      latitude = 24.5793,
      longitude = 73.6841
    ),
    Place(
      id = "gangaur_ghat",
      name = "Gangaur Ghat",
      category = "Lakes",
      tag = "Ghats",
      rating = 4.7,
      reviewCount = "920",
      distance = "600m away",
      subtitle = "Sunset gathering",
      description = "The prime ghat on the eastern shore of Lake Pichola where ancient royal processions and traditional Mewar festivals converge against tranquil water reflections.",
      imageRes = R.drawable.haveli_alleyway,
      timing = "Open 24 hours",
      insiderTip = "Sit on the stone steps between 5:30 and 6:15 PM as pigeons flock and lakeside lamps turn golden.",
      coordinates = "24.5801° N, 73.6806° E",
      latitude = 24.5801,
      longitude = 73.6806
    )
  )

  val trendingDigest = listOf(
    Place(
      id = "sajjangarh",
      name = "Sajjangarh (Monsoon Palace)",
      category = "Sunset spots",
      tag = "Viewpoint",
      badge = "BEST SUNSET IN RAJASTHAN",
      rating = 4.8,
      distance = "45 min drive",
      meta = "Peak Season",
      description = "Panoramic 360° high altitude palace atop Bansdara Peak (944m). Built to watch monsoon clouds, offering celestial panoramic sunsets across lakes and mountains.",
      imageRes = R.drawable.sajjangarh_monsoon,
      timing = "9:00 AM – 6:00 PM",
      audioGuideIncluded = true,
      audioTracks = listOf(
        AudioTrack("Astronomical Ambition of Sajjan Singh", "3:40", "The royal palace designed to predict Monsoon rains.")
      ),
      insiderTip = "Arrive 1 hour prior to sunset to secure the west terrace viewing ledge before crowds peak.",
      coordinates = "24.5908° N, 73.6375° E",
      latitude = 24.5908,
      longitude = 73.6375
    ),
    Place(
      id = "fateh_sagar",
      name = "Fateh Sagar Lake",
      category = "Lakes",
      tag = "Lakeside",
      badge = "LAKESIDE WALKING TRAIL",
      rating = 4.7,
      distance = "Easy trail",
      meta = "Kulhad Coffee",
      description = "Scenic lakeside walking promenade with fresh mountain breeze, solar observatory island, and evening promenade stalls serving piping hot kulhad coffee.",
      imageRes = R.drawable.fateh_sagar,
      timing = "Open 24 hours",
      insiderTip = "Walk the Marine Drive promenade at sunset, then take the cable car ropeway up to Neemach Mata Temple.",
      coordinates = "24.6033° N, 73.6739° E",
      latitude = 24.6033,
      longitude = 73.6739
    ),
    Place(
      id = "badi_lake",
      name = "Badi Lake (Bahubali Hills)",
      category = "Sunset spots",
      tag = "Nature Hike",
      badge = "SECRET SUNRISE HIKE",
      rating = 4.9,
      distance = "25 min drive",
      meta = "Best at 6:15 AM • Wild Crocodile Spot",
      description = "Untouched serene freshwater lake nestled in the Aravallis. A short 15-minute trail up Bahubali Peak rewards you with stunning cliffside vistas over islands.",
      imageRes = R.drawable.badi_lake,
      timing = "Best at sunrise (6:00 AM – 8:30 AM)",
      insiderTip = "Pack drinking water and wear trail shoes; morning light gives the lake an emerald mirror shimmer.",
      coordinates = "24.6158° N, 73.6268° E",
      latitude = 24.6158,
      longitude = 73.6268
    )
  )

  val allPlaces = listOf(featuredExpedition) + popularNearYou + trendingDigest

  val insiderHint = "Take the 5:00 PM heritage jetty boat from Lal Ghat for the most surreal sunset reflections."
}
