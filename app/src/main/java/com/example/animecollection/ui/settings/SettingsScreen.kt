package com.example.animecollection.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animecollection.ui.component.CompButton
import com.example.animecollection.ui.component.bottombar.BottomNavGraph
import com.example.animecollection.ui.component.bottombar.RootNavigator
import com.example.animecollection.ui.destinations.LoginScreenDestination
import com.example.animecollection.ui.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.launch
import okhttp3.internal.notifyAll

@BottomNavGraph
@Destination
@Composable
fun SettingsScreen(
    navigator: RootNavigator,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    var isOpenDialog by remember {
        mutableStateOf(false)
    }

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
                },
                onLogoutClicked = { isOpenDialog = true },

            )
            if (isOpenDialog) {
                LogoutAlertDialog(
                    onDismissClicked = { isOpenDialog = false },
                    onConfirmClicked = {
                        viewModel.logout()
                        navigator.value.navigate(LoginScreenDestination) {
                            popUpTo(LoginScreenDestination) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun SettingsContent(
    isDarkTheme: Boolean,
    switchChecked: () -> Unit,
    onLogoutClicked: () -> Unit
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
            Spacer(modifier = Modifier.weight(1f))
            CompButton(modifier = Modifier.fillMaxWidth(), onClick = onLogoutClicked, text = "Keluar")
        }
    }
}