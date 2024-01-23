package com.example.animecollection.ui.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.ui.component.CompErrorMessage
import com.example.animecollection.ui.component.CompLoadingAnimation
import com.example.animecollection.ui.detail.model.AnimeCharacterState
import com.example.animecollection.ui.detail.model.GenreState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    viewModel: DetailViewModel = hiltViewModel(),
    anime: Anime
) {
    viewModel.getAnimeCharacter(anime.id)
    viewModel.getGenre(anime.id)
    viewModel.checkIsFavorite(anime.id  )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            val characterState = viewModel.characterState.collectAsState().value
            val genre = viewModel.genreState.collectAsState().value
            val isFavorite = viewModel.isFavorite.collectAsState().value

            DetailContent(
                anime = anime,
                character = characterState,
                genre = genre,
                onBackClick = { navigator.popBackStack() },
                onFavoriteClicked = {
                    if (isFavorite)
                        viewModel.removeFavorite(it.id)
                    else
                        viewModel.addFavorite(it)
                },
                isFavorite = isFavorite
            )
        }
    }
}

@Composable
fun DetailContent(
    anime: Anime,
    character: AnimeCharacterState,
    genre: GenreState,
    onBackClick: () -> Unit,
    onFavoriteClicked: (Anime) -> Unit,
    isFavorite: Boolean
) {
    val lazyListState = rememberLazyListState()
    val scrolledAppBar = remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex == 0 }
    }

    Scaffold {
        Box {
            LazyColumn(
                modifier = Modifier
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                state = lazyListState
            ) {
                item {
                    val coverImage = remember {
                        anime.coverImage
                    }

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(3f),
                        model = coverImage,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
                item {
                    Row(Modifier.fillMaxWidth()) {
                        AnimeTitle(anime = anime)
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = { onFavoriteClicked(anime) }) {
                            Icon(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.primary, CircleShape)
                                    .padding(2.dp),
                                imageVector = if (isFavorite) Icons.Default.Star else Icons.Default.StarBorder,
                                contentDescription = null,
                                tint = if (isFavorite) Color.Yellow else Color.White
                            )
                        }
                    }
                }
                item {
                    DetailAnime(anime = anime, character = character, genre = genre)
                }
            }

            CompDetailTopBar(
                onBackClick = { onBackClick() },
                title = anime.titleEn,
                scrolled = scrolledAppBar.value,
                onFavoriteClicked = { onFavoriteClicked(anime) },
                isFavorite = isFavorite
            )
        }

    }
}

@Composable
fun CompDetailTopBar(
    onBackClick: () -> Unit,
    title: String,
    scrolled: Boolean,
    onFavoriteClicked: () -> Unit,
    isFavorite: Boolean
) {
    AnimatedContent(
        targetState = scrolled,
        transitionSpec = {
            fadeIn(animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessLow,
                visibilityThreshold = null
            )) with
                    fadeOut(animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium,
                        visibilityThreshold = null
                    ))
        },
    ) { scrolled1 ->
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = if (scrolled1)
                    Color.Transparent
                else
                    MaterialTheme.colorScheme.primary
            ),
            navigationIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary, CircleShape)
                            .padding(2.dp),
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            title = {
                if (!scrolled1) {
                    Text(
                        text = title, color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            },
            actions = {
                if (!scrolled1) {
                    IconButton(onClick = { onFavoriteClicked() }) {
                        Icon(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primary, CircleShape)
                                .padding(2.dp),
                            imageVector = if (isFavorite) Icons.Default.Star else Icons.Default.StarBorder,
                            contentDescription = null,
                            tint = if (isFavorite) Color.Yellow else Color.White
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun AnimeTitle(
    anime: Anime
) {
    Column(Modifier.padding(8.dp)) {
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
    }
}

@Composable
fun DetailAnime(
    anime: Anime,
    character: AnimeCharacterState,
    genre: GenreState
) {
    val posterImage = remember {
        anime.posterImage
    }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row(
            Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .weight(4f)
                    .aspectRatio(0.8f)
                    .clip(MaterialTheme.shapes.medium),
                model = posterImage,
                contentDescription = "",
                contentScale = ContentScale.Crop

            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(7f)
            ) {
                Text(text = "Rating: ${anime.rating}")
                FlowRow {
                    genre.genre.forEach {
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
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(
            modifier = Modifier
                .padding(start = 8.dp, bottom = 2.dp),
            text = "Synopsis",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(text = anime.synopsis, style = MaterialTheme.typography.bodyMedium)
        Text(
            modifier = Modifier
                .padding(start = 8.dp, bottom = 2.dp, top = 16.dp),
            text = "Character",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge
        )
        AnimeCharacter(characterData = character)
    }
}

@Composable
fun AnimeCharacter(
    characterData: AnimeCharacterState
) {
    characterData.apply {
        if (isLoading)
            CompLoadingAnimation(Modifier.padding(120.dp, 40.dp))
        else if (message.isNotEmpty())
            CompErrorMessage(message = message)
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