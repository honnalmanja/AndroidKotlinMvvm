package com.honnalmanja.androidkotlinmvvm.di.user

import com.honnalmanja.androidkotlinmvvm.domain.usecase.UserUseCase
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.user.LoginViewModelFactory
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.user.SignUpViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @UserScope
    @Provides
    fun provideLoginViewModelFactory(userUseCase: UserUseCase): LoginViewModelFactory {
        return LoginViewModelFactory(userUseCase)
    }

    @UserScope
    @Provides
    fun provideSignUpViewModelFactory(userUseCase: UserUseCase): SignUpViewModelFactory {
        return SignUpViewModelFactory(userUseCase)
    }

}