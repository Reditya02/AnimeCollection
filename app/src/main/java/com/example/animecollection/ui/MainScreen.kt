package com.example.animecollection.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.animecollection.ui.component.CompBottomBar
import com.example.animecollection.core.navigation.RootNavigator
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
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