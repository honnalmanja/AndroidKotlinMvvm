package com.honnalmanja.androidkotlinmvvm.di.core

import com.honnalmanja.androidkotlinmvvm.data.local.AppPreference
import com.honnalmanja.androidkotlinmvvm.data.repository.user.impl.UserPreferenceDataSourceImpl
import com.honnalmanja.androidkotlinmvvm.data.repository.user.source.UserPreferenceDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceDataModule {

    @Singleton
    @Provides
    fun providePreferenceDataSource(appPreference: AppPreference): UserPreferenceDataSource {
        return UserPreferenceDataSourceImpl(appPreference)
    }

}