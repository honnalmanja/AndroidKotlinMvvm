package com.honnalmanja.androidkotlinmvvm.presentation.viewModel.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.honnalmanja.androidkotlinmvvm.domain.usecase.TaskUseCase

class TaskViewModel(private val taskUseCase: TaskUseCase): ViewModel() {

    private val _TAG = "TaskViewModel"

    fun getUserToken() = liveData {

        val response = taskUseCase.getAuthToken()
        emit(response)

    }

    fun getTaskList() = liveData {
        val response = taskUseCase.getAllTask()
        emit(response)
    }

}