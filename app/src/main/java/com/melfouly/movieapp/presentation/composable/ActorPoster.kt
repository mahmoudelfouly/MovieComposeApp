package com.melfouly.movieapp.presentation.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.melfouly.movieapp.data.network.ApiHelper
import com.melfouly.movieapp.domain.model.Actor
import com.melfouly.movieapp.presentation.navigation.HomeNavigationTab

@Composable
fun ActorPoster(
    actor: Actor,
    onNavigateToDetails: (homeNavigationTab: HomeNavigationTab, id: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(290.dp)
            .border(
                border = BorderStroke(width = 1.dp, Color.Black),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onNavigateToDetails(HomeNavigationTab.ACTORS, actor.id) },
        color = MaterialTheme.colorScheme.background
    ) {
        ConstraintLayout {
            val (box, poster, title) = createRefs()

            // Poster
            NetworkImage(
                networkUrl = ApiHelper.getPosterPath(actor.profilePath),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .clip(CutCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                    .constrainAs(poster) {
                        top.linkTo(parent.top)
                    }
            )

            Box(
                modifier = Modifier
                    .height(50.dp)
                    .alpha(0.7f)
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .constrainAs(box) {
                        top.linkTo(poster.bottom)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Text(
                text = actor.name,
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.85f)
                    .constrainAs(title) {
                        top.linkTo(box.top)
                        bottom.linkTo(box.bottom)
                    }
            )
        }
    }
}