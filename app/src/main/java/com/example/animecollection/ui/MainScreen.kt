package com.example.animecollection.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.Navigator
import androidx.navigation.compose.rememberNavController
import com.example.animecollection.ui.component.bottombar.BottomBarDestination
import com.example.animecollection.ui.component.bottombar.CompBottomBar
import com.example.animecollection.ui.component.bottombar.RootNavigator
import com.example.animecollection.ui.destinations.TrendingScreenDestination
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.navigation.dependency

@Destination
@Composable
fun MainScreen(
    navigator: DestinationsNavigator
) {
    val navController = rememberAnimatedNavController()

    println("create main")

    Scaffold(
        bottomBar = {
            CompBottomBar(navController = navController)
        }
    ) {
        DestinationsNavHost(
            modifier = Modifier.padding(it),
            navGraph = NavGraphs.bottom,
            navController = navController,
            dependenciesContainerBuilder = {
                dependency(RootNavigator(navigator))
            }
        )
    }
}