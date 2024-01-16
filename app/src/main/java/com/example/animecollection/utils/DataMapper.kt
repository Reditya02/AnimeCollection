package com.example.animecollection.utils

import com.example.animecollection.data.remote.response.AnimeDetailResponse
import com.example.animecollection.data.remote.response.AnimeGenreResponse
import com.example.animecollection.data.remote.response.AnimeResponse
import com.example.animecollection.data.remote.response.SearchedAnimeResponse
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.domain.model.AnimeCharacter
import com.example.animecollection.domain.model.AnimeDetail

object DataMapper {
    fun mapAnimeResponseToAnimeLocal(listData: AnimeResponse): List<Anime> =
        listData.data.map { result ->
            val anime = result.attributes
            Anime(
                id = result.id,
                image = anime.posterImage.tiny,
                title = anime.canonicalTitle,
                rating = anime.averageRating
            )
        }

    fun mapSearchedAnimeResponseToAnimeLocal(listData: SearchedAnimeResponse): List<Anime> =
        listData.data.map { result ->
            val anime = result.attributes
            Anime(
                id = result.id,
                image = anime.posterImage.tiny,
                title = anime.canonicalTitle,
                rating = anime.averageRating ?: "0"
            )
        }

    fun mapAnimeDetailResponseToAnimeDetailLocal(
        response: AnimeDetailResponse,
        genre: AnimeGenreResponse
    ): AnimeDetail =
        response.data.attributes.run {
            AnimeDetail(
                posterImage = posterImage.tiny ?: "",
                coverImage = coverImage.tiny ?: "",
                titleEn = titles.en ?: titles.enJp,
                titleJp = titles.enJp ?: "",
                rating = averageRating ?: "",
                synopsis = synopsis ?: "",
                genre = mapGenreResponse(genre)
            )
        }

    fun mapGenreResponse(response: AnimeGenreResponse): List<String> =
        response.data.map { result ->
            result.attributes.name
        }
}