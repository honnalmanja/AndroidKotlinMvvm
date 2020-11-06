package com.honnalmanja.androidkotlinmvvm.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.honnalmanja.androidkotlinmvvm.domain.usecase.UserUseCase

class LoginViewModelFactory(private val userUseCase: UserUseCase)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(userUseCase) as T
    }
}