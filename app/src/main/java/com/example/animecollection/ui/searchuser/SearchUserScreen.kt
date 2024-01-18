package com.example.animecollection.ui.searchuser

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.animecollection.core.navigation.BottomNavGraph
import com.example.animecollection.core.navigation.RootNavigator
import com.example.animecollection.domain.model.User
import com.example.animecollection.ui.component.CompErrorMessage
import com.example.animecollection.ui.component.CompListAnime
import com.example.animecollection.ui.component.CompLoadingAnimation
import com.example.animecollection.ui.component.CompSearchBar
import com.example.animecollection.ui.destinations.ProfileWithArgumentScreenDestination
import com.google.firebase.storage.FirebaseStorage
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.tasks.await

@BottomNavGraph
@Destination
@Composable
fun SearchUserScreen(
    navigator: RootNavigator,
    viewModel: SearchUserViewModel = hiltViewModel(),
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            val state = viewModel.state.collectAsState().value

            SearchUserContent(
                listData = state.result,
                errorMessage = state.errorMessage,
                isLoading = state.isLoading,
                query = state.query,
                onSearch = { viewModel.search(it) },
                onSearchTextFieldChanged = { viewModel.onSearchTextFieldValueChanged(it) },
                onCardClick = { navigator.value.navigate(ProfileWithArgumentScreenDestination(it)) }
            )
        }
    }
}

@Composable
fun SearchUserContent(
    listData: List<User>,
    errorMessage: String,
    isLoading: Boolean,
    query: String,
    onSearch: (String) -> Unit,
    onSearchTextFieldChanged: (String) -> Unit,
    onCardClick: (User) -> Unit
) {
    Scaffold {
        Column(
            Modifier.padding(it)
        ) {
            CompSearchBar(
                query = query,
                onQueryChange = onSearchTextFieldChanged,
                onSearch = onSearch
            )
            if (isLoading) CompLoadingAnimation()
            if (errorMessage.isNotEmpty()) CompErrorMessage(message = errorMessage)
            else {
                LazyColumn {
                    items(listData) {
                        CompListUser(
                            modifier = Modifier.clickable { onCardClick(it) },
                            user = it
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CompListUser(
    modifier: Modifier = Modifier,
    user:User
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp, 4.dp)
    ) {
        var url by remember {
            mutableStateOf(Uri.parse(""))
        }

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url.toString())
                .size(Size.ORIGINAL)
                .build()
        )

        LaunchedEffect(Unit) {
            val storage = FirebaseStorage.getInstance().reference
            url = storage.child(user.photo).downloadUrl.await()
        }

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
            Text(modifier = Modifier.weight(0.7f), text = user.username)
        }
    }
}