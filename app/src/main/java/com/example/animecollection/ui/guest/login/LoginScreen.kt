package com.example.animecollection.ui.guest.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animecollection.ui.component.CompButton
import com.example.animecollection.ui.component.CompEditText
import com.example.animecollection.ui.destinations.MainScreenDestination
import com.example.animecollection.ui.destinations.RegisterScreenDestination
import com.example.animecollection.ui.destinations.SplashScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            val state = viewModel.state.collectAsState().value

            if (state.isLogin) {
                navigator.navigate(MainScreenDestination) {
                    popUpTo(SplashScreenDestination) {
                        inclusive = true
                    }
                }
            }

            LoginContent(
                emailValue = state.email,
                onEmailTextFieldValueChanged = { viewModel.onEmailTextFieldValueChanged(it) },
                passwordValue = state.password,
                onPasswordTextFieldValueChanged = { viewModel.onPasswordTextFieldValueChanged(it) },
                onLoginClicked = {
                    focusManager.clearFocus()
                    viewModel.onLoginClicked()
                },
                onRegisterClicked = { navigator.navigate(RegisterScreenDestination) },
                onHideShowPasswordToggled = { viewModel.onHideShowPasswordToggled() },
                isPasswordShown = state.isPasswordShown,
                isNotEmpty = state.isNotEmpty,
                isLoading = state.isLoading,
                errorMessage = state.errorMessage,
            )
        }
    }
}

@Composable
fun LoginContent(
    emailValue: String,
    onEmailTextFieldValueChanged: (String) -> Unit,
    passwordValue: String,
    onPasswordTextFieldValueChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    onHideShowPasswordToggled: () -> Unit,
    isPasswordShown: Boolean,
    isNotEmpty: Boolean,
    isLoading: Boolean,
    errorMessage: String
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Anime Collection", style = MaterialTheme.typography.headlineMedium) })
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 32.dp),
                text = "Masuk",
                style = MaterialTheme.typography.headlineSmall
            )
            CompEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 0.dp, end = 0.dp, bottom = 4.dp),
                value = emailValue,
                label = "Email",
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") },
                onValueChange = onEmailTextFieldValueChanged,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            CompEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 24.dp),
                value = passwordValue,
                label = "Password",
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
                onValueChange = onPasswordTextFieldValueChanged,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                trailingIcon = { IconButton(onClick = onHideShowPasswordToggled) { Icon(
                    imageVector = if (isPasswordShown) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = null
                ) } },
                visualTransformation = if (isPasswordShown) VisualTransformation.None else PasswordVisualTransformation()

            )
            CompButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onLoginClicked ,
                text = "Masuk",
                enabled = isNotEmpty
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Belum punya akun? ")
                Text(
                    modifier = Modifier.clickable(onClick = onRegisterClicked),
                    text = "Buat akun"
                )
            }

        }
    }
}