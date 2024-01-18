package com.example.animecollection.ui.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animecollection.domain.model.User
import com.example.animecollection.ui.destinations.DetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ProfileWithArgumentScreen(
    navigator: DestinationsNavigator,
    viewModel: ProfileViewModel = hiltViewModel(),
    user: User
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Profile") },
                    navigationIcon = { IconButton(onClick = { navigator.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ChevronLeft, contentDescription = "")
                    }}
                )
            }
        ) {
            val state = viewModel.state.collectAsState().value

            ProfileContent(
                modifier = Modifier.padding(it),
                isLoading = state.isLoading,
                userData = user,
                lisData = state.listFavorite,
                onCardClick = { navigator.navigate(DetailScreenDestination(it)) },
            )
        }
    }
}