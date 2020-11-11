package com.honnalmanja.androidkotlinmvvm.domain.usecase

import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.CreateUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.LoginUserRequest
import com.honnalmanja.androidkotlinmvvm.domain.repository.UserRepository
import com.honnalmanja.androidkotlinmvvm.data.model.app.UserLiveData
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.User

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

    suspend fun saveUserData(user: User?, token: String?): Boolean? {
        return userRepository.saveUserAndToken(user, token)
    }

}