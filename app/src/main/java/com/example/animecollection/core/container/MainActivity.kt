package com.example.animecollection.core.container

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.animecollection.core.theme.AnimeCollectionTheme
import com.example.animecollection.ui.NavGraphs
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainNavController = rememberAnimatedNavController()
            AnimeCollectionTheme(
                darkTheme = viewModel.getIsDarkTheme()
            ) {
                DestinationsNavHost(navGraph = NavGraphs.root, navController = mainNavController)
            }
        }
    }
}