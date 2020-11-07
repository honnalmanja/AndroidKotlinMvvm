package com.honnalmanja.androidkotlinmvvm.data.api

import com.honnalmanja.androidkotlinmvvm.data.model.remote.CreateUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.LoginUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TMServices {


    //-------------------------- User Services ------------------------------//
    @POST("users/add")
    suspend fun createUser(@Body createUserRequest: CreateUserRequest?): Response<UserResponse?>?

    @POST("users/login")
    suspend fun loginUser(@Body loginUserRequest: LoginUserRequest?): Response<UserResponse?>?

    @POST("users/logoutAll")
    suspend fun logoutAll(@Header("Authorization") token: String?): Response<UserResponse?>?

    @POST("users/logout")
    suspend fun logoutUser(@Header("Authorization") token: String?): Response<UserResponse?>?
    //-------------------------- User Services ------------------------------//
}