package com.example.animecollection.ui.settings

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animecollection.ui.component.CompButton
import com.example.animecollection.core.navigation.BottomNavGraph
import com.example.animecollection.core.navigation.RootNavigator
import com.example.animecollection.ui.destinations.ChangeNameScreenDestination
import com.example.animecollection.ui.destinations.LoginScreenDestination
import com.example.animecollection.ui.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.launch

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

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            viewModel.uploadImage(uri)
        }
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
                onChangeNameClicked = { navigator.value.navigate(ChangeNameScreenDestination) },
                onChangePhotoClicked = { galleryLauncher.launch("image/*") }
            )
            if (isOpenDialog) {
                LogoutAlertDialog(
                    onDismissClicked = { isOpenDialog = false },
                    onConfirmClicked = {
                        viewModel.logout()
                        navigator.value.navigate(LoginScreenDestination) {
                            popUpTo(SettingsScreenDestination) {
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
    onLogoutClicked: () -> Unit,
    onChangeNameClicked: () -> Unit,
    onChangePhotoClicked: () -> Unit
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
            Row(
                Modifier
                    .padding(12.dp)
                    .clickable { onChangeNameClicked() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Change username")
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "")
            }
            Row(
                Modifier
                    .padding(12.dp)
                    .clickable { onChangePhotoClicked() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Change user photo")
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "")
            }
            Spacer(modifier = Modifier.weight(1f))
            CompButton(modifier = Modifier.fillMaxWidth(), onClick = onLogoutClicked, text = "Logout")
        }
    }
}