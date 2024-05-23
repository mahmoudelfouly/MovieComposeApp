package com.melfouly.movieapp.presentation.composable

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.melfouly.movieapp.R
import com.melfouly.movieapp.data.network.ApiHelper
import com.melfouly.movieapp.domain.model.Video

@Composable
fun VideoPoster(
    video: Video,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    Surface(
        modifier = modifier
            .height(100.dp)
            .width(150.dp)
            .border(
                border = BorderStroke(width = 1.dp, Color.Black),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                if (video.site == "YouTube") {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(ApiHelper.getYoutubeVideoPath(video.key))
                    )
                    context.startActivity(intent)
                }
            },
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            NetworkImage(
                networkUrl = ApiHelper.getYoutubeThumbnailPath(video.key),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
            )

            Image(
                painter = painterResource(id = R.drawable.youtube_icon),
                contentDescription = "",
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}