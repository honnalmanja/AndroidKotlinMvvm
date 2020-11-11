package com.honnalmanja.androidkotlinmvvm.di.core

import com.honnalmanja.androidkotlinmvvm.data.repository.task.TaskRepositoryImpl
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskCacheDataSource
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskLocalDataSource
import com.honnalmanja.androidkotlinmvvm.data.repository.task.source.TaskRemoteDataSource
import com.honnalmanja.androidkotlinmvvm.data.repository.user.UserRepositoryImpl
import com.honnalmanja.androidkotlinmvvm.data.repository.user.source.UserPreferenceDataSource
import com.honnalmanja.androidkotlinmvvm.data.repository.user.source.UserRemoteDataSource
import com.honnalmanja.androidkotlinmvvm.domain.repository.TaskRepository
import com.honnalmanja.androidkotlinmvvm.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource,
        userPreferenceDataSource: UserPreferenceDataSource
    ) : UserRepository {
        return UserRepositoryImpl(userRemoteDataSource, userPreferenceDataSource)
    }

    @Singleton
    @Provides
    fun provideTaskRepository(
        taskRemoteDataSource: TaskRemoteDataSource,
        taskLocalDataSource: TaskLocalDataSource,
        taskCacheDataSource: TaskCacheDataSource
    ): TaskRepository {
        return TaskRepositoryImpl(taskRemoteDataSource, taskLocalDataSource, taskCacheDataSource)
    }

}