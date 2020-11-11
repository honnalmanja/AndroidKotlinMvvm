package com.honnalmanja.androidkotlinmvvm.presentation.viewModel.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.honnalmanja.androidkotlinmvvm.domain.usecase.UserUseCase

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val userUseCase: UserUseCase)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}