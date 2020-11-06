package com.honnalmanja.androidkotlinmvvm.data.repository.user.source

import com.honnalmanja.androidkotlinmvvm.data.model.CreateUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.LoginUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header

interface UserRemoteDataSource {

    suspend fun loginUser(loginRequest: LoginUserRequest) : Response<UserResponse?>?

    suspend fun logoutUser(@Header("Authorization") token: String?): Response<UserResponse?>?

    suspend fun logoutAll(@Header("Authorization") token: String?): Response<UserResponse?>?

    suspend fun createUser(@Body createUserRequest: CreateUserRequest?): Response<UserResponse?>?

}