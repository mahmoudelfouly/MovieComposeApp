package com.melfouly.movieapp.presentation.composable

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.melfouly.movieapp.R
import com.melfouly.movieapp.presentation.ui.theme.StarColor
import kotlinx.coroutines.delay

@Composable
fun RateBar(
    rate: Float,
    modifier: Modifier = Modifier
) {

    var animatedRate by remember { mutableStateOf(0f) }

    val animationProgress by animateFloatAsState(
        targetValue = animatedRate,
        animationSpec = tween(durationMillis = 2000, easing = LinearEasing),
        label = ""
    )

    LaunchedEffect(rate) {
        delay(500)
        animatedRate = rate / 2f
    }

    Row(modifier = modifier.wrapContentSize()) {
        (1..5).forEach { index ->
            val painter = when {
                animationProgress >= index -> painterResource(id = R.drawable.outline_star)
                animationProgress > index - 1 -> painterResource(id = R.drawable.outline_star_half)
                else -> painterResource(id = R.drawable.outline_star_border)
            }

            Image(
                painter = painter,
                contentDescription = "",
                colorFilter = ColorFilter.tint(StarColor),
            )
        }

    }
}