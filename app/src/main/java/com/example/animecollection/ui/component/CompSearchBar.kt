package com.example.animecollection.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animecollection.core.theme.AnimeCollectionTheme

@Composable
fun CompSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    text: String
) {
    val queryValue = remember {
        mutableSetOf(query)
    }

    OutlinedTextField(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        value = query,
        onValueChange = onQueryChange,
        trailingIcon = {
            IconButton(onClick = { onSearch(query) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        },
        placeholder = {
            Text(if (queryValue.isEmpty()) text else "")
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions{ onSearch(query) }
    )
}

@Preview
@Composable
fun CompSearchBarPreview() {
    AnimeCollectionTheme {
        Surface {
            CompSearchBar(query = "query", onQueryChange = { }, onSearch = {}, text = "search")
        }
    }
}