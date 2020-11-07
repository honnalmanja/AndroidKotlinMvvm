package com.honnalmanja.androidkotlinmvvm.domain.repository


import com.honnalmanja.androidkotlinmvvm.data.model.remote.CreateUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.LoginUserRequest
import com.honnalmanja.androidkotlinmvvm.data.UserLiveData

interface UserRepository {

    suspend fun loginUser(loginUserRequest: LoginUserRequest) : UserLiveData

    suspend fun createUser(createUserRequest: CreateUserRequest) : UserLiveData

    suspend fun userToken() : String?

}