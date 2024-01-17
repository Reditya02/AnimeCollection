package com.example.animecollection.ui.component

import android.util.Size
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Arrangement.Absolute.aligned
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CompButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    text: String
) {
//    var size by remember {
//        mutableStateOf(Size.Zero)
//    }

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        border = border,
        colors = colors,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(contentPadding)
                .align(Alignment.CenterVertically),
            text = text,
            textAlign = TextAlign.Center
        )
    }
}
