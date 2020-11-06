package com.honnalmanja.androidkotlinmvvm.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.honnalmanja.androidkotlinmvvm.domain.usecase.UserUseCase
import com.honnalmanja.androidkotlinmvvm.utils.LogUtils

class TaskViewModel(private val userUseCase: UserUseCase): ViewModel() {

    private val _TAG = "TaskViewModel"

    fun getUserToken() = liveData {

        val response = userUseCase.userToken()
        emit(response)

    }

}