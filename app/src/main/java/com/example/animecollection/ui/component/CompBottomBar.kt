package com.example.animecollection.ui.component

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.animecollection.core.navigation.BottomBarDestination
import com.example.animecollection.ui.NavGraphs
import com.example.animecollection.ui.appCurrentDestinationAsState
import com.example.animecollection.ui.destinations.Destination
import com.example.animecollection.ui.startAppDestination
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popUpTo

@Composable
fun CompBottomBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val currentDestination: Destination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        BottomBarDestination.values().forEach { destination ->
            NavigationBarItem(
                selected = currentDestination == destination.direction,
                onClick = {
                    navController.navigate(destination.direction) {
                        popUpTo(currentDestination) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                icon = { Icon(destination.icon, contentDescription = "")},
                label = { Text(text = destination.text) },
            )
        }
    }
}