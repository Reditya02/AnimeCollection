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

@Composable
fun AListAnime(
    modifier: Modifier = Modifier,
    anime: Anime
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp, 4.dp)
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(anime.image)
                .size(Size.ORIGINAL)
                .build()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .weight(0.3f)
                    .aspectRatio(1f),
                painter = painter,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(0.7f)
            ) {
                Text(text = anime.title)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = anime.rating)

            }
        }
    }
}

val dummyListAnimeData = listOf(
    Anime(
        id = "0",
        image = "https://media.kitsu.io/anime/45857/poster_image/medium-b9c3b3f613b0c12453409ef9ee41d5ec.jpeg",
        title = "JJK",
        rating = "12"
    ),
    Anime(
        id = "0",
        image = "https://media.kitsu.io/anime/45857/poster_image/medium-b9c3b3f613b0c12453409ef9ee41d5ec.jpeg",
        title = "JJK 2",
        rating = "12"
    ),
    Anime(
        id = "0",
        image = "https://media.kitsu.io/anime/45857/poster_image/medium-b9c3b3f613b0c12453409ef9ee41d5ec.jpeg",
        title = "JJK 3",
        rating = "12"
    ),
)