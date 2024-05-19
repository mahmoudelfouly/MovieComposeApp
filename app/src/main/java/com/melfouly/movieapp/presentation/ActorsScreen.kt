package com.melfouly.movieapp.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.melfouly.movieapp.presentation.ui.movie.NetworkImage

@Composable
fun ActorsScreen() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(290.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black),
    ) {
        NetworkImage(networkUrl = "https://github.com/skydoves/MovieCompose/raw/main/previews/preview2.png")
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "Actor's Name",
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
            )
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    ActorsScreen()
}