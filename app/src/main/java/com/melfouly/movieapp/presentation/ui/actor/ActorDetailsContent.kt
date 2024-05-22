package com.melfouly.movieapp.presentation.ui.actor

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.melfouly.movieapp.data.network.ApiHelper
import com.melfouly.movieapp.domain.model.ActorDetails
import com.melfouly.movieapp.presentation.composable.BackButton
import com.melfouly.movieapp.presentation.composable.KeywordCard
import com.melfouly.movieapp.presentation.composable.NetworkImage
import com.melfouly.movieapp.presentation.composable.Overview

@Composable
fun ActorDetailsContent(
    actorDetails: ActorDetails,
    scrollState: ScrollState,
    onBackPressed: (Boolean) -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            NetworkImage(
                networkUrl = ApiHelper.getPosterPath(actorDetails.profilePath),
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                text = actorDetails.name,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            Text(
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                text = "Also known as:",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
            LazyRow(
                modifier = Modifier.padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                actorDetails.alsoKnownAs?.size?.let {
                    items(it) { index ->
                        KeywordCard(name = actorDetails.alsoKnownAs[index])
                    }
                }
            }
            Overview(title = "Biography", desc = actorDetails.biography)
        }


        BackButton(modifier = Modifier.align(Alignment.TopStart), onBackPressed = onBackPressed)
    }
}