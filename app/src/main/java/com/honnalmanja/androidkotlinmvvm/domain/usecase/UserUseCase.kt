package com.honnalmanja.androidkotlinmvvm.domain.usecase

import com.honnalmanja.androidkotlinmvvm.data.model.CreateUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.LoginUserRequest
import com.honnalmanja.androidkotlinmvvm.domain.repository.UserRepository
import com.honnalmanja.androidkotlinmvvm.data.UserLiveData

class UserUseCase(private val userRepository: UserRepository) {

    suspend fun loginUser(loginUserRequest: LoginUserRequest) : UserLiveData {
        return userRepository.loginUser(loginUserRequest)
    }

    suspend fun createUser(createUserRequest: CreateUserRequest) : UserLiveData {
        return userRepository.createUser(createUserRequest)
    }

    suspend fun userToken() : String? {
        return userRepository.userToken()
    }

}