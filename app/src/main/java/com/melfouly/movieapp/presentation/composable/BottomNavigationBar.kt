package com.melfouly.movieapp.presentation.composable

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.melfouly.movieapp.presentation.navigation.HomeNavigationTab

@Composable
fun BottomNavigationBar(
    selectedTab: HomeNavigationTab,
    onClickTab: (HomeNavigationTab) -> Unit
) {

    val tabs = HomeNavigationTab.entries

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 4.dp,
    ) {
        tabs.forEach { tab ->
            NavigationBarItem(
                selected = tab == selectedTab,
                onClick = { onClickTab(tab) },
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedTextColor = Color.Black,
                    selectedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    disabledIconColor = Color.DarkGray,
                    disabledTextColor = Color.DarkGray
                ),
                icon = {
                    Icon(
                        painter = painterResource(id = tab.icon),
                        contentDescription = tab.title
                    )
                },
                label = {
                    Text(
                        text = tab.title,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                alwaysShowLabel = true
            )
        }
    }
}