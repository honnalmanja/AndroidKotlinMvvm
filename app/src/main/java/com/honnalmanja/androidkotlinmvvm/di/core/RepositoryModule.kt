package com.honnalmanja.androidkotlinmvvm.di.core

import com.honnalmanja.androidkotlinmvvm.data.repository.user.UserRepositoryImpl
import com.honnalmanja.androidkotlinmvvm.data.repository.user.source.UserPreferenceDataSource
import com.honnalmanja.androidkotlinmvvm.data.repository.user.source.UserRemoteDataSource
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

}