package com.honnalmanja.androidkotlinmvvm.di.task

import com.honnalmanja.androidkotlinmvvm.domain.usecase.UserUseCase
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.LoginViewModelFactory
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.TaskViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TaskModule {

    @TaskScope
    @Provides
    fun provideTaskViewModelFactory(userUseCase: UserUseCase): TaskViewModelFactory {
        return TaskViewModelFactory(userUseCase)
    }

}