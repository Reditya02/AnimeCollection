package com.example.animecollection.module

import com.example.animecollection.domain.repository.IAnimeDatabaseRepository
import com.example.animecollection.domain.repository.ILocaleRepository
import com.example.animecollection.domain.repository.IUserRepository
import com.example.animecollection.domain.usecase.*
import com.example.animecollection.domain.usecase.theme.ChangeThemeUseCase
import com.example.animecollection.domain.usecase.theme.GetIsDarkThemeUseCase
import com.example.animecollection.domain.usecase.auth.GetUidUseCase
import com.example.animecollection.domain.usecase.auth.LoginUseCase
import com.example.animecollection.domain.usecase.auth.LogoutUseCase
import com.example.animecollection.domain.usecase.auth.RegisterUseCase
import com.example.animecollection.domain.usecase.favorite.AddFavoriteUseCase
import com.example.animecollection.domain.usecase.favorite.CheckIsFavoriteUseCase
import com.example.animecollection.domain.usecase.favorite.GetAllFavoriteUseCase
import com.example.animecollection.domain.usecase.favorite.RemoveFavoriteUseCase
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

    @Provides
    @Singleton
    fun providesGetUidUseCase(repository: IUserRepository) =
        GetUidUseCase(repository)

    @Provides
    @Singleton
    fun providesLogoutUseCase(repository: IUserRepository) =
        LogoutUseCase(repository)

    @Provides
    @Singleton
    fun providesAddFavoriteUseCase(repository: IUserRepository) =
        AddFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun providesCheckIsFavoriteUseCase(repository: IUserRepository) =
        CheckIsFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun providesGetAllFavoriteUseCase(repository: IUserRepository) =
        GetAllFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun providesRemoveFavorite(repository: IUserRepository) =
        RemoveFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun providesSearhUserUseCase(repository: IUserRepository) =
        SearchUserUseCase(repository)

}