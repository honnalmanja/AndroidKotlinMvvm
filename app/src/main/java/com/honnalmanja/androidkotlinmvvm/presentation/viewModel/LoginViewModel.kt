package com.honnalmanja.androidkotlinmvvm.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.honnalmanja.androidkotlinmvvm.data.model.LoginUserRequest
import com.honnalmanja.androidkotlinmvvm.domain.usecase.UserUseCase

class LoginViewModel(
    private val userUseCase: UserUseCase
) : ViewModel(){

    fun loginUser( email: String, password: String) = liveData {
        val loginUserRequest = LoginUserRequest(email, password)
        val response = userUseCase.loginUser(loginUserRequest)
        emit(response)
    } 

}