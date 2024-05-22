package com.melfouly.movieapp.presentation.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.melfouly.movieapp.R

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onBackPressed: (Boolean) -> Unit
) {
    FloatingActionButton(
        modifier = modifier
            .padding(8.dp),
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = { onBackPressed(true) }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = ""
        )
    }
}