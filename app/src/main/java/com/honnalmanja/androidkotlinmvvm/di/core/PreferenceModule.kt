package com.honnalmanja.androidkotlinmvvm.di.core

import android.content.Context
import com.honnalmanja.androidkotlinmvvm.data.local.prefs.AppPreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Singleton
    @Provides
    fun provideAppPreference(context: Context): AppPreference {
        return AppPreference(context.applicationContext)
    }

}