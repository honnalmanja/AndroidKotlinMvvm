package com.honnalmanja.androidkotlinmvvm.di.core

import com.honnalmanja.androidkotlinmvvm.domain.repository.UserRepository
import com.honnalmanja.androidkotlinmvvm.domain.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideUserUseCase(userRepository: UserRepository) : UserUseCase {
        return UserUseCase(userRepository)
    }

}