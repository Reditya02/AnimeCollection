package com.example.animecollection.ui.trending

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.ui.component.CompErrorMessage
import com.example.animecollection.ui.component.CompListAnime
import com.example.animecollection.ui.component.CompLoadingAnimation
import com.example.animecollection.core.navigation.BottomNavGraph
import com.example.animecollection.core.navigation.RootNavigator
import com.example.animecollection.ui.destinations.DetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination

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
                onCardClick = { navigator.value.navigate(DetailScreenDestination(it)) }

            )
        }
    }
}

@Composable
fun TrendingContent(
    listData: List<Anime>,
    message: String,
    isLoading: Boolean,
    onCardClick: (Anime) -> Unit
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
                CompLoadingAnimation()
            else if (listData.isNotEmpty())
                ListTrendingAnime(listData = listData) {
                    onCardClick(it)
                }
            else
                CompErrorMessage(message = message)
        }
    }
}

@Composable
fun ListTrendingAnime(
    listData: List<Anime>,
    onCardClick: (Anime) -> Unit
) {
    LazyColumn(
        content = {
            items(listData) {
                CompListAnime(
                    modifier = Modifier.clickable { onCardClick(it) },
                    anime = it
                )
            }
        }
    )
}