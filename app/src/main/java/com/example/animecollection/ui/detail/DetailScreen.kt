package com.example.animecollection.ui.detail

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalView
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
import kotlinx.coroutines.coroutineScope

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
            ) {
                navigator.popBackStack()
            }
        }
    }
}

@Composable
fun DetailContent(
    anime: AnimeDetail,
    message: String,
    isLoading: Boolean,
    character: AnimeCharacterState,
    onBackClick: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    val scrolledAppBar = remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex == 0 }
    }

    Scaffold {
        Box {
            if (isLoading)
                ALoadingAnimation()
            else if (message.isNotEmpty())
                AErrorMessage(message = message)
            else {
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
                        DetailAnime(anime = anime, character = character)
                    }
                }
            }

            CompDetailTopBar(
                onBackClick = { onBackClick() },
                title = anime.titleEn,
                scrolled = scrolledAppBar.value
            )
        }

    }
}

@Composable
fun CompDetailTopBar(
    onBackClick: () -> Unit,
    title: String,
    scrolled: Boolean
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
        Row(
            modifier = Modifier
                .background(if (scrolled1) Color.Transparent else MaterialTheme.colorScheme.primary)
                .fillMaxWidth()
                .padding(8.dp)
                .aspectRatio(8f),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background, CircleShape),
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            if (!scrolled1) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun AnimeTitle(
    anime: AnimeDetail
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
    anime: AnimeDetail,
    character: AnimeCharacterState,
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

fun Modifier.visibilityPercentage(callback: (Float) -> Unit): Modifier {
    return composed {
        val view = LocalView.current.parent as View
        onGloballyPositioned {
            callback(visibilityPercentage(view))
        }
    }
}

fun visibilityPercentage(view: View): Float {
    if (!view.isAttachedToWindow) return 0f
    val rect = Rect()
    if (!view.getLocalVisibleRect(rect)) return 0f
    val totalArea = (view.height * view.width).toFloat()
    if (totalArea == 0f) return 0f
    val visibleArea = (rect.bottom - rect.top) * (rect.right - rect.left)
    return visibleArea / totalArea
}