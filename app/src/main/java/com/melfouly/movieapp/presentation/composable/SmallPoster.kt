package com.melfouly.movieapp.presentation.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.melfouly.movieapp.data.network.ApiHelper
import com.melfouly.movieapp.domain.model.Movie

@Composable
fun SmallPoster(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(190.dp)
            .border(
                border = BorderStroke(width = 1.dp, Color.Black),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { },
        color = MaterialTheme.colorScheme.background
    ) {

        // Poster
        NetworkImage(
            networkUrl = ApiHelper.getPosterPath(movie.posterPath),
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
                .clip(RoundedCornerShape(8.dp))
        )

    }
}