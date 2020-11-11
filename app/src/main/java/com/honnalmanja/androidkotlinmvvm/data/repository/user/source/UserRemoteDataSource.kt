package com.honnalmanja.androidkotlinmvvm.data.repository.user.source

import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.CreateUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.LoginUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.UserResponse
import retrofit2.Response

interface UserRemoteDataSource {

    suspend fun loginUser(loginRequest: LoginUserRequest) : Response<UserResponse>

    suspend fun logoutUser(token: String?): Response<UserResponse>

    suspend fun logoutAll(token: String?): Response<UserResponse>

    suspend fun createUser(createUserRequest: CreateUserRequest?): Response<UserResponse>

}