package com.example.animecollection.ui.component.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.animecollection.ui.destinations.SearchScreenDestination
import com.example.animecollection.ui.destinations.SettingsScreenDestination
import com.example.animecollection.ui.destinations.TrendingScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    val text: String
) {
    Trending(TrendingScreenDestination, Icons.Default.LocalFireDepartment, "Trending"),
    Search(SearchScreenDestination, Icons.Default.Search, "Search"),
    Settings(SettingsScreenDestination, Icons.Default.Settings, "Settings")
}