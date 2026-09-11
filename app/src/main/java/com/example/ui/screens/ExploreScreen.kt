package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.MewarData
import com.example.model.Place
import com.example.ui.theme.GoldenAmber
import com.example.ui.theme.MewarNavy
import com.example.ui.theme.MewarNavyContainer
import com.example.ui.theme.MewarOnSurface
import com.example.ui.theme.MewarOnSurfaceVariant
import com.example.ui.theme.MewarSecondaryFixed
import com.example.ui.theme.MewarSurface
import com.example.ui.theme.MewarSurfaceContainerHigh
import com.example.ui.theme.MewarSurfaceContainerLow
import com.example.ui.theme.MewarTerracotta
import com.example.ui.theme.TealWater

@Composable
fun ExploreScreen(
  onPlaceClick: (Place) -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedCategory by remember { mutableStateOf("All") }
  val categories = listOf("All", "Heritage", "Sacred", "Lakes", "Sunset spots")

  val places = MewarData.allPlaces.filter {
    selectedCategory == "All" || it.category == selectedCategory
  }

  var highlightedPlaceId by remember { mutableStateOf<String?>("city_palace") }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MewarSurface),
    contentPadding = PaddingValues(bottom = 96.dp)
  ) {
    // Top Bar
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 12.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "Mewār Map & Directory",
              fontSize = 22.sp,
              fontWeight = FontWeight.Bold,
              color = MewarOnSurface
            )
            Text(
              text = "Explore landmarks, ghats & mountain viewpoints",
              fontSize = 12.sp,
              color = MewarOnSurfaceVariant
            )
          }

          Surface(
            shape = CircleShape,
            color = MewarSecondaryFixed,
            modifier = Modifier.size(38.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = Icons.Default.Map,
                contentDescription = null,
                tint = MewarTerracotta,
                modifier = Modifier.size(20.dp)
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Category Filter Row
        LazyRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          items(categories) { cat ->
            val isSelected = cat == selectedCategory
            Surface(
              shape = CircleShape,
              color = if (isSelected) MewarTerracotta else MewarSurfaceContainerHigh,
              modifier = Modifier.clickable { selectedCategory = cat }
            ) {
              Text(
                text = cat,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) Color.White else MewarOnSurface,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
              )
            }
          }
        }
      }
    }

    // Stylized Map Card
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 8.dp)
      ) {
        Surface(
          shape = RoundedCornerShape(22.dp),
          color = Color(0xFFE8EEF5),
          shadowElevation = 4.dp,
          modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
        ) {
          Box(modifier = Modifier.fillMaxSize()) {
            // Map graphics drawing
            Canvas(modifier = Modifier.fillMaxSize()) {
              val w = size.width
              val h = size.height

              // Lake Pichola (tranquil blue body)
              val lakePath = Path().apply {
                moveTo(w * 0.25f, h * 0.15f)
                cubicTo(w * 0.45f, h * 0.2f, w * 0.55f, h * 0.5f, w * 0.48f, h * 0.85f)
                cubicTo(w * 0.35f, h * 0.95f, w * 0.18f, h * 0.75f, w * 0.15f, h * 0.45f)
                close()
              }
              drawPath(lakePath, color = Color(0xFFC7DBEC))

              // Fateh Sagar Lake (top right)
              drawRoundRect(
                color = Color(0xFFC7DBEC),
                topLeft = Offset(w * 0.65f, h * 0.1f),
                size = Size(w * 0.3f, h * 0.45f),
                cornerRadius = CornerRadius(40f, 40f)
              )

              // Old City Roads
              drawLine(
                color = Color(0xFFD3D8DE),
                start = Offset(w * 0.48f, h * 0.2f),
                end = Offset(w * 0.85f, h * 0.7f),
                strokeWidth = 6f
              )
              drawLine(
                color = Color(0xFFD3D8DE),
                start = Offset(w * 0.15f, h * 0.7f),
                end = Offset(w * 0.9f, h * 0.55f),
                strokeWidth = 5f
              )
            }

            // Map Overlay Badges / Pins
            // Pin 1: City Palace
            Box(
              modifier = Modifier
                .align(Alignment.Center)
                .offset(x = (-30).dp, y = (-10).dp)
                .clickable {
                  highlightedPlaceId = "city_palace"
                  onPlaceClick(MewarData.featuredExpedition)
                }
            ) {
              Surface(
                shape = CircleShape,
                color = MewarTerracotta,
                shadowElevation = 6.dp,
                modifier = Modifier.size(32.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "City Palace",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                  )
                }
              }
            }

            // Pin 2: Bagore Ki Haveli
            Box(
              modifier = Modifier
                .align(Alignment.Center)
                .offset(x = (-50).dp, y = (-40).dp)
                .clickable {
                  highlightedPlaceId = "bagore_ki_haveli"
                  onPlaceClick(MewarData.popularNearYou[0])
                }
            ) {
              Surface(
                shape = CircleShape,
                color = MewarNavyContainer,
                shadowElevation = 4.dp,
                modifier = Modifier.size(28.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Bagore Ki Haveli",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                  )
                }
              }
            }

            // Pin 3: Monsoon Palace
            Box(
              modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-20).dp, y = (24).dp)
                .clickable {
                  highlightedPlaceId = "sajjangarh"
                  onPlaceClick(MewarData.trendingDigest[0])
                }
            ) {
              Surface(
                shape = CircleShape,
                color = MewarNavyContainer,
                shadowElevation = 4.dp,
                modifier = Modifier.size(28.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Icon(
                    imageVector = Icons.Default.Navigation,
                    contentDescription = "Sajjangarh",
                    tint = GoldenAmber,
                    modifier = Modifier.size(16.dp)
                  )
                }
              }
            }

            // Bottom Map Label
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = MewarSurface.copy(alpha = 0.92f),
              modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
              ) {
                Box(
                  modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF2B9348))
                )
                Text(
                  text = "Live Mewar Map • Tap pins to view",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = MewarOnSurface
                )
              }
            }
          }
        }
      }
    }

    // List of Places
    items(places) { place ->
      Surface(
        shape = RoundedCornerShape(18.dp),
        color = MewarSurfaceContainerLow,
        shadowElevation = 1.dp,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 6.dp)
          .clickable { onPlaceClick(place) }
          .testTag("explore_item_${place.id}")
      ) {
        Row(
          modifier = Modifier.padding(12.dp),
          horizontalArrangement = Arrangement.spacedBy(14.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Image(
            painter = painterResource(id = place.imageRes),
            contentDescription = place.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
              .size(76.dp)
              .clip(RoundedCornerShape(14.dp))
          )

          Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                shape = CircleShape,
                color = MewarSurfaceContainerHigh
              ) {
                Text(
                  text = place.tag,
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  color = MewarTerracotta,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
              }

              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Star,
                  contentDescription = null,
                  tint = GoldenAmber,
                  modifier = Modifier.size(13.dp)
                )
                Text(
                  text = "${place.rating}",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = MewarOnSurface
                )
              }
            }

            Text(
              text = place.name,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold,
              color = MewarOnSurface,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )

            Text(
              text = place.description,
              fontSize = 12.sp,
              color = MewarOnSurfaceVariant,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )

            Row(
              horizontalArrangement = Arrangement.spacedBy(10.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "📍 ${place.distance}",
                fontSize = 11.sp,
                color = MewarOnSurfaceVariant
              )
              if (place.audioGuideIncluded) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.Headphones,
                    contentDescription = null,
                    tint = MewarTerracotta,
                    modifier = Modifier.size(12.dp)
                  )
                  Text(
                    text = "Audio Guide",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MewarTerracotta
                  )
                }
              }
            }
          }
        }
      }
    }
  }
}
