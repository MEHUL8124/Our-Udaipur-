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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessAuto
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.AppThemeMode
import com.example.ui.theme.GoldenAmber
import com.example.ui.theme.MewarButtonContainer
import com.example.ui.theme.MewarNavy
import com.example.ui.theme.MewarNavyContainer
import com.example.ui.theme.MewarOnSurface
import com.example.ui.theme.MewarOnSurfaceVariant
import com.example.ui.theme.MewarSecondaryFixed
import com.example.ui.theme.MewarSurface
import com.example.ui.theme.MewarSurfaceContainerHigh
import com.example.ui.theme.MewarSurfaceContainerLow
import com.example.ui.theme.MewarTerracotta
import com.example.ui.theme.MewarTerracottaContainer

@Composable
fun ProfileScreen(
  currentThemeMode: AppThemeMode,
  onThemeModeChange: (AppThemeMode) -> Unit,
  onRevisitWelcome: () -> Unit,
  modifier: Modifier = Modifier
) {
  var goldenHourNotifications by remember { mutableStateOf(true) }
  var offlineAudioGuide by remember { mutableStateOf(true) }

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
          .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // Avatar + Status Badge
        Box(contentAlignment = Alignment.BottomEnd) {
          Image(
            painter = painterResource(id = R.drawable.ananya_avatar),
            contentDescription = "Mehul's Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
              .size(96.dp)
              .clip(CircleShape)
          )

          Surface(
            shape = CircleShape,
            color = GoldenAmber,
            modifier = Modifier.size(24.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = Icons.Default.WbSunny,
                contentDescription = null,
                tint = MewarNavy,
                modifier = Modifier.size(14.dp)
              )
            }
          }
        }

        // Name and Mewar Edition Tier
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Text(
            text = "Mehul Sharma",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MewarOnSurface
          )

          Surface(
            shape = CircleShape,
            color = MewarSecondaryFixed
          ) {
            Text(
              text = "MEWĀR CONNOISSEUR • VIP EDITION",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 0.8.sp,
              color = MewarTerracotta,
              modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
          }

          Text(
            text = "Trip: 24 Oct – 28 Oct • Lake Pichola Itinerary",
            fontSize = 13.sp,
            color = MewarOnSurfaceVariant
          )
        }

        // Stats Row
        Surface(
          shape = RoundedCornerShape(20.dp),
          color = MewarSurfaceContainerLow,
          shadowElevation = 1.dp,
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 16.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(
                text = "8",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MewarOnSurface
              )
              Text(
                text = "Places Visited",
                fontSize = 12.sp,
                color = MewarOnSurfaceVariant
              )
            }

            Box(
              modifier = Modifier
                .size(width = 1.dp, height = 30.dp)
                .background(MewarSurfaceContainerHigh)
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(
                text = "4",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MewarOnSurface
              )
              Text(
                text = "Audio Guides",
                fontSize = 12.sp,
                color = MewarOnSurfaceVariant
              )
            }

            Box(
              modifier = Modifier
                .size(width = 1.dp, height = 30.dp)
                .background(MewarSurfaceContainerHigh)
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(
                text = "142",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MewarOnSurface
              )
              Text(
                text = "Photos Captured",
                fontSize = 12.sp,
                color = MewarOnSurfaceVariant
              )
            }
          }
        }

        // Settings / Appearance Section
        Column(
          modifier = Modifier.fillMaxWidth(),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Text(
            text = "Appearance",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MewarOnSurface
          )

          Surface(
            shape = RoundedCornerShape(18.dp),
            color = MewarSurfaceContainerLow,
            shadowElevation = 1.dp,
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(
              modifier = Modifier.padding(16.dp),
              verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Column(modifier = Modifier.weight(1f)) {
                  Text(
                    text = "App Theme",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MewarOnSurface
                  )
                  Text(
                    text = currentThemeMode.description,
                    fontSize = 12.sp,
                    color = MewarOnSurfaceVariant
                  )
                }

                Surface(
                  shape = CircleShape,
                  color = when (currentThemeMode) {
                    AppThemeMode.DARK -> MewarTerracottaContainer
                    AppThemeMode.LIGHT -> MewarSecondaryFixed
                    AppThemeMode.SYSTEM -> MewarSurfaceContainerHigh
                  },
                  modifier = Modifier.size(36.dp)
                ) {
                  Box(contentAlignment = Alignment.Center) {
                    Icon(
                      imageVector = when (currentThemeMode) {
                        AppThemeMode.LIGHT -> Icons.Default.WbSunny
                        AppThemeMode.DARK -> Icons.Default.DarkMode
                        AppThemeMode.SYSTEM -> Icons.Default.BrightnessAuto
                      },
                      contentDescription = null,
                      tint = when (currentThemeMode) {
                        AppThemeMode.LIGHT -> MewarTerracotta
                        AppThemeMode.DARK -> MewarTerracotta
                        AppThemeMode.SYSTEM -> MewarOnSurfaceVariant
                      },
                      modifier = Modifier.size(20.dp)
                    )
                  }
                }
              }

              // Segmented Selector with Sun, Moon, and System options
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .background(MewarSurfaceContainerHigh, RoundedCornerShape(14.dp))
                  .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                val options = listOf(
                  Triple(AppThemeMode.SYSTEM, "System", Icons.Default.BrightnessAuto),
                  Triple(AppThemeMode.LIGHT, "Light", Icons.Default.WbSunny),
                  Triple(AppThemeMode.DARK, "Dark", Icons.Default.DarkMode)
                )

                options.forEach { (mode, label, icon) ->
                  val isSelected = currentThemeMode == mode
                  Surface(
                    onClick = { onThemeModeChange(mode) },
                    shape = RoundedCornerShape(10.dp),
                    color = if (isSelected) MewarSurface else Color.Transparent,
                    shadowElevation = if (isSelected) 2.dp else 0.dp,
                    modifier = Modifier
                      .weight(1f)
                      .height(44.dp)
                      .testTag("theme_option_${mode.storageKey}")
                  ) {
                    Row(
                      modifier = Modifier.fillMaxSize(),
                      horizontalArrangement = Arrangement.Center,
                      verticalAlignment = Alignment.CenterVertically
                    ) {
                      Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = if (isSelected) MewarTerracotta else MewarOnSurfaceVariant,
                        modifier = Modifier.size(16.dp)
                      )
                      Spacer(modifier = Modifier.width(6.dp))
                      Text(
                        text = label,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) MewarOnSurface else MewarOnSurfaceVariant
                      )
                    }
                  }
                }
              }
            }
          }
        }

        // Settings Section
        Column(
          modifier = Modifier.fillMaxWidth(),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Text(
            text = "Preferences & Experiences",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MewarOnSurface
          )

          // Golden Hour Switch
          Surface(
            shape = RoundedCornerShape(16.dp),
            color = MewarSurfaceContainerLow,
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(16.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Notifications,
                  contentDescription = null,
                  tint = MewarTerracotta
                )
                Column {
                  Text(
                    text = "Golden Hour Sunset Alert",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MewarOnSurface
                  )
                  Text(
                    text = "Notifies 30 mins before Lake Pichola sunset",
                    fontSize = 11.sp,
                    color = MewarOnSurfaceVariant
                  )
                }
              }

              Switch(
                checked = goldenHourNotifications,
                onCheckedChange = { goldenHourNotifications = it },
                colors = SwitchDefaults.colors(
                  checkedThumbColor = Color.White,
                  checkedTrackColor = MewarTerracotta
                )
              )
            }
          }

          // Offline Guides Switch
          Surface(
            shape = RoundedCornerShape(16.dp),
            color = MewarSurfaceContainerLow,
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(16.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Headphones,
                  contentDescription = null,
                  tint = MewarTerracotta
                )
                Column {
                  Text(
                    text = "Offline Audio Guides",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MewarOnSurface
                  )
                  Text(
                    text = "High quality palace narration downloaded",
                    fontSize = 11.sp,
                    color = MewarOnSurfaceVariant
                  )
                }
              }

              Switch(
                checked = offlineAudioGuide,
                onCheckedChange = { offlineAudioGuide = it },
                colors = SwitchDefaults.colors(
                  checkedThumbColor = Color.White,
                  checkedTrackColor = MewarTerracotta
                )
              )
            }
          }

          // Audio Language
          Surface(
            shape = RoundedCornerShape(16.dp),
            color = MewarSurfaceContainerLow,
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(16.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Language,
                  contentDescription = null,
                  tint = MewarTerracotta
                )
                Column {
                  Text(
                    text = "Audio Guide Narration",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MewarOnSurface
                  )
                  Text(
                    text = "English (Royal Archival Storyteller)",
                    fontSize = 11.sp,
                    color = MewarOnSurfaceVariant
                  )
                }
              }

              Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MewarOnSurfaceVariant
              )
            }
          }

          Spacer(modifier = Modifier.height(6.dp))

          // Replay Welcome Experience
          Button(
            onClick = onRevisitWelcome,
            colors = ButtonDefaults.buttonColors(
              containerColor = MewarButtonContainer,
              contentColor = Color.White
            ),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(50.dp)
              .testTag("revisit_welcome_button")
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Replay,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
              )
              Text(
                text = "Revisit Welcome & Onboarding Experience",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
              )
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          // Official Udaipur Travel Guide Branding Card
          Surface(
            shape = RoundedCornerShape(20.dp),
            color = MewarSurfaceContainerLow,
            modifier = Modifier
              .fillMaxWidth()
              .testTag("app_about_branding_card")
          ) {
            Column(
              modifier = Modifier.padding(20.dp),
              horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Surface(
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 3.dp,
                modifier = Modifier.size(72.dp)
              ) {
                Image(
                  painter = painterResource(id = R.drawable.udaipur_travel_guide_logo),
                  contentDescription = "Udaipur Travel Guide Official Logo",
                  contentScale = ContentScale.Fit,
                  modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                )
              }

              Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
              ) {
                Text(
                  text = "UDAIPUR",
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 2.sp,
                  color = MewarTerracotta
                )
                Text(
                  text = "Travel Guide",
                  fontSize = 18.sp,
                  fontWeight = FontWeight.Bold,
                  color = MewarOnSurface
                )
                Text(
                  text = "Version 2.4.0 • Archival Edition",
                  fontSize = 12.sp,
                  color = MewarOnSurfaceVariant
                )
              }

              Text(
                text = "Official city guide and archival travel companion for Udaipur, Rajasthan.",
                fontSize = 12.sp,
                color = MewarOnSurfaceVariant,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
              )
            }
          }
        }
      }
    }
  }
}
