package com.honnalmanja.androidkotlinmvvm.di.task

import com.honnalmanja.androidkotlinmvvm.domain.usecase.TaskUseCase
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.task.AddTaskViewModelFactory
import com.honnalmanja.androidkotlinmvvm.presentation.viewModel.task.TaskListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TaskModule {

    @TaskScope
    @Provides
    fun provideTaskListViewModelFactory(taskUseCase: TaskUseCase): TaskListViewModelFactory {
        return TaskListViewModelFactory(taskUseCase)
    }

    @TaskScope
    @Provides
    fun provideAddTaskViewModelFactory(taskUseCase: TaskUseCase): AddTaskViewModelFactory {
        return AddTaskViewModelFactory(taskUseCase)
    }

}