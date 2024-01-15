package com.example.animecollection.data.remote

import com.example.animecollection.data.remote.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("trending/anime")
    suspend fun getListTrendingAnime(): Response<AnimeResponse>

    @GET("anime/{id}")
    suspend fun getDetailAnime(
        @Path("id") id: String
    ): Response<AnimeDetailResponse>

    @GET("anime/{id}/genres")
    suspend fun getGenre(
        @Path("id") id: String
    ): Response<AnimeGenreResponse>

    @GET("anime/{id}/relationships/characters")
    suspend fun getCharacterId(
        @Path("id") id: String
    ): Response<AnimeCharacterIdResponse>

    @GET("media-characters/{id}/character")
    suspend fun getCharacter(
        @Path("id") id: String
    ): Response<AnimeCharacterResponse>
}