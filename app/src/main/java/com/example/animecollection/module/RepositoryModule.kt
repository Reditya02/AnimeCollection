package com.example.animecollection.module

import com.example.animecollection.data.locale.LocaleDatasources
import com.example.animecollection.data.remote.api.RemoteApiDatasources
import com.example.animecollection.data.remote.firebase.RemoteFirebaseDatasources
import com.example.animecollection.data.repository.AnimeDatabaseRepositoryImpl
import com.example.animecollection.data.repository.LocaleRepositoryImpl
import com.example.animecollection.data.repository.UserRepositoryImpl
import com.example.animecollection.domain.repository.IAnimeDatabaseRepository
import com.example.animecollection.domain.repository.ILocaleRepository
import com.example.animecollection.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesAnimeDatabaseRepositoryImpl(remoteApiDatasources: RemoteApiDatasources): IAnimeDatabaseRepository =
        AnimeDatabaseRepositoryImpl(remoteApiDatasources)

    @Provides
    @Singleton
    fun providesLocaleRepositoryImpl(localeDatasources: LocaleDatasources): ILocaleRepository =
        LocaleRepositoryImpl(localeDatasources)

    @Provides
    @Singleton
    fun providesUserRepositoryImpl(firebaseDatasources: RemoteFirebaseDatasources): IUserRepository =
        UserRepositoryImpl(firebaseDatasources)
}