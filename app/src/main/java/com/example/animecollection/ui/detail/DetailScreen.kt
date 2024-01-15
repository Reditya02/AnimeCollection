package com.example.animecollection.ui.detail

import android.util.Log
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.animecollection.R
import com.example.animecollection.domain.model.AnimeDetail
import com.example.animecollection.ui.component.AErrorMessage
import com.example.animecollection.ui.component.ALoadingAnimation
import com.example.animecollection.ui.detail.model.AnimeCharacterState
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
            val characterState = viewModel.characterState.collectAsState().value

            DetailContent(
                anime = state.anime,
                message = state.message,
                isLoading = state.isLoading,
                character = characterState,
            )
        }
    }
}

@Composable
fun DetailContent(
    anime: AnimeDetail,
    message: String,
    isLoading: Boolean,
    character: AnimeCharacterState,
) {
    Log.d("Reditya", "Detail Content $character")
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

                DetailAnime(anime = anime, character = character)
            }
        }
    }
}

@Composable
fun DetailAnime(
    anime: AnimeDetail,
    character: AnimeCharacterState,
) {
    Log.d("Reditya", "Detail Anime $character")
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
        AnimeCharacter(characterData = character)
    }
}

@Composable
fun AnimeCharacter(
    characterData: AnimeCharacterState
) {
    Log.d("Reditya", "character $characterData")
    characterData.apply {
        Column {
            Text(text = "Character")
            if (isLoading)
                ALoadingAnimation(Modifier.padding(120.dp, 40.dp))
            else if (message.isNotEmpty())
                AErrorMessage(message = message)
            else {
                LazyRow(
                    content = {
                        items(character) {
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
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    modifier = Modifier
                                        .weight(2f)
                                        .padding(4.dp, 6.dp),
                                    text = it.name
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}