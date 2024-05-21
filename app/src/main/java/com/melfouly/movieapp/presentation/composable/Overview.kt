package com.melfouly.movieapp.presentation.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Overview(
    title: String,
    desc: String
) {
    Text(
        modifier = Modifier.padding(horizontal = 8.dp),
        text = "$title:",
        color = Color.Black,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
    )
    Text(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        text = desc,
        color = Color.Black,
    )
}