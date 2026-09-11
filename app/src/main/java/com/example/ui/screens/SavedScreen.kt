package com.example.ui.screens

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.ui.theme.MewarNavyContainer
import com.example.ui.theme.MewarOnSurface
import com.example.ui.theme.MewarOnSurfaceVariant
import com.example.ui.theme.MewarSecondaryFixed
import com.example.ui.theme.MewarSurface
import com.example.ui.theme.MewarSurfaceContainerHigh
import com.example.ui.theme.MewarSurfaceContainerLow
import com.example.ui.theme.MewarTerracotta

@Composable
fun SavedScreen(
  savedPlaceIds: Set<String>,
  onPlaceClick: (Place) -> Unit,
  onToggleSave: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val savedPlaces = MewarData.allPlaces.filter { savedPlaceIds.contains(it.id) }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MewarSurface),
    contentPadding = PaddingValues(bottom = 96.dp)
  ) {
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 14.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "Saved Expeditions",
              fontSize = 22.sp,
              fontWeight = FontWeight.Bold,
              color = MewarOnSurface
            )
            Text(
              text = "${savedPlaces.size} curated places bookmarked for your trip",
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
                imageVector = Icons.Default.Bookmark,
                contentDescription = null,
                tint = MewarTerracotta,
                modifier = Modifier.size(18.dp)
              )
            }
          }
        }
      }
    }

    if (savedPlaces.isEmpty()) {
      item {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
          contentAlignment = Alignment.Center
        ) {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Surface(
              shape = CircleShape,
              color = MewarSurfaceContainerHigh,
              modifier = Modifier.size(64.dp)
            ) {
              Box(contentAlignment = Alignment.Center) {
                Icon(
                  imageVector = Icons.Default.Bookmark,
                  contentDescription = null,
                  tint = MewarOnSurfaceVariant,
                  modifier = Modifier.size(32.dp)
                )
              }
            }

            Text(
              text = "No saved places yet",
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold,
              color = MewarOnSurface
            )

            Text(
              text = "Tap the heart or bookmark icon on any palace, haveli, or sunset spot to save it here for quick access.",
              fontSize = 13.sp,
              color = MewarOnSurfaceVariant,
              modifier = Modifier.padding(horizontal = 20.dp)
            )
          }
        }
      }
    } else {
      items(savedPlaces) { place ->
        Surface(
          shape = RoundedCornerShape(18.dp),
          color = MewarSurfaceContainerLow,
          shadowElevation = 1.dp,
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .clickable { onPlaceClick(place) }
            .testTag("saved_item_${place.id}")
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
                Text(
                  text = place.tag,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = MewarTerracotta
                )

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
                text = "📍 ${place.distance} • ${place.timing}",
                fontSize = 11.sp,
                color = MewarOnSurfaceVariant
              )
            }

            IconButton(
              onClick = { onToggleSave(place.id) },
              modifier = Modifier.size(36.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Remove",
                tint = Color(0xFFD90429),
                modifier = Modifier.size(20.dp)
              )
            }
          }
        }
      }
    }
  }
}
