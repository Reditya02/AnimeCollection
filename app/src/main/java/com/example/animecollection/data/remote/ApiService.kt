package com.example.animecollection.data.remote

import com.example.animecollection.data.remote.response.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("trending/anime")
    suspend fun getListTrendingAnime(): Response<AnimeResponse>
}