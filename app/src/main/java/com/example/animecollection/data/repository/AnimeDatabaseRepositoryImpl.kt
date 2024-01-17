package com.example.animecollection.data.repository

import com.example.animecollection.core.UIState
import com.example.animecollection.data.remote.api.RemoteApiDatasources
import com.example.animecollection.data.remote.api.response.ApiResponse
import com.example.animecollection.domain.model.AnimeCharacter
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.domain.repository.IAnimeDatabaseRepository
import com.example.animecollection.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeDatabaseRepositoryImpl @Inject constructor(
    private val datasources: RemoteApiDatasources
) : IAnimeDatabaseRepository {
    override fun getAllAnime(): Flow<UIState<List<Anime>>> = flow {
        emit(UIState.Loading())
        when (val response = datasources.getListTrendingAnime().first()) {
            ApiResponse.Empty -> emit(UIState.Error("No Data"))
            is ApiResponse.Error -> emit(UIState.Error(response.message))
            is ApiResponse.Success -> emit(
                UIState.Success(
                    DataMapper.mapAnimeResponseToAnimeLocal(
                        response.data
                    )
                )
            )
        }
    }

    override fun getAnimeCharacter(id: String): Flow<UIState<List<AnimeCharacter>>> = flow {
        val result: MutableList<AnimeCharacter> = mutableListOf()
        var isResponsevalid = false
        val characterId = datasources.getCharacterId(id).first()
        if (characterId is ApiResponse.Success) {
            characterId.data.data.take(6).map {
                when (val char = datasources.getCharacter(it.id).first()) {
                    ApiResponse.Empty -> emit(UIState.Error("No Data"))
                    is ApiResponse.Error -> emit(UIState.Error("Error"))
                    is ApiResponse.Success -> {
                        isResponsevalid = true
                        val charData = char.data.data.attributes
                        result.add(
                            AnimeCharacter(
                                image = charData.image.tiny,
                                name = charData.canonicalName
                            )
                        )
                    }
                }
            }
            if (isResponsevalid)
                emit(UIState.Success(result))
        }
    }

    override fun searchAnime(query: String): Flow<UIState<List<Anime>>> = flow {
        emit(UIState.Loading())
        when (val response = datasources.getSearchedAnime(query).first()) {
            ApiResponse.Empty -> emit(UIState.Error("No Data"))
            is ApiResponse.Error -> emit(UIState.Error(response.message))
            is ApiResponse.Success -> emit(
                UIState.Success(
                    DataMapper.mapSearchedAnimeResponseToAnimeLocal(
                        response.data
                    )
                )
            )
        }
    }

    override fun getGenre(id: String): Flow<UIState<List<String>>> = flow {
        emit(UIState.Loading())
        when (val response = datasources.getGenre(id).first()) {
            ApiResponse.Empty -> emit(UIState.Error("No Data"))
            is ApiResponse.Error -> emit(UIState.Error(response.message))
            is ApiResponse.Success -> emit(
                UIState.Success(response.data.data.map { it.attributes.name })
            )
        }
    }
}