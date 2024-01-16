package com.example.animecollection.utils

import com.example.animecollection.data.remote.response.AnimeDetailResponse
import com.example.animecollection.data.remote.response.AnimeGenreResponse
import com.example.animecollection.data.remote.response.AnimeResponse
import com.example.animecollection.data.remote.response.SearchedAnimeResponse
import com.example.animecollection.domain.model.Anime

object DataMapper {
    fun mapAnimeResponseToAnimeLocal(listData: AnimeResponse): List<Anime> =
        listData.data.map { result ->
            val anime = result.attributes
            Anime(
                id = result.id,
                posterImage = anime.posterImage.run { small ?: original ?: tiny ?: large ?: ""},
                coverImage = anime.coverImage?.run { small ?: original ?: tiny ?: large ?: ""} ?: "",
                titleEn = anime.titles.run { en ?: enJp ?: jaJp ?: "" },
                titleJp = anime.titles.run { jaJp ?: enJp ?: "" },
                rating = anime.averageRating ?: "-",
                synopsis = anime.synopsis,
                genre = listOf()
            )
        }

    fun mapSearchedAnimeResponseToAnimeLocal(listData: SearchedAnimeResponse): List<Anime> =
        listData.data.map { result ->
            val anime = result.attributes
            Anime(
                id = result.id,
                posterImage = anime.posterImage.run { small ?: original ?: tiny ?: large ?: ""},
                coverImage = anime.coverImage?.run { small ?: original ?: tiny ?: large ?: ""} ?: "",
                titleEn = anime.titles.run { en ?: enJp ?: jaJp ?: "" },
                titleJp = anime.titles.run { jaJp ?: enJp ?: "" },
                rating = anime.averageRating ?: "-",
                synopsis = anime.synopsis,
                genre = listOf()
            )
        }

    fun mapAnimeDetailResponseToAnimeDetailLocal(
        response: AnimeDetailResponse,
        genre: AnimeGenreResponse
    ): Anime =
        response.data.attributes.run {
            Anime(
                id = response.data.id,
                posterImage = posterImage.tiny ?: "",
                coverImage = coverImage?.tiny ?: "",
                titleEn = titles.en ?: titles.enJp ?: "",
                titleJp = titles.enJp ?: "",
                rating = averageRating ?: "",
                synopsis = synopsis,
                genre = mapGenreResponse(genre)
            )
        }

    fun mapGenreResponse(response: AnimeGenreResponse): List<String> =
        response.data.map { result ->
            result.attributes.name
        }
}