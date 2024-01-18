package com.example.animecollection.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.animecollection.ui.destinations.*
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    val text: String
) {
    Trending(TrendingScreenDestination, Icons.Default.LocalFireDepartment, "Trending"),
    Search(SearchScreenDestination, Icons.Default.Search, "Search"),
    SearchUser(SearchUserScreenDestination, Icons.Default.SearchOff, "Search User"),
    Settings(SettingsScreenDestination, Icons.Default.Settings, "Settings"),
    Profile(ProfileScreenDestination, Icons.Default.Person, "Profile")
}