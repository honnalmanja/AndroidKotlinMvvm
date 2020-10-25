package com.honnalmanja.androidkotlinmvvm.di

import android.app.Application
import com.honnalmanja.androidkotlinmvvm.model.local.AppPreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Singleton
    @Provides
    fun provideAppPreference(application: Application): AppPreference{
        return AppPreference(application)
    }

}