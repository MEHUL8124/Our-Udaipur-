package com.example.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Castle
import androidx.compose.material.icons.filled.NorthEast
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.MewarData
import com.example.ui.theme.MewarNavy
import com.example.ui.theme.MewarNavyContainer
import com.example.ui.theme.MewarOnSurface
import com.example.ui.theme.MewarOnSurfaceVariant
import com.example.ui.theme.MewarSecondaryFixed
import com.example.ui.theme.MewarSurface
import com.example.ui.theme.MewarSurfaceContainer
import com.example.ui.theme.MewarSurfaceContainerHigh
import com.example.ui.theme.MewarSurfaceContainerHighest
import com.example.ui.theme.MewarTerracotta

@Composable
fun OnboardingScreen(
  onComplete: () -> Unit,
  onJumpToMap: () -> Unit,
  modifier: Modifier = Modifier
) {
  val steps = MewarData.onboardingSteps
  var currentStepIndex by remember { mutableIntStateOf(0) }
  var isBookmarked by remember { mutableStateOf(false) }

  val currentStep = steps[currentStepIndex]
  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MewarSurface)
      .statusBarsPadding()
      .navigationBarsPadding()
      .padding(horizontal = 20.dp)
      .verticalScroll(scrollState),
    verticalArrangement = Arrangement.SpaceBetween
  ) {
    // Top Bar
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 12.dp),
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
          Image(
            painter = painterResource(id = R.drawable.udaipur_travel_guide_logo),
            contentDescription = "Udaipur Travel Guide Official Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
              .size(38.dp)
              .clip(CircleShape)
          )
        }
        Column {
          Text(
            text = "UDAIPUR",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = MewarTerracotta,
            letterSpacing = 1.6.sp
          )
          Text(
            text = "Travel Guide",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = MewarNavyContainer,
            letterSpacing = (-0.3).sp
          )
        }
      }

      TextButton(
        onClick = onComplete,
        modifier = Modifier.testTag("skip_button")
      ) {
        Text(
          text = "Skip",
          color = MewarOnSurfaceVariant,
          fontSize = 14.sp,
          fontWeight = FontWeight.Medium
        )
      }
    }

    // Carousel with Stacked Peeking Visual Cards
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
    ) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(380.dp),
        contentAlignment = Alignment.Center
      ) {
        // Step 3 peek layer (in back, shifted right and slightly scaled down)
        if (currentStepIndex < 2) {
          Box(
            modifier = Modifier
              .fillMaxWidth(0.85f)
              .height(330.dp)
              .offset(x = 24.dp, y = 12.dp)
              .scale(0.92f)
              .clip(RoundedCornerShape(20.dp))
              .background(MewarSurfaceContainerHigh)
          ) {
            Image(
              painter = painterResource(id = steps[2].imageRes),
              contentDescription = null,
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxSize()
            )
            Box(
              modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.45f))
            )
          }
        }

        // Step 2 peek layer
        if (currentStepIndex == 0) {
          Box(
            modifier = Modifier
              .fillMaxWidth(0.92f)
              .height(350.dp)
              .offset(x = 12.dp, y = 6.dp)
              .scale(0.96f)
              .clip(RoundedCornerShape(20.dp))
              .background(MewarSurfaceContainer)
          ) {
            Image(
              painter = painterResource(id = steps[1].imageRes),
              contentDescription = null,
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxSize()
            )
            Box(
              modifier = Modifier
                .fillMaxSize()
                .background(MewarNavyContainer.copy(alpha = 0.35f))
            )
          }
        }

        // Active Foreground Hero Card
        AnimatedContent(
          targetState = currentStepIndex,
          transitionSpec = {
            val direction = if (targetState > initialState) 1 else -1
            (slideInHorizontally(
              initialOffsetX = { fullWidth -> direction * fullWidth },
              animationSpec = tween(durationMillis = 380, easing = FastOutSlowInEasing)
            ) + fadeIn(
              animationSpec = tween(durationMillis = 380, easing = FastOutSlowInEasing)
            )).togetherWith(
              slideOutHorizontally(
                targetOffsetX = { fullWidth -> -direction * fullWidth },
                animationSpec = tween(durationMillis = 380, easing = FastOutSlowInEasing)
              ) + fadeOut(
                animationSpec = tween(durationMillis = 280, easing = FastOutSlowInEasing)
              )
            )
          },
          label = "hero_card"
        ) { stepIndex ->
          val step = steps[stepIndex]
          Surface(
            shape = RoundedCornerShape(20.dp),
            shadowElevation = 8.dp,
            modifier = Modifier
              .fillMaxWidth()
              .height(360.dp)
          ) {
            Box(modifier = Modifier.fillMaxSize()) {
              Image(
                painter = painterResource(id = step.imageRes),
                contentDescription = step.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
              )

              // Atmospheric vignette gradient
              Box(
                modifier = Modifier
                  .fillMaxSize()
                  .background(
                    Brush.verticalGradient(
                      0f to Color.Transparent,
                      0.5f to MewarNavy.copy(alpha = 0.25f),
                      1f to MewarNavy.copy(alpha = 0.85f)
                    )
                  )
              )

              // Top Floating Location Pill
              Surface(
                shape = CircleShape,
                color = MewarSurface.copy(alpha = 0.92f),
                shadowElevation = 2.dp,
                modifier = Modifier
                  .align(Alignment.TopStart)
                  .padding(16.dp)
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.Castle,
                    contentDescription = null,
                    tint = MewarTerracotta,
                    modifier = Modifier.size(16.dp)
                  )
                  Text(
                    text = step.locationPill,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MewarNavyContainer,
                    letterSpacing = 0.6.sp
                  )
                }
              }

              // Bottom Mood Pill & Bookmark Button
              Row(
                modifier = Modifier
                  .align(Alignment.BottomCenter)
                  .fillMaxWidth()
                  .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Surface(
                  shape = CircleShape,
                  color = MewarNavyContainer.copy(alpha = 0.75f)
                ) {
                  Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                  ) {
                    Icon(
                      imageVector = Icons.Default.WbTwilight,
                      contentDescription = null,
                      tint = MewarSecondaryFixed,
                      modifier = Modifier.size(15.dp)
                    )
                    Text(
                      text = step.moodPill,
                      fontSize = 12.sp,
                      fontWeight = FontWeight.Medium,
                      color = Color.White
                    )
                  }
                }

                Surface(
                  shape = CircleShape,
                  color = MewarSurface.copy(alpha = 0.92f),
                  shadowElevation = 2.dp,
                  modifier = Modifier
                    .size(36.dp)
                    .clickable { isBookmarked = !isBookmarked }
                ) {
                  Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                  ) {
                    Icon(
                      imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                      contentDescription = "Bookmark",
                      tint = if (isBookmarked) MewarTerracotta else MewarNavy,
                      modifier = Modifier.size(18.dp)
                    )
                  }
                }
              }
            }
          }
        }
      }

      // Next Peek Cue
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 8.dp, end = 4.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = currentStep.nextHint,
          fontSize = 12.sp,
          color = MewarOnSurfaceVariant.copy(alpha = 0.8f),
          fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowForward,
          contentDescription = null,
          tint = MewarOnSurfaceVariant.copy(alpha = 0.8f),
          modifier = Modifier.size(14.dp)
        )
      }
    }

    // Editorial Content with smooth slide transitions
    AnimatedContent(
      targetState = currentStepIndex,
      transitionSpec = {
        val direction = if (targetState > initialState) 1 else -1
        (slideInHorizontally(
          initialOffsetX = { fullWidth -> direction * (fullWidth / 2) },
          animationSpec = tween(durationMillis = 380, easing = FastOutSlowInEasing)
        ) + fadeIn(
          animationSpec = tween(durationMillis = 380, easing = FastOutSlowInEasing)
        )).togetherWith(
          slideOutHorizontally(
            targetOffsetX = { fullWidth -> -direction * (fullWidth / 2) },
            animationSpec = tween(durationMillis = 380, easing = FastOutSlowInEasing)
          ) + fadeOut(
            animationSpec = tween(durationMillis = 280, easing = FastOutSlowInEasing)
          )
        )
      },
      label = "editorial_content",
      modifier = Modifier.fillMaxWidth()
    ) { stepIndex ->
      val step = steps[stepIndex]
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        // Step Tag
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Box(
            modifier = Modifier
              .size(7.dp)
              .clip(CircleShape)
              .background(MewarTerracotta)
          )
          Text(
            text = "${step.stepNumber} • ${step.stepLabel.uppercase()}",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.2.sp,
            color = MewarTerracotta
          )
        }

        Text(
          text = step.title,
          fontSize = 25.sp,
          lineHeight = 32.sp,
          fontWeight = FontWeight.Bold,
          color = MewarOnSurface
        )

        Text(
          text = step.description,
          fontSize = 14.sp,
          lineHeight = 22.sp,
          color = MewarOnSurfaceVariant
        )
      }
    }

    // Bottom Stepper Controls & Action Buttons
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      // Step Indicator Dots
      Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        steps.forEachIndexed { index, _ ->
          val isSelected = index == currentStepIndex
          Box(
            modifier = Modifier
              .height(8.dp)
              .width(if (isSelected) 32.dp else 8.dp)
              .clip(CircleShape)
              .background(if (isSelected) MewarTerracotta else MewarSurfaceContainerHighest)
              .clickable { currentStepIndex = index }
          )
        }
      }

      // Actions
      Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Button(
          onClick = {
            if (currentStepIndex < steps.size - 1) {
              currentStepIndex++
            } else {
              onComplete()
            }
          },
          colors = ButtonDefaults.buttonColors(
            containerColor = MewarNavyContainer,
            contentColor = Color.White
          ),
          shape = RoundedCornerShape(14.dp),
          modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .testTag("continue_button")
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Text(
              text = if (currentStepIndex == steps.size - 1) "Get Started" else "Continue",
              fontSize = 15.sp,
              fontWeight = FontWeight.SemiBold
            )
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowForward,
              contentDescription = null,
              modifier = Modifier.size(18.dp)
            )
          }
        }

        TextButton(
          onClick = onJumpToMap,
          modifier = Modifier
            .fillMaxWidth()
            .testTag("jump_to_map_button")
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Text(
              text = "Already familiar? Jump to map",
              color = MewarTerracotta,
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold
            )
            Icon(
              imageVector = Icons.Default.NorthEast,
              contentDescription = null,
              tint = MewarTerracotta,
              modifier = Modifier.size(16.dp)
            )
          }
        }
      }
    }
  }
}
