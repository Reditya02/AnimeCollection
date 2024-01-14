package com.example.animecollection.ui.trending

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.ui.component.AListAnime
import com.example.animecollection.ui.destinations.DetailScreenDestination
import com.example.animecollection.ui.theme.AnimeCollectionTheme
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

    Log.d("Reditya S", state.listAnime.toString())

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            TrendingContent(listData = state.listAnime) {
                navigator.navigate(DetailScreenDestination)
            }
        }
    }
}

@Composable
fun TrendingContent(
    listData: List<Anime>,
    onCardClick: () -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
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
    }

}