package com.example.animecollection.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.animecollection.domain.model.Anime


@Composable
fun CompListAnime(
    modifier: Modifier = Modifier,
    anime: Anime
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp, 4.dp),
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(anime.posterImage)
                .size(Size.ORIGINAL)
                .build()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .weight(0.3f)
                    .aspectRatio(1f),
                painter = painter,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(0.7f)
            ) {
                Text(text = anime.titleEn)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "")
                    Text(text = anime.rating)
                }
            }
        }
    }
}