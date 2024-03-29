package com.example.animecollection.ui.guest.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.animecollection.ui.component.CompLoadingAnimation
import com.example.animecollection.ui.destinations.LoginScreenDestination
import com.example.animecollection.ui.destinations.MainScreenDestination
import com.example.animecollection.ui.destinations.SplashScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.launch

@Destination
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator,
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val state = viewModel.state.collectAsState().value

        if (state.isRegister) {
            navigator.navigate(MainScreenDestination) {
                popUpTo(SplashScreenDestination) {
                    inclusive = true
                }
            }
        }

        RegisterContent(
            nameValue = state.name,
            onNameTextFieldValueChanged = { viewModel.onNameTextFieldValueChanged(it) },
            emailValue = state.email,
            onEmailTextFieldValueChanged = { viewModel.onEmailTextFieldValueChanged(it) },
            passwordValue = state.password,
            onPasswordTextFieldValueChanged = { viewModel.onPasswordTextFieldValueChanged(it) },
            retypePasswordValue = state.retypePassword,
            onRetypePasswordTextFieldValueChanged = { viewModel.onRetypePasswordTextFieldValueChanged(it) },
            onCreateAccountClicked = {
                focusManager.clearFocus()
                viewModel.onRegisterClicked()
            },
            onLoginClicked = { navigator.navigate(LoginScreenDestination) },
            onHideShowPasswordToggled = { viewModel.onHideShowPasswordToggled() },
            isPasswordShown = state.isPasswordShown,
            onHideShowRetypePasswordToggled = { viewModel.onHideShowRetypePasswordToggled() },
            isRetypePasswordShown = state.isRetypePasswordShown,
            isNotEmpty = state.isNotEmpty,
            isLoading = state.isLoading,
            errorMessage = state.errorMessage,
        )
    }
}

@Composable
fun RegisterContent(
    nameValue: String,
    onNameTextFieldValueChanged: (String) -> Unit,
    emailValue: String,
    onEmailTextFieldValueChanged: (String) -> Unit,
    passwordValue: String,
    onPasswordTextFieldValueChanged: (String) -> Unit,
    retypePasswordValue: String,
    onRetypePasswordTextFieldValueChanged: (String) -> Unit,
    onCreateAccountClicked: () -> Unit,
    onLoginClicked: () -> Unit,
    onHideShowPasswordToggled: () -> Unit,
    isPasswordShown: Boolean,
    onHideShowRetypePasswordToggled: () -> Unit,
    isRetypePasswordShown: Boolean,
    isNotEmpty: Boolean,
    isLoading: Boolean,
    errorMessage: String
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = errorMessage) {
        if (errorMessage.isNotEmpty())
            scope.launch {
                snackbarHostState.showSnackbar(errorMessage)
            }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Anime Collection", style = MaterialTheme.typography.headlineMedium) })
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 32.dp),
                text = "Buat Akun",
                style = MaterialTheme.typography.headlineSmall
            )
            CompEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 0.dp, end = 0.dp, bottom = 4.dp),
                value = nameValue,
                label = "Nama",
                leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
                onValueChange = onNameTextFieldValueChanged,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            CompEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 4.dp),
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
                    .padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 4.dp),
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
            CompEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 24.dp),
                value = retypePasswordValue,
                label = "Masukkan Ulang Password",
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
                onValueChange = onRetypePasswordTextFieldValueChanged,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                trailingIcon = { IconButton(onClick = onHideShowRetypePasswordToggled) { Icon(
                    imageVector = if (isRetypePasswordShown) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = null
                ) } },
                visualTransformation = if (isRetypePasswordShown) VisualTransformation.None else PasswordVisualTransformation()

            )

            CompButton(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .fillMaxWidth(),
                onClick = onCreateAccountClicked,
                text = "Buat Akun",
                enabled = isNotEmpty
            )

            if (isLoading)
                CompLoadingAnimation()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Sudah punya akun? ")
                Text(
                    modifier = Modifier.clickable(onClick = onLoginClicked),
                    text = "Masuk"
                )
            }

        }
    }
}