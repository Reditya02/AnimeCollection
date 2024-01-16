package com.example.animecollection.module

import com.example.animecollection.data.repository.AnimeDatabaseRepositoryImpl
import com.example.animecollection.domain.repository.IAnimeDatabaseRepository
import com.example.animecollection.domain.repository.ILocaleRepository
import com.example.animecollection.domain.usecase.GetAllAnimeUseCase
import com.example.animecollection.domain.usecase.GetDetailAnimeUseCase
import com.example.animecollection.domain.usecase.GetSearchedAnimeUseCase
import com.example.animecollection.domain.usecase.theme.ChangeThemeUseCase
import com.example.animecollection.domain.usecase.theme.GetIsDarkThemeUseCase
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
    fun providesGetAllAnimeUseCase(repository: IAnimeDatabaseRepository) =
        GetAllAnimeUseCase(repository)

    @Provides
    @Singleton
    fun providesGetDetailAnimeUseCase(repository: IAnimeDatabaseRepository) =
        GetDetailAnimeUseCase(repository)

    @Provides
    @Singleton
    fun providesChangeThemeUseCase(repository: ILocaleRepository) =
        ChangeThemeUseCase(repository)

    @Provides
    @Singleton
    fun providesGetThemeUseCase(repository: ILocaleRepository) =
        GetIsDarkThemeUseCase(repository)

    @Provides
    @Singleton
    fun providesGetSearchedAnimeUseCase(repository: IAnimeDatabaseRepository) =
        GetSearchedAnimeUseCase(repository)
}