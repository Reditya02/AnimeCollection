package com.example.animecollection.ui.settings

import android.app.AlertDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LogoutAlertDialog(
    onDismissClicked: () -> Unit,
    onConfirmClicked: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissClicked,
        confirmButton = {
            Button(
                onClick = onConfirmClicked
            ) { Text(text = "Iya") }
        },
        dismissButton = {
            Button(
                onClick = onDismissClicked
            ) { Text(text = "Tidak") }
        },
        title = {
            Text(
                text = "Apakah anda ingin keluar?",
                Modifier.padding(8.dp, 8.dp, 8.dp, 32.dp)
            )
        }
    )
}