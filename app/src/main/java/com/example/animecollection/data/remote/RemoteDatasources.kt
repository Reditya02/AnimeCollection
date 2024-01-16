package com.example.animecollection.data.remote

import android.util.Log
import com.example.animecollection.data.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDatasources @Inject constructor(
    private val apiService: ApiService
) {
    fun getListTrendingAnime(): Flow<ApiResponse<AnimeResponse>> {
        return flow {
            val response = apiService.getListTrendingAnime()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                if (body.data.isNotEmpty())
                    emit(ApiResponse.Success(body))
                else
                    emit(ApiResponse.Empty)
            } else {
                emit(ApiResponse.Error(response.message()))
            }
        }.catch {
            emit(ApiResponse.Error("Cannot connect to server"))
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailAnime(id: String): Flow<ApiResponse<AnimeDetailResponse>> = flow {
        val response = apiService.getDetailAnime(id)
        val body = response.body()
        if (response.isSuccessful && body != null) {
            emit(ApiResponse.Success(body))
        } else {
            emit(ApiResponse.Error(response.message()))
        }
    }.catch {
        emit(ApiResponse.Error("Cannot connect to server"))
    }.flowOn(Dispatchers.IO)

    fun getGenre(id: String): Flow<ApiResponse<AnimeGenreResponse>> = flow {
        val response = apiService.getGenre(id)
        val body = response.body()
        if (response.isSuccessful && body != null)
            emit(ApiResponse.Success(body))
        else
            emit(ApiResponse.Error(response.message()))
    }.catch {
        emit(ApiResponse.Error("Cannot connect to server"))
    }.flowOn(Dispatchers.IO)

    fun getCharacterId(id: String): Flow<ApiResponse<AnimeCharacterIdResponse>> = flow {
        val response = apiService.getCharacterId(id)
        val body = response.body()
        if (response.isSuccessful && body != null)
            emit(ApiResponse.Success(body))
        else
            emit(ApiResponse.Error(response.message()))
    }.catch {
        emit(ApiResponse.Error("Cannot connect to server"))
    }.flowOn(Dispatchers.IO)

    fun getCharacter(id: String): Flow<ApiResponse<AnimeCharacterResponse>> = flow {
        val response = apiService.getCharacter(id)
        val body = response.body()
        if (response.isSuccessful && body != null)
            emit(ApiResponse.Success(body))
        else
            emit(ApiResponse.Error(response.message()))
    }.catch {
        emit(ApiResponse.Error("Cannot connect to server"))
    }.flowOn(Dispatchers.IO)

    fun getSearchedAnime(query: String): Flow<ApiResponse<SearchedAnimeResponse>> = flow {
        val response = apiService.getSearchedAnime(query)
        val body = response.body()
        if (response.isSuccessful && body != null)
            emit(ApiResponse.Success(body))
        else
            emit(ApiResponse.Error(response.message()))
    }.catch { e ->
        emit(ApiResponse.Error("Cannot connect to server"))
    }.flowOn(Dispatchers.IO)
}