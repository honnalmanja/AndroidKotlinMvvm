package com.honnalmanja.androidkotlinmvvm.di.core

import com.honnalmanja.androidkotlinmvvm.data.api.TMServices
import com.honnalmanja.androidkotlinmvvm.data.repository.task.impl.TaskRemoteDataSourceImpl
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskRemoteDataSource
import com.honnalmanja.androidkotlinmvvm.data.repository.user.impl.UserRemoteDataSourceImpl
import com.honnalmanja.androidkotlinmvvm.data.repository.user.source.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(tmServices: TMServices) : UserRemoteDataSource {
        return UserRemoteDataSourceImpl(tmServices)
    }

    @Singleton
    @Provides
    fun provideTaskRemoteDataSource(tmServices: TMServices) : TaskRemoteDataSource {
        return TaskRemoteDataSourceImpl(tmServices)
    }

}