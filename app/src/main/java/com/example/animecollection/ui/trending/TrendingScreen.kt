package com.example.animecollection.ui.trending

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.ui.component.AErrorMessage
import com.example.animecollection.ui.component.AListAnime
import com.example.animecollection.ui.component.ALoadingAnimation
import com.example.animecollection.ui.component.bottombar.BottomNavGraph
import com.example.animecollection.ui.component.bottombar.RootNavigator
import com.example.animecollection.ui.destinations.DetailScreenDestination
import com.example.animecollection.ui.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@BottomNavGraph(start = true)
@Destination
@Composable
fun TrendingScreen(
    navigator: RootNavigator,
    viewModel: TrendingViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            val state = viewModel.state.collectAsState().value

            TrendingContent(
                listData = state.listAnime,
                message = state.message,
                isLoading = state.isLoading,
                onCardClick = { navigator.value.navigate(DetailScreenDestination(it)) },
                onSettingsClicked = { navigator.value.navigate(SettingsScreenDestination) },

            )
        }
    }
}

@Composable
fun TrendingContent(
    listData: List<Anime>,
    message: String,
    isLoading: Boolean,
    onCardClick: (String) -> Unit,
    onSettingsClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Trending Anime") },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (isLoading)
                ALoadingAnimation()
            else if (listData.isNotEmpty())
                ListTrendingAnime(listData = listData) {
                    onCardClick(it)
                }
            else
                AErrorMessage(message = message)
        }
    }
}

@Composable
fun ListTrendingAnime(
    listData: List<Anime>,
    onCardClick: (String) -> Unit
) {
    LazyColumn(
        content = {
            items(listData) {
                AListAnime(
                    modifier = Modifier.clickable { onCardClick(it.id) },
                    anime = it
                )
            }
        }
    )
}