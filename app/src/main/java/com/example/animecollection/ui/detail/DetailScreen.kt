package com.example.animecollection.ui.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.animecollection.R
import com.example.animecollection.domain.model.AnimeCharacter
import com.example.animecollection.domain.model.AnimeDetail
import com.example.animecollection.ui.component.AErrorMessage
import com.example.animecollection.ui.component.ALoadingAnimation
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
        if (isLoading)
            ALoadingAnimation()
        else if (message.isNotEmpty())
            AErrorMessage(message = message)
        else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val coverImage = remember {
                    anime.coverImage
                }

                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    model = coverImage,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                DetailAnime(anime = anime)
            }
        }
    }
}

@Composable
fun DetailAnime(
    anime: AnimeDetail
) {
    val posterImage = remember {
        anime.posterImage
    }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {

        Text(
            text = anime.titleEn,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = Modifier
                .padding(start = 8.dp)
                .alpha(0.8f),
            text = anime.titleJp,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .weight(4f)
                    .clip(MaterialTheme.shapes.medium),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                model = posterImage,
                contentDescription = "",
                contentScale = ContentScale.Crop

            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(7f)
            ) {
                FlowRow {
                    anime.genre.forEach {
                        Card(
                            modifier = Modifier
                                .padding(2.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(12.dp, 4.dp),
                                text = it
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 12.dp))
        Text(
            modifier = Modifier
                .padding(start = 8.dp, bottom = 2.dp),
            text = "Synopsis",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(text = anime.synopsis, style = MaterialTheme.typography.bodyMedium)
        LazyRow(
            content = {
                items(anime.characters) {
                    Card(
                        modifier = Modifier
                            .width(120.dp)
                            .height(210.dp)
                            .padding(4.dp)
                    ) {
                        AsyncImage(
                            modifier = Modifier.weight(8f),
                            model = it.image,
                            contentDescription = "",
                            placeholder = painterResource(id = R.drawable.ic_launcher_background),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            modifier = Modifier.weight(2f).padding(4.dp, 6.dp),
                            text = it.name
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewDetailAnime() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            DetailContent(
                anime = AnimeDetail(
                    posterImage = "",
                    coverImage = "",
                    titleEn = "Title en",
                    titleJp = "Title jp",
                    rating = "rating",
                    synopsis = "ini adalah synopsis",
                    genre = listOf("Genre 1", "Genre 2"),
                    characters = listOf(
                        AnimeCharacter(
                            image = "", name = "Char 1"
                        ),
                        AnimeCharacter(
                            image = "", name = "Char 2"
                        ),
                        AnimeCharacter(
                            image = "", name = "Char 3"
                        ),
                    )
                ),
                message = "",
                isLoading = false
            )
        }
    }

}