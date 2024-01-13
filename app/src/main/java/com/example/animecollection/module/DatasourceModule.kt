package com.example.animecollection.module

import com.example.animecollection.data.remote.ApiService
import com.example.animecollection.data.remote.RemoteDatasources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {
    @Provides
    @Singleton
    fun providesRemoteDatasources(apiService: ApiService) = RemoteDatasources(apiService)
}