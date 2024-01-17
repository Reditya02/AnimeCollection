package com.example.animecollection.module

import com.example.animecollection.domain.repository.IAnimeDatabaseRepository
import com.example.animecollection.domain.repository.ILocaleRepository
import com.example.animecollection.domain.repository.IUserRepository
import com.example.animecollection.domain.usecase.GetAllAnimeUseCase
import com.example.animecollection.domain.usecase.GetGenreUseCase
import com.example.animecollection.domain.usecase.GetSearchedAnimeUseCase
import com.example.animecollection.domain.usecase.theme.ChangeThemeUseCase
import com.example.animecollection.domain.usecase.theme.GetIsDarkThemeUseCase
import com.example.animecollection.domain.usecase.user.LoginUseCase
import com.example.animecollection.domain.usecase.user.RegisterUseCase
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

    @Provides
    @Singleton
    fun providesGetGenreUseCase(repository: IAnimeDatabaseRepository) =
        GetGenreUseCase(repository)

    @Provides
    @Singleton
    fun providesLoginUseCase(repository: IUserRepository) =
        LoginUseCase(repository)

    @Provides
    @Singleton
    fun providesRegisterUseCase(repository: IUserRepository) =
        RegisterUseCase(repository)
}