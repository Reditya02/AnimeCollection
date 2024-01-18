package com.example.animecollection.ui.changename

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animecollection.core.navigation.RootNavigator
import com.example.animecollection.ui.component.CompButton
import com.example.animecollection.ui.component.CompEditText
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ChangeNameScreen(
    navigator: DestinationsNavigator,
    viewModel: ChangeNameViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val state = viewModel.state.collectAsState().value
        ChangeNameContent(name = state, onTextFieldValueChanged = { viewModel.onTextFieldValueChanged(it) }) {
            viewModel.changeName(state)
            navigator.popBackStack()
        }
    }
}

@Composable
fun ChangeNameContent(
    name: String,
    onTextFieldValueChanged: (String) -> Unit,
    onSaveClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        CompEditText(
            value = name,
            label = "name",
            onValueChange = onTextFieldValueChanged,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        CompButton(modifier = Modifier.fillMaxWidth(), onClick = onSaveClicked, text ="Save")
    }
}