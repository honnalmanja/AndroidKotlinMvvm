package com.honnalmanja.androidkotlinmvvm.di.task

import com.honnalmanja.androidkotlinmvvm.domain.usecase.TaskUseCase
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.TaskViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TaskModule {

    @TaskScope
    @Provides
    fun provideTaskViewModelFactory(taskUseCase: TaskUseCase): TaskViewModelFactory {
        return TaskViewModelFactory(taskUseCase)
    }

}