package com.example.animecollection.module

import com.example.animecollection.data.repository.AnimeDatabaseRepositoryImpl
import com.example.animecollection.domain.usecase.GetAllAnimeUseCase
import com.example.animecollection.domain.usecase.GetDetailAnimeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun providesGetAllAnimeUseCase(repositoryImpl: AnimeDatabaseRepositoryImpl) =
        GetAllAnimeUseCase(repositoryImpl)

    @Provides
    @Singleton
    fun providesGetDetailAnimeUseCase(repositoryImpl: AnimeDatabaseRepositoryImpl) =
        GetDetailAnimeUseCase(repositoryImpl)
}