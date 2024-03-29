package com.example.animecollection.ui.profile

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size.Companion.ORIGINAL
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.animecollection.R
import com.example.animecollection.core.navigation.BottomNavGraph
import com.example.animecollection.core.navigation.RootNavigator
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.domain.model.User
import com.example.animecollection.ui.destinations.DetailScreenDestination
import com.example.animecollection.ui.trending.ListTrendingAnime
import com.google.firebase.storage.FirebaseStorage
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.tasks.await

@BottomNavGraph
@Destination
@Composable
fun ProfileScreen(
    navigator: RootNavigator,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    viewModel.getListFavorite(null)
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Profile") }
                )
            }
        ) {
            val state = viewModel.state.collectAsState().value

            ProfileContent(
                modifier = Modifier.padding(it),
                isLoading = state.isLoading,
                userData = state.data,
                lisData = state.listFavorite,
                onCardClick = { navigator.value.navigate(DetailScreenDestination(it)) },
            )
        }

    }
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    userData: User,
    lisData: List<Anime>,
    onCardClick: (Anime) -> Unit
) {
    Column(modifier) {
        UserProfileContent(
            isLoading = isLoading,
            userData = userData
        )
        Text(
            modifier = Modifier
                .padding(start = 8.dp, bottom = 2.dp, top = 16.dp)
                .fillMaxWidth(),
            text = "Favorite",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
        ListFavorite(
            listData = lisData,
            onCardClick = { onCardClick(it) }
        )
    }
}

@Composable
fun UserProfileContent(
    isLoading: Boolean,
    userData: User,
) {
    if (!isLoading) {
        Column {
            Row {
                var url by remember {
                    mutableStateOf(Uri.parse(""))
                }

                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(url.toString())
                        .size(ORIGINAL)
                        .build()
                )

                LaunchedEffect(Unit) {
                    val storage = FirebaseStorage.getInstance().reference
                    url = storage.child(userData.photo).downloadUrl.await()
                }

                Spacer(modifier = Modifier.weight(0.3f))
                if (painter.state is AsyncImagePainter.State.Success) {
                    Image(
                        modifier = Modifier
                            .clip(CircleShape)
                            .weight(0.4f)
                            .aspectRatio(1f),
                        painter = painter,
                        contentDescription = "description",
                        contentScale = ContentScale.Crop
                    )
                } else {
                    val composition by rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(R.raw.anim_loading)
                    )

                    LottieAnimation(
                        modifier = Modifier
                            .weight(0.4f)
                            .aspectRatio(1f)
                            .background(White, CircleShape),
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        contentScale = ContentScale.Fit
                    )
                }
                Spacer(modifier = Modifier.weight(0.3f))
            }
            Spacer(Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = userData.username,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = userData.email,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ListFavorite(
    listData: List<Anime>,
    onCardClick: (Anime) -> Unit
) {
    ListTrendingAnime(listData = listData) {
        onCardClick(it)
    }
}