package com.example.animecollection.utils

import com.example.animecollection.data.remote.api.response.AnimeResponse
import com.example.animecollection.data.remote.api.response.SearchedAnimeResponse
import com.example.animecollection.domain.model.Anime

object DataMapper {
    fun mapAnimeResponseToAnimeLocal(listData: AnimeResponse): List<Anime> =
        listData.data.map { result ->
            val anime = result.attributes
            Anime(
                id = result.id,
                posterImage = anime.posterImage.run { original ?: small ?: tiny ?: large ?: ""},
                coverImage = anime.coverImage?.run { original ?: small ?: tiny ?: large ?: ""} ?: "",
                titleEn = anime.titles.run { en ?: enJp ?: jaJp ?: "" },
                titleJp = anime.titles.run { jaJp ?: enJp ?: "" },
                rating = anime.averageRating ?: "-",
                synopsis = anime.synopsis
            )
        }

    fun mapSearchedAnimeResponseToAnimeLocal(listData: SearchedAnimeResponse): List<Anime> =
        listData.data.map { result ->
            val anime = result.attributes
            Anime(
                id = result.id,
                posterImage = anime.posterImage.run { original ?: small ?: tiny ?: large ?: ""},
                coverImage = anime.coverImage?.run { original ?: small ?: tiny ?: large ?: ""} ?: "",
                titleEn = anime.titles.run { en ?: enJp ?: jaJp ?: "" },
                titleJp = anime.titles.run { jaJp ?: enJp ?: "" },
                rating = anime.averageRating ?: "-",
                synopsis = anime.synopsis
            )
        }
}