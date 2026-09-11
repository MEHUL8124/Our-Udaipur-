package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
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
import com.example.ui.theme.MewarNavy
import com.example.ui.theme.MewarNavyContainer
import com.example.ui.theme.MewarOnSurface
import com.example.ui.theme.MewarOnSurfaceVariant
import com.example.ui.theme.MewarPrimaryFixed
import com.example.ui.theme.MewarSecondaryFixed
import com.example.ui.theme.MewarSecondaryFixedDim
import com.example.ui.theme.MewarSurface
import com.example.ui.theme.MewarSurfaceContainer
import com.example.ui.theme.MewarSurfaceContainerHigh
import com.example.ui.theme.MewarSurfaceContainerHighest
import com.example.ui.theme.MewarSurfaceContainerLow
import com.example.ui.theme.MewarTerracotta
import com.example.ui.theme.MewarTerracottaContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  onPlaceClick: (Place) -> Unit,
  onProfileClick: () -> Unit,
  onExploreAll: () -> Unit,
  savedPlaceIds: Set<String>,
  onToggleSave: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  var searchQuery by remember { mutableStateOf("") }
  var selectedFilter by remember { mutableStateOf("All spots") }

  val featured = MewarData.featuredExpedition
  val popularPlaces = MewarData.popularNearYou
  val digestPlaces = MewarData.trendingDigest

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MewarSurface),
    contentPadding = PaddingValues(bottom = 96.dp)
  ) {
    // 1. Top Header Bar
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Surface(
            shape = CircleShape,
            color = Color.White,
            shadowElevation = 2.dp,
            modifier = Modifier.size(38.dp)
          ) {
            Box(
              contentAlignment = Alignment.Center,
              modifier = Modifier.fillMaxSize()
            ) {
              Image(
                painter = painterResource(id = R.drawable.udaipur_travel_guide_logo),
                contentDescription = "Udaipur Travel Guide Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                  .size(38.dp)
                  .clip(CircleShape)
              )
            }
          }

          Column {
            Text(
              text = "UDAIPUR",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.6.sp,
              color = MewarTerracotta
            )
            Text(
              text = "Home",
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              color = MewarOnSurface
            )
          }
        }

        // Mehul Avatar with Golden Status Dot
        Box(
          modifier = Modifier
            .clickable { onProfileClick() }
            .testTag("avatar_profile_button")
        ) {
          Image(
            painter = painterResource(id = R.drawable.ananya_avatar),
            contentDescription = "Mehul Profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
              .size(42.dp)
              .clip(CircleShape)
          )
          // Golden status indicator
          Box(
            modifier = Modifier
              .size(10.dp)
              .clip(CircleShape)
              .background(GoldenAmber)
              .align(Alignment.TopEnd)
          )
        }
      }
    }

    // 2. Greeting & Golden Hour Subline
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 4.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Text(
            text = "Khamma Ghani, Mehul",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MewarOnSurface
          )
          Text(text = "✨", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Icon(
            imageVector = Icons.Default.WbSunny,
            contentDescription = null,
            tint = MewarTerracotta,
            modifier = Modifier.size(14.dp)
          )
          Text(
            text = "Tuesday, 24 Oct • Golden Hour 5:42 PM",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = MewarOnSurfaceVariant
          )
        }
      }
    }

    // 3. Search Bar
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 12.dp)
      ) {
        Surface(
          shape = RoundedCornerShape(18.dp),
          color = MewarSurfaceContainerLow,
          shadowElevation = 1.dp,
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Search,
              contentDescription = "Search",
              tint = MewarOnSurfaceVariant,
              modifier = Modifier.size(20.dp)
            )

            Box(modifier = Modifier.weight(1f)) {
              if (searchQuery.isEmpty()) {
                Text(
                  text = "Where do you want to go?",
                  fontSize = 15.sp,
                  color = MewarOnSurfaceVariant.copy(alpha = 0.7f)
                )
              }
              OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                  .fillMaxWidth()
                  .testTag("search_input"),
                colors = TextFieldDefaults.colors(
                  focusedContainerColor = Color.Transparent,
                  unfocusedContainerColor = Color.Transparent,
                  disabledContainerColor = Color.Transparent,
                  focusedIndicatorColor = Color.Transparent,
                  unfocusedIndicatorColor = Color.Transparent
                ),
                singleLine = true
              )
            }

            IconButton(
              onClick = { },
              modifier = Modifier.size(28.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Voice search",
                tint = MewarOnSurfaceVariant,
                modifier = Modifier.size(19.dp)
              )
            }

            IconButton(
              onClick = onExploreAll,
              modifier = Modifier.size(28.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Tune,
                contentDescription = "Filters",
                tint = MewarNavy,
                modifier = Modifier.size(19.dp)
              )
            }
          }
        }
      }
    }

    // 4. Quick Category Filters Row
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState())
          .padding(horizontal = 20.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        MewarData.quickFilters.forEach { filter ->
          val isSelected = filter == selectedFilter
          Surface(
            shape = CircleShape,
            color = if (isSelected) MewarTerracotta else MewarSurfaceContainerHigh,
            shadowElevation = if (isSelected) 2.dp else 0.dp,
            modifier = Modifier
              .clickable { selectedFilter = filter }
              .testTag("filter_$filter")
          ) {
            Text(
              text = filter,
              fontSize = 13.sp,
              fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
              color = if (isSelected) Color.White else MewarOnSurface,
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
            )
          }
        }
      }
    }

    // 5. Featured Expedition Card
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 14.dp)
      ) {
        Surface(
          shape = RoundedCornerShape(22.dp),
          shadowElevation = 6.dp,
          modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .clickable { onPlaceClick(featured) }
            .testTag("featured_expedition_card")
        ) {
          Box(modifier = Modifier.fillMaxSize()) {
            Image(
              painter = painterResource(id = featured.imageRes),
              contentDescription = featured.name,
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxSize()
            )

            // Deep gradient overlay
            Box(
              modifier = Modifier
                .fillMaxSize()
                .background(
                  Brush.verticalGradient(
                    0f to Color.Transparent,
                    0.4f to MewarNavy.copy(alpha = 0.35f),
                    0.7f to MewarNavy.copy(alpha = 0.85f),
                    1f to MewarNavy.copy(alpha = 0.98f)
                  )
                )
            )

            // Top Row: Featured Pill + Favorite Button
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                shape = CircleShape,
                color = MewarSurface.copy(alpha = 0.9f)
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                  Box(
                    modifier = Modifier
                      .size(6.dp)
                      .clip(CircleShape)
                      .background(MewarTerracotta)
                  )
                  Text(
                    text = "FEATURED EXPEDITION",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = MewarNavy
                  )
                }
              }

              Surface(
                shape = CircleShape,
                color = MewarSurface.copy(alpha = 0.9f),
                modifier = Modifier
                  .size(36.dp)
                  .clickable { onToggleSave(featured.id) }
              ) {
                Box(
                  contentAlignment = Alignment.Center,
                  modifier = Modifier.fillMaxSize()
                ) {
                  val isSaved = savedPlaceIds.contains(featured.id)
                  Icon(
                    imageVector = if (isSaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Save",
                    tint = if (isSaved) Color(0xFFD90429) else MewarNavy,
                    modifier = Modifier.size(18.dp)
                  )
                }
              }
            }

            // Bottom Content Lockup
            Column(
              modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(18.dp),
              verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              // Rating & Distance
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Star,
                  contentDescription = null,
                  tint = GoldenAmber,
                  modifier = Modifier.size(14.dp)
                )
                Text(
                  text = "${featured.rating} (${featured.reviewCount})",
                  fontSize = 12.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = Color.White
                )
                Text(text = "•", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
                Icon(
                  imageVector = Icons.Default.NearMe,
                  contentDescription = null,
                  tint = MewarPrimaryFixed,
                  modifier = Modifier.size(12.dp)
                )
                Text(
                  text = featured.distance,
                  fontSize = 12.sp,
                  color = MewarPrimaryFixed
                )
              }

              Text(
                text = featured.name,
                fontSize = 21.sp,
                lineHeight = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )

              Text(
                text = featured.description,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                color = MewarPrimaryFixed.copy(alpha = 0.9f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
              )

              Spacer(modifier = Modifier.height(2.dp))

              // Audio Guide Badge & Circular Action Button
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = "CURATED AUDIO GUIDE INCLUDED",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 1.sp,
                  color = MewarSecondaryFixedDim
                )

                Surface(
                  shape = CircleShape,
                  color = MewarTerracotta,
                  modifier = Modifier.size(34.dp)
                ) {
                  Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                  ) {
                    Icon(
                      imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                      contentDescription = "Open expedition",
                      tint = Color.White,
                      modifier = Modifier.size(18.dp)
                    )
                  }
                }
              }
            }
          }
        }
      }
    }

    // 6. Curated Itineraries Section
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 8.dp, bottom = 12.dp)
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Curated Itineraries",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MewarOnSurface
          )

          Text(
            text = "Browse All",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = MewarTerracotta,
            modifier = Modifier.clickable { onExploreAll() }
          )
        }

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 6.dp),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          // Heritage chip (selected styled like screenshot)
          Surface(
            shape = RoundedCornerShape(14.dp),
            color = MewarNavyContainer,
            modifier = Modifier.clickable { onExploreAll() }
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Text(text = "🏛️", fontSize = 14.sp)
              Text(
                text = "Heritage",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
              )
              Surface(
                shape = CircleShape,
                color = MewarTerracotta,
                modifier = Modifier.size(20.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text(
                    text = "12",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                  )
                }
              }
            }
          }

          // Lakes chip
          Surface(
            shape = RoundedCornerShape(14.dp),
            color = MewarSurfaceContainerHigh,
            modifier = Modifier.clickable { onExploreAll() }
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Text(text = "🌊", fontSize = 14.sp)
              Text(
                text = "Lakes",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MewarOnSurface
              )
            }
          }

          // Food chip
          Surface(
            shape = RoundedCornerShape(14.dp),
            color = MewarSurfaceContainerHigh,
            modifier = Modifier.clickable { onExploreAll() }
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Text(text = "🍽️", fontSize = 14.sp)
              Text(
                text = "Mewari Food",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MewarOnSurface
              )
            }
          }

          // Handicrafts chip
          Surface(
            shape = RoundedCornerShape(14.dp),
            color = MewarSurfaceContainerHigh,
            modifier = Modifier.clickable { onExploreAll() }
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Text(text = "🎨", fontSize = 14.sp)
              Text(
                text = "Handicrafts",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MewarOnSurface
              )
            }
          }
        }
      }
    }

    // 7. Popular Near You Section
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 10.dp, bottom = 14.dp)
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "Popular Near You",
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              color = MewarOnSurface
            )
            Text(
              text = "Walking distance from Old City center",
              fontSize = 12.sp,
              color = MewarOnSurfaceVariant
            )
          }

          Text(
            text = "See all (18) >",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = MewarTerracotta,
            modifier = Modifier.clickable { onExploreAll() }
          )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
          contentPadding = PaddingValues(horizontal = 20.dp),
          horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          items(popularPlaces) { place ->
            Surface(
              shape = RoundedCornerShape(18.dp),
              color = MewarSurfaceContainerLow,
              shadowElevation = 2.dp,
              modifier = Modifier
                .width(220.dp)
                .clickable { onPlaceClick(place) }
                .testTag("popular_place_${place.id}")
            ) {
              Column {
                Box(
                  modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                ) {
                  Image(
                    painter = painterResource(id = place.imageRes),
                    contentDescription = place.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                  )

                  // Tag Pill
                  Surface(
                    shape = CircleShape,
                    color = MewarSurface.copy(alpha = 0.9f),
                    modifier = Modifier
                      .align(Alignment.TopStart)
                      .padding(10.dp)
                  ) {
                    Text(
                      text = place.tag,
                      fontSize = 11.sp,
                      fontWeight = FontWeight.Bold,
                      color = MewarTerracotta,
                      modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                  }

                  // Rating pill
                  Surface(
                    shape = CircleShape,
                    color = MewarNavy.copy(alpha = 0.75f),
                    modifier = Modifier
                      .align(Alignment.BottomEnd)
                      .padding(10.dp)
                  ) {
                    Row(
                      modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                      verticalAlignment = Alignment.CenterVertically,
                      horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                      Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = GoldenAmber,
                        modifier = Modifier.size(12.dp)
                      )
                      Text(
                        text = "${place.rating}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                      )
                    }
                  }
                }

                Column(
                  modifier = Modifier.padding(12.dp),
                  verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                  Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    Text(
                      text = "📍 ${place.distance}",
                      fontSize = 11.sp,
                      color = MewarOnSurfaceVariant
                    )
                    if (place.subtitle.isNotEmpty()) {
                      Text(
                        text = place.subtitle,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = MewarTerracotta
                      )
                    }
                  }

                  Text(
                    text = place.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MewarOnSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                  )

                  Text(
                    text = place.description,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = MewarOnSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                  )
                }
              }
            }
          }
        }
      }
    }

    // 8. THE MEWAR DIGEST: Trending & Recommended
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 10.dp)
      ) {
        Text(
          text = "THE MEWAR DIGEST",
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.4.sp,
          color = MewarTerracotta
        )

        Text(
          text = "Trending & Recommended",
          fontSize = 19.sp,
          fontWeight = FontWeight.Bold,
          color = MewarOnSurface
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(
          verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          digestPlaces.forEach { place ->
            Surface(
              shape = RoundedCornerShape(18.dp),
              color = MewarSurfaceContainerLow,
              shadowElevation = 1.dp,
              modifier = Modifier
                .fillMaxWidth()
                .clickable { onPlaceClick(place) }
                .testTag("digest_item_${place.id}")
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                // Left Image
                Image(
                  painter = painterResource(id = place.imageRes),
                  contentDescription = place.name,
                  contentScale = ContentScale.Crop,
                  modifier = Modifier
                    .size(86.dp)
                    .clip(RoundedCornerShape(14.dp))
                )

                // Right Content
                Column(
                  modifier = Modifier.weight(1f),
                  verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                  if (place.badge.isNotEmpty()) {
                    Surface(
                      shape = CircleShape,
                      color = MewarSecondaryFixed
                    ) {
                      Text(
                        text = place.badge,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp,
                        color = MewarTerracotta,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
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
                    lineHeight = 16.sp,
                    color = MewarOnSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                  )

                  Text(
                    text = place.meta,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = MewarOnSurfaceVariant.copy(alpha = 0.8f)
                  )
                }
              }
            }
          }
        }
      }
    }

    // 9. Insider Hint Callout Card
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 14.dp)
      ) {
        Surface(
          shape = RoundedCornerShape(20.dp),
          color = MewarSurfaceContainerHigh,
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(18.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.Top
          ) {
            Surface(
              shape = CircleShape,
              color = MewarSecondaryFixed,
              modifier = Modifier.size(40.dp)
            ) {
              Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
              ) {
                Icon(
                  imageVector = Icons.Default.Lightbulb,
                  contentDescription = null,
                  tint = MewarTerracotta,
                  modifier = Modifier.size(20.dp)
                )
              }
            }

            Column(
              modifier = Modifier.weight(1f),
              verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Text(
                text = "Insider Hint",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MewarOnSurface
              )
              Text(
                text = MewarData.insiderHint,
                fontSize = 13.sp,
                lineHeight = 19.sp,
                color = MewarOnSurfaceVariant
              )
            }
          }
        }
      }
    }
  }
}
