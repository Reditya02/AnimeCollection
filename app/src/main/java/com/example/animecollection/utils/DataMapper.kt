package com.example.animecollection.utils

import com.example.animecollection.data.remote.response.AnimeDetailResponse
import com.example.animecollection.data.remote.response.AnimeResponse
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.domain.model.AnimeDetail

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

    fun mapAnimeDetailResponseToAnimeDetailLocal(response: AnimeDetailResponse): AnimeDetail =
        response.data.attributes.run {
            AnimeDetail(
                posterImage = posterImage.medium,
                coverImage = coverImage.original,
                titleEn = titles.en,
                titleJp = titles.enJp,
                rating = averageRating,
                synopsis = synopsis,
                genre = listOf()

            )
        }
}