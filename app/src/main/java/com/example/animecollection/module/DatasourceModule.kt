package com.example.animecollection.module

import com.example.animecollection.data.locale.LocaleDatasources
import com.example.animecollection.data.locale.SharedPref
import com.example.animecollection.data.remote.api.ApiService
import com.example.animecollection.data.remote.api.RemoteApiDatasources
import com.example.animecollection.data.remote.firebase.RemoteFirebaseDatasources
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
    fun providesRemoteApiDatasources(apiService: ApiService) = RemoteApiDatasources(apiService)

    @Provides
    @Singleton
    fun providesLocalseDatasources(pref: SharedPref) = LocaleDatasources(pref)

    @Provides
    @Singleton
    fun providesRemoteFirebaseDatasources() = RemoteFirebaseDatasources()
}