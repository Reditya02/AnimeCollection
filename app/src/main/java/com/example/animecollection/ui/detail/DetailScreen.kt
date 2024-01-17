package com.example.animecollection.ui.detail

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
                onFavoriteClicked = { viewModel.addFavorite(it) },
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
                    AnimeTitle(anime = anime)
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
                        text = title,
                    )
                }
            },
            actions = {
                IconButton(onClick = { onFavoriteClicked() }) {
                    Icon(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary, CircleShape)
                            .padding(2.dp),
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = if (isFavorite) Color.Yellow else Color.White
                    )
                }
            }
        )
    }
}

@Composable
fun AnimeTitle(
    anime: Anime
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
                    .clip(MaterialTheme.shapes.medium),
                model = posterImage,
                contentDescription = "",
                contentScale = ContentScale.Crop

            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(7f)
            ) {
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
    characterData.apply {
        Column {
            Text(text = "Character")
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
}