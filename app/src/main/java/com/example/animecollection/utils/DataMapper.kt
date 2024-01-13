package com.example.animecollection.utils

import com.example.animecollection.data.remote.response.AnimeResponse
import com.example.animecollection.domain.model.Anime

object DataMapper {
    fun mapAnimeResponseToAnimeLocal(listData: AnimeResponse): List<Anime> =
        listData.data.map { result ->
            val anime = result.attributes
            Anime(
                id = result.id,
                image = anime.posterImage.medium,
                title = anime.canonicalTitle,
                rating = anime.averageRating
            )
        }
}