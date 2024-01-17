package com.example.animecollection.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.ui.component.CompErrorMessage
import com.example.animecollection.ui.component.CompListAnime
import com.example.animecollection.ui.component.CompLoadingAnimation
import com.example.animecollection.ui.component.CompSearchBar
import com.example.animecollection.core.navigation.BottomNavGraph
import com.example.animecollection.core.navigation.RootNavigator
import com.example.animecollection.ui.destinations.DetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination

@BottomNavGraph
@Destination
@Composable
fun SearchScreen(
    navigator: RootNavigator,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            val state = viewModel.state.collectAsState().value

            SearchContent(
                listData = state.result,
                errorMessage = state.errorMessage,
                isLoading = state.isLoading,
                query = state.query,
                onSearch = { viewModel.search(it) },
                onSearchTextFieldChanged = { viewModel.onSearchtextFieldvalueChanged(it) },
                onCardClick = { navigator.value.navigate(DetailScreenDestination(it)) }
            )
        }
    }
}

@Composable
fun SearchContent(
    listData: List<Anime>,
    errorMessage: String,
    isLoading: Boolean,
    query: String,
    onSearch: (String) -> Unit,
    onSearchTextFieldChanged: (String) -> Unit,
    onCardClick: (Anime) -> Unit
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
                        CompListAnime(
                            modifier = Modifier.clickable { onCardClick(it) },
                            anime = it
                        )
                    }
                }
            }
        }
    }
}