package com.honnalmanja.androidkotlinmvvm.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.honnalmanja.androidkotlinmvvm.domain.usecase.TaskUseCase
import com.honnalmanja.androidkotlinmvvm.domain.usecase.UserUseCase
import com.honnalmanja.androidkotlinmvvm.utils.LogUtils

class TaskViewModel(private val taskUseCase: TaskUseCase): ViewModel() {

    private val _TAG = "TaskViewModel"

    fun getUserToken() = liveData {

        val response = taskUseCase.getAuthToken()
        emit(response)

    }

}