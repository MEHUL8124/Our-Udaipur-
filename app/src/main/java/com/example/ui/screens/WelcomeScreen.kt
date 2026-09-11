package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.MewarNavy
import com.example.ui.theme.MewarPrimaryFixed
import com.example.ui.theme.MewarPrimaryFixedDim
import com.example.ui.theme.MewarSecondaryFixed
import com.example.ui.theme.MewarSecondaryFixedDim
import com.example.ui.theme.MewarTerracottaContainer
import kotlin.math.roundToInt

@Composable
fun WelcomeScreen(
  onBegin: () -> Unit,
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "welcome_anim")
  val pulseAlpha by infiniteTransition.animateFloat(
    initialValue = 0.4f,
    targetValue = 1f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulseAlpha"
  )

  val bounceY by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = -8f,
    animationSpec = infiniteRepeatable(
      animation = tween(1000, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "bounceY"
  )

  var logoVisible by remember { mutableStateOf(false) }
  LaunchedEffect(Unit) {
    logoVisible = true
  }

  val logoScale by animateFloatAsState(
    targetValue = if (logoVisible) 1f else 0.85f,
    animationSpec = tween(durationMillis = 750, easing = FastOutSlowInEasing),
    label = "logoScale"
  )
  val logoAlpha by animateFloatAsState(
    targetValue = if (logoVisible) 1f else 0f,
    animationSpec = tween(durationMillis = 650, easing = FastOutSlowInEasing),
    label = "logoAlpha"
  )

  val draggableState = rememberDraggableState { delta ->
    if (delta < -20f) {
      onBegin()
    }
  }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(MewarNavy)
      .draggable(
        state = draggableState,
        orientation = Orientation.Vertical
      )
  ) {
    // Fullscreen Background Photograph
    Image(
      painter = painterResource(id = R.drawable.hero_lake_pichola),
      contentDescription = "Lake Pichola and Taj Lake Palace in Udaipur at twilight",
      contentScale = ContentScale.Crop,
      modifier = Modifier.fillMaxSize()
    )

    // Atmospheric Layered Gradients matching HTML design
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          Brush.verticalGradient(
            0f to MewarNavy.copy(alpha = 0.70f),
            0.4f to MewarNavy.copy(alpha = 0.25f),
            0.75f to MewarNavy.copy(alpha = 0.85f),
            1f to MewarNavy.copy(alpha = 0.98f)
          )
        )
    )

    // Radial sunset warmth overlay
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          Brush.radialGradient(
            colors = listOf(
              MewarTerracottaContainer.copy(alpha = 0.15f),
              Color.Transparent
            ),
            radius = 900f
          )
        )
    )

    // Main Content
    Column(
      modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .navigationBarsPadding()
        .padding(horizontal = 24.dp, vertical = 16.dp),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      // Top Status Bar Capsule & Archival Tag
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          shape = CircleShape,
          color = Color.White.copy(alpha = 0.15f),
          modifier = Modifier.shadow(4.dp, CircleShape)
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Box(
              modifier = Modifier
                .size(7.dp)
                .clip(CircleShape)
                .background(MewarSecondaryFixedDim.copy(alpha = pulseAlpha))
            )
            Text(
              text = "LAKE PICHOLA • 24°N",
              fontSize = 11.sp,
              fontWeight = FontWeight.SemiBold,
              letterSpacing = 1.6.sp,
              color = MewarPrimaryFixed
            )
          }
        }

        Text(
          text = "ARCHIVAL EDITION",
          fontSize = 11.sp,
          fontWeight = FontWeight.Medium,
          letterSpacing = 1.6.sp,
          color = MewarPrimaryFixedDim.copy(alpha = 0.85f)
        )
      }

      // Bottom Editorial Lockup
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // Official Udaipur Travel Guide Logo with Entrance Animation
        Box(
          contentAlignment = Alignment.Center,
          modifier = Modifier
            .graphicsLayer {
              scaleX = logoScale
              scaleY = logoScale
              alpha = logoAlpha
            }
            .clickable(
              indication = null,
              interactionSource = remember { MutableInteractionSource() }
            ) { onBegin() }
        ) {
          // Warm terracotta ambient glow
          Box(
            modifier = Modifier
              .size(148.dp)
              .clip(CircleShape)
              .background(MewarTerracottaContainer.copy(alpha = 0.30f))
          )

          // Brand logo circular card
          Surface(
            shape = CircleShape,
            color = Color.White,
            shadowElevation = 10.dp,
            modifier = Modifier.size(136.dp)
          ) {
            Box(
              contentAlignment = Alignment.Center,
              modifier = Modifier.fillMaxSize()
            ) {
              Image(
                painter = painterResource(id = R.drawable.udaipur_travel_guide_logo),
                contentDescription = "Udaipur Travel Guide Official Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                  .size(136.dp)
                  .clip(CircleShape)
              )
            }
          }
        }

        // Mewar Arch Divider Line
        Row(
          modifier = Modifier.width(180.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Box(
            modifier = Modifier
              .weight(1f)
              .height(1.dp)
              .background(
                Brush.horizontalGradient(
                  colors = listOf(Color.Transparent, MewarSecondaryFixed)
                )
              )
          )

          // Water drop / arch symbol
          Box(
            modifier = Modifier
              .padding(horizontal = 8.dp)
              .size(8.dp)
              .clip(CircleShape)
              .background(MewarSecondaryFixed)
          )

          Box(
            modifier = Modifier
              .weight(1f)
              .height(1.dp)
              .background(
                Brush.horizontalGradient(
                  colors = listOf(MewarSecondaryFixed, Color.Transparent)
                )
              )
          )
        }

        // Identity Typography
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Text(
            text = "Udaipur",
            fontSize = 44.sp,
            lineHeight = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center
          )

          Text(
            text = "Discover Udaipur differently.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = MewarPrimaryFixed,
            textAlign = TextAlign.Center
          )
        }

        // Heritage Badge
        Surface(
          shape = CircleShape,
          color = MewarNavy.copy(alpha = 0.70f),
          shadowElevation = 4.dp
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(
              imageVector = Icons.Default.AutoAwesome,
              contentDescription = null,
              tint = MewarSecondaryFixedDim,
              modifier = Modifier.size(15.dp)
            )
            Text(
              text = "Curated City Guide • Mewar Edition",
              fontSize = 12.sp,
              fontWeight = FontWeight.Medium,
              letterSpacing = 0.6.sp,
              color = MewarSecondaryFixed
            )
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Interactive Swipe Up Button
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier
            .offset { IntOffset(0, bounceY.roundToInt()) }
            .clickable { onBegin() }
            .testTag("enter_button")
            .padding(12.dp)
        ) {
          Surface(
            shape = CircleShape,
            color = Color.White.copy(alpha = 0.18f),
            modifier = Modifier.size(44.dp)
          ) {
            Box(
              contentAlignment = Alignment.Center,
              modifier = Modifier.fillMaxSize()
            ) {
              Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Swipe up to begin",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = "SWIPE UP TO BEGIN",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 2.sp,
            color = MewarPrimaryFixedDim
          )
        }
      }
    }
  }
}
