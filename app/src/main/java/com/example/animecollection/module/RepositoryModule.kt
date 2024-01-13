package com.example.animecollection.module

import com.example.animecollection.data.remote.RemoteDatasources
import com.example.animecollection.data.repository.AnimeDatabaseRepositoryImpl
import com.example.animecollection.domain.repository.IAnimeDatabaseRepository
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
    fun providesAnimeDatabaseRepositoryImpl(remoteDatasources: RemoteDatasources): IAnimeDatabaseRepository =
        AnimeDatabaseRepositoryImpl(remoteDatasources)
}