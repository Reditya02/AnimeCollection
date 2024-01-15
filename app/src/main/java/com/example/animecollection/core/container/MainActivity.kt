package com.example.animecollection.core.container

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.animecollection.ui.NavGraphs
import com.example.animecollection.core.theme.AnimeCollectionTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var splasScreen = true

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                splasScreen
            }
        }

        setContent {
            val mainNavController = rememberAnimatedNavController()
            AnimeCollectionTheme(
                darkTheme = viewModel.getIsDarkTheme()
            ) {
                DestinationsNavHost(navGraph = NavGraphs.root, navController = mainNavController)
            }
        }

        lifecycleScope.launch {
            delay(1000)
            splasScreen = false
        }
    }
}