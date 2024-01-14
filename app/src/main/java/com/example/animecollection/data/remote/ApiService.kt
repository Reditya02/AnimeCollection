package com.example.animecollection.data.remote

import com.example.animecollection.data.remote.response.AnimeDetailResponse
import com.example.animecollection.data.remote.response.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("trending/anime")
    suspend fun getListTrendingAnime(): Response<AnimeResponse>

    @GET("anime/{id}")
    suspend fun getDetailAnime(
        @Path("id")
        id: String
    ): Response<AnimeDetailResponse>
}