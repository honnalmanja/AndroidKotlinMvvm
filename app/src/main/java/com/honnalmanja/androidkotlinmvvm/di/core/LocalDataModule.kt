package com.honnalmanja.androidkotlinmvvm.di.core

import com.honnalmanja.androidkotlinmvvm.data.local.db.TaskDao
import com.honnalmanja.androidkotlinmvvm.data.local.prefs.AppPreference
import com.honnalmanja.androidkotlinmvvm.data.repository.task.impl.TaskLocalDataSourceImpl
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskLocalDataSource
import com.honnalmanja.androidkotlinmvvm.data.repository.user.impl.UserPreferenceDataSourceImpl
import com.honnalmanja.androidkotlinmvvm.data.repository.user.source.UserPreferenceDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun providePreferenceDataSource(appPreference: AppPreference): UserPreferenceDataSource {
        return UserPreferenceDataSourceImpl(appPreference)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(appPreference: AppPreference, taskDao: TaskDao): TaskLocalDataSource {
        return TaskLocalDataSourceImpl(appPreference, taskDao)
    }

}