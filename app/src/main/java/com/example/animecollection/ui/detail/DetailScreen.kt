package com.example.animecollection.ui.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.animecollection.domain.model.AnimeDetail
import com.example.animecollection.ui.component.AErrorMessage
import com.example.animecollection.ui.component.ALoadingAnimation
import com.example.animecollection.ui.trending.ListTrendingAnime
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    viewModel: DetailViewModel = hiltViewModel(),
    id: String
) {
    viewModel.getDetailAnime(id)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            val state = viewModel.state.collectAsState().value

            DetailContent(
                anime = state.anime,
                message = state.message,
                isLoading = state.isLoading
            )
        }
    }
}

@Composable
fun DetailContent(
    anime: AnimeDetail,
    message: String,
    isLoading: Boolean,
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (isLoading)
                ALoadingAnimation()
            else if (message.isNotEmpty())
                AErrorMessage(message = message)
            else
                DetailAnime(anime = anime)
        }
    }
}

@Composable
fun DetailAnime(
    anime: AnimeDetail
) {
    Column(
        Modifier.fillMaxWidth()
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(anime.posterImage)
                .size(Size.ORIGINAL)
                .build()
        )

        Row(
            Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .weight(4f),
                painter = painter,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(7f)
            ) {
                Text(text = anime.titleEn)

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = anime.titleJp,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        Text(text = anime.synopsis)
    }
}