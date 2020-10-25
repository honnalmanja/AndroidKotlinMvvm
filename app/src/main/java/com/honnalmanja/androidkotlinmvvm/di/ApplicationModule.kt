package com.honnalmanja.androidkotlinmvvm.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application{
        return application
    }

}