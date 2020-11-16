package com.honnalmanja.androidkotlinmvvm.presentation.viewModel.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.honnalmanja.androidkotlinmvvm.domain.usecase.TaskUseCase

@Suppress("UNCHECKED_CAST")
class TaskListViewModelFactory(private val taskUseCase: TaskUseCase):
    ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            return TaskListViewModel(taskUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}