package com.example.animecollection.utils

import com.example.animecollection.data.remote.response.AnimeDetailResponse
import com.example.animecollection.data.remote.response.AnimeGenreResponse
import com.example.animecollection.data.remote.response.AnimeResponse
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

    fun mapAnimeDetailResponseToAnimeDetailLocal(
        response: AnimeDetailResponse,
        genre: AnimeGenreResponse,
        characters: List<AnimeCharacter>
    ): AnimeDetail =
        response.data.attributes.run {
            AnimeDetail(
                posterImage = posterImage.tiny,
                coverImage = coverImage.tiny,
                titleEn = titles.en,
                titleJp = titles.enJp,
                rating = averageRating,
                synopsis = synopsis,
                genre = mapGenreResponse(genre),
                characters = characters
            )
        }

    fun mapGenreResponse(response: AnimeGenreResponse): List<String> =
        response.data.map { result ->
            result.attributes.name
        }
}