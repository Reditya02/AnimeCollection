package com.example.animecollection.data.repository

import android.util.Log
import com.example.animecollection.core.UIState
import com.example.animecollection.data.remote.RemoteDatasources
import com.example.animecollection.data.remote.response.ApiResponse
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.domain.model.AnimeDetail
import com.example.animecollection.domain.repository.IAnimeDatabaseRepository
import com.example.animecollection.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeDatabaseRepositoryImpl @Inject constructor(
    private val datasources: RemoteDatasources
) : IAnimeDatabaseRepository {
    override fun getAllAnime(): Flow<UIState<List<Anime>>> = flow {
        emit(UIState.Loading())
        when (val response = datasources.getListTrendingAnime().first()) {
            ApiResponse.Empty -> emit(UIState.Error("No Data"))
            is ApiResponse.Error -> emit(UIState.Error(response.message))
            is ApiResponse.Success -> emit(UIState.Success(DataMapper.mapAnimeResponseToAnimeLocal(response.data)))
        }
    }

    override fun getDetailAnime(id: String): Flow<UIState<AnimeDetail>> = flow {
        emit(UIState.Loading())
        when (val response = datasources.getDetailAnime(id).first()) {
            ApiResponse.Empty -> emit(UIState.Error("No Data"))
            is ApiResponse.Error -> emit(UIState.Error(response.message))
            is ApiResponse.Success -> emit(UIState.Success(DataMapper.mapAnimeDetailResponseToAnimeDetailLocal(response.data)))
        }
    }
}