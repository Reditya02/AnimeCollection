package com.example.animecollection.ui

import android.app.appsearch.AppSearchManager.SearchContext
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
import androidx.lifecycle.ViewModel
import com.example.animecollection.core.UIState
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.ui.component.AErrorMessage
import com.example.animecollection.ui.component.AListAnime
import com.example.animecollection.ui.component.ALoadingAnimation
import com.example.animecollection.ui.component.CompSearchBar
import com.example.animecollection.ui.component.bottombar.BottomNavGraph
import com.example.animecollection.ui.component.bottombar.RootNavigator
import com.example.animecollection.ui.search.SearchViewModel
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
                onSearchTextFieldChanged = { viewModel.onSearchtextFieldvalueChanged(it) }
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
    onSearchTextFieldChanged: (String) -> Unit
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
            if (isLoading) ALoadingAnimation()
            if (errorMessage.isNotEmpty()) AErrorMessage(message = errorMessage)
            else {
                LazyColumn {
                    items(listData) {
                        AListAnime(anime = it)
                    }
                }
            }
        }
    }
}