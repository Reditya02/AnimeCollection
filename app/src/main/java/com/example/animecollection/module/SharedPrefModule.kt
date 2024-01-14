package com.example.animecollection.module

import android.content.Context
import android.content.SharedPreferences
import com.example.animecollection.data.locale.SharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {
    private const val SHARED_PREF = "shared_pref"

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context) =
        context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideSharedPref(sharedPref: SharedPreferences) =
        SharedPref(sharedPref)
}