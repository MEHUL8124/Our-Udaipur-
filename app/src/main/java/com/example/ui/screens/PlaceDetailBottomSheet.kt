package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Place
import com.example.util.NavigationUtil
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailBottomSheet(
  place: Place?,
  isSaved: Boolean,
  onToggleSave: (String) -> Unit,
  sheetState: SheetState,
  onDismiss: () -> Unit
) {
  if (place == null) return

  var isAudioPlaying by remember { mutableStateOf(false) }
  var currentTrackIndex by remember { mutableIntStateOf(0) }
  var audioProgress by remember { mutableFloatStateOf(0.35f) }

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = MewarSurface,
    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    dragHandle = {
      Box(
        modifier = Modifier
          .padding(top = 10.dp, bottom = 4.dp)
          .size(width = 36.dp, height = 4.dp)
          .clip(CircleShape)
          .background(MewarSurfaceContainerHigh)
      )
    }
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .navigationBarsPadding()
        .padding(horizontal = 20.dp, vertical = 8.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      // Hero Image Card
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(220.dp)
      ) {
        Image(
          painter = painterResource(id = place.imageRes),
          contentDescription = place.name,
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
        )

        // Close & Save Action Buttons
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Surface(
            shape = CircleShape,
            color = MewarSurface.copy(alpha = 0.9f),
            modifier = Modifier
              .size(38.dp)
              .clickable { onDismiss() }
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = MewarNavy,
                modifier = Modifier.size(18.dp)
              )
            }
          }

          Surface(
            shape = CircleShape,
            color = MewarSurface.copy(alpha = 0.9f),
            modifier = Modifier
              .size(38.dp)
              .clickable { onToggleSave(place.id) }
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = if (isSaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Save",
                tint = if (isSaved) Color(0xFFD90429) else MewarNavy,
                modifier = Modifier.size(18.dp)
              )
            }
          }
        }
      }

      // Title & Meta Info
      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Surface(
            shape = CircleShape,
            color = MewarSecondaryFixed
          ) {
            Text(
              text = place.tag,
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = MewarTerracotta,
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
            )
          }

          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Star,
              contentDescription = null,
              tint = GoldenAmber,
              modifier = Modifier.size(14.dp)
            )
            Text(
              text = "${place.rating} (${place.reviewCount})",
              fontSize = 12.sp,
              fontWeight = FontWeight.SemiBold,
              color = MewarOnSurface
            )
          }

          Text(
            text = "•  📍 ${place.distance}",
            fontSize = 12.sp,
            color = MewarOnSurfaceVariant
          )
        }

        Text(
          text = place.name,
          fontSize = 24.sp,
          fontWeight = FontWeight.Bold,
          color = MewarOnSurface
        )

        if (place.timing.isNotEmpty()) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Schedule,
              contentDescription = null,
              tint = MewarTerracotta,
              modifier = Modifier.size(14.dp)
            )
            Text(
              text = place.timing,
              fontSize = 12.sp,
              fontWeight = FontWeight.Medium,
              color = MewarOnSurfaceVariant
            )
          }
        }
      }

      // Curated Audio Guide Player
      if (place.audioGuideIncluded && place.audioTracks.isNotEmpty()) {
        val currentTrack = place.audioTracks[currentTrackIndex]
        Surface(
          shape = RoundedCornerShape(20.dp),
          color = MewarNavyContainer,
          shadowElevation = 2.dp,
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Headphones,
                  contentDescription = null,
                  tint = MewarSecondaryFixed,
                  modifier = Modifier.size(18.dp)
                )
                Text(
                  text = "CURATED AUDIO NARRATION",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 1.sp,
                  color = MewarSecondaryFixed
                )
              }

              Text(
                text = "${currentTrackIndex + 1}/${place.audioTracks.size}",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White.copy(alpha = 0.7f)
              )
            }

            Text(
              text = currentTrack.title,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )

            Text(
              text = currentTrack.description,
              fontSize = 12.sp,
              color = Color.White.copy(alpha = 0.8f)
            )

            // Audio Scrubber Progress
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
              LinearProgressIndicator(
                progress = { audioProgress },
                modifier = Modifier
                  .fillMaxWidth()
                  .height(4.dp)
                  .clip(CircleShape),
                color = MewarTerracotta,
                trackColor = Color.White.copy(alpha = 0.2f)
              )

              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Text(
                  text = if (isAudioPlaying) "1:18" else "0:00",
                  fontSize = 11.sp,
                  color = Color.White.copy(alpha = 0.6f)
                )
                Text(
                  text = currentTrack.duration,
                  fontSize = 11.sp,
                  color = Color.White.copy(alpha = 0.6f)
                )
              }
            }

            // Controls
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                place.audioTracks.forEachIndexed { idx, _ ->
                  Box(
                    modifier = Modifier
                      .size(if (idx == currentTrackIndex) 18.dp else 8.dp, 8.dp)
                      .clip(CircleShape)
                      .background(
                        if (idx == currentTrackIndex) MewarTerracotta else Color.White.copy(
                          alpha = 0.3f
                        )
                      )
                      .clickable {
                        currentTrackIndex = idx
                        audioProgress = 0.2f
                      }
                  )
                }
              }

              Surface(
                shape = CircleShape,
                color = MewarTerracotta,
                modifier = Modifier
                  .size(42.dp)
                  .clickable { isAudioPlaying = !isAudioPlaying }
                  .testTag("audio_play_button")
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Icon(
                    imageVector = if (isAudioPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = if (isAudioPlaying) "Pause" else "Play",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                  )
                }
              }
            }
          }
        }
      }

      // Narrative Description
      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
          text = "About this landmark",
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold,
          color = MewarOnSurface
        )
        Text(
          text = place.description,
          fontSize = 14.sp,
          lineHeight = 22.sp,
          color = MewarOnSurfaceVariant
        )
      }

      // Insider Tip
      if (place.insiderTip.isNotEmpty()) {
        Surface(
          shape = RoundedCornerShape(16.dp),
          color = MewarSurfaceContainerHigh,
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(14.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Lightbulb,
              contentDescription = null,
              tint = MewarTerracotta,
              modifier = Modifier.size(20.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
              Text(
                text = "Insider Archival Tip",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MewarOnSurface
              )
              Text(
                text = place.insiderTip,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                color = MewarOnSurfaceVariant
              )
            }
          }
        }
      }

      // Action Button
      val context = LocalContext.current
      Button(
        onClick = {
          NavigationUtil.navigateToPlace(context, place)
        },
        colors = ButtonDefaults.buttonColors(
          containerColor = MewarButtonContainer,
          contentColor = Color.White
        ),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(50.dp)
          .testTag("navigate_to_place_button")
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Navigation,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
          )
          Text(
            text = "Navigate to Place",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))
    }
  }
}
