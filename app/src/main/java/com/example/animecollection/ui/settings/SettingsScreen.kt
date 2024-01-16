package com.example.animecollection.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animecollection.ui.component.bottombar.BottomNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@BottomNavGraph
@Destination
@Composable
fun SettingsScreen(
    navigator: DestinationsNavigator,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column {
            val state = viewModel.isDarkModeState.collectAsState()
            SettingsContent(
                isDarkTheme = state.value,
                switchChecked = {
                    viewModel.changeTheme()
                }
            )
        }
    }
}

@Composable
fun SettingsContent(
    isDarkTheme: Boolean,
    switchChecked: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Settings") },
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Column {
            Row(
                Modifier
                    .padding(it)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Dark Theme")
                Spacer(modifier = Modifier.weight(1f))
                Switch(checked = isDarkTheme, onCheckedChange = {
                    switchChecked()
                    scope.launch {
                        snackbarHostState.showSnackbar("Buka ulang aplikasi untuk mengubah tema")
                    }
                })
            }
        }
    }
}