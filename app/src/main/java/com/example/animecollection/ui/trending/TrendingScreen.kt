package com.example.animecollection.ui.trending

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.animecollection.R
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.ui.component.AErrorMessage
import com.example.animecollection.ui.component.AListAnime
import com.example.animecollection.ui.component.ALoadingAnimation
import com.example.animecollection.ui.destinations.DetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun TrendingScreen(
    navigator: DestinationsNavigator,
    viewModel: TrendingViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            TrendingContent(
                listData = state.listAnime,
                message = state.message,
                isLoading = state.isLoading
            ) {
                navigator.navigate(DetailScreenDestination)
            }
        }
    }
}

@Composable
fun TrendingContent(
    listData: List<Anime>,
    message: String,
    isLoading: Boolean,
    onCardClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Trending Anime") }
            )
        },
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
                    onCardClick()
                }
            else
                AErrorMessage(message = message)
        }
    }
}

@Composable
fun ListTrendingAnime(
    listData: List<Anime>,
    onCardClick: () -> Unit
) {
    LazyColumn(
        content = {
            items(listData) {
                AListAnime(
                    modifier = Modifier.clickable { onCardClick() },
                    anime = it
                )
            }
        }
    )
}