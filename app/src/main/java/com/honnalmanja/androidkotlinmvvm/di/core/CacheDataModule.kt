package com.honnalmanja.androidkotlinmvvm.di.core

import com.honnalmanja.androidkotlinmvvm.data.repository.task.impl.TaskCacheDataSourceImpl
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskCacheDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun provideTaskCacheDataSource(): TaskCacheDataSource {
        return TaskCacheDataSourceImpl()
    }

}