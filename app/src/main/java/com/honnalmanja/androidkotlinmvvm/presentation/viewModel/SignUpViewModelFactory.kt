package com.honnalmanja.androidkotlinmvvm.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.honnalmanja.androidkotlinmvvm.domain.usecase.UserUseCase

@Suppress("UNCHECKED_CAST")
class SignUpViewModelFactory(private val userUseCase: UserUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(userUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}