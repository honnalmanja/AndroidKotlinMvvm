package com.honnalmanja.androidkotlinmvvm.data.repository.user.impl

import com.honnalmanja.androidkotlinmvvm.data.api.TMServices
import com.honnalmanja.androidkotlinmvvm.data.model.remote.CreateUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.LoginUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.UserResponse
import com.honnalmanja.androidkotlinmvvm.data.repository.user.source.UserRemoteDataSource
import retrofit2.Response

class UserRemoteDataSourceImpl(
    private val service: TMServices
): UserRemoteDataSource {

    override suspend fun loginUser(loginRequest: LoginUserRequest): Response<UserResponse> {
        return service.loginUser(loginRequest)
    }

    override suspend fun logoutUser(token: String?): Response<UserResponse> {
        return service.logoutUser(token)
    }

    override suspend fun logoutAll(token: String?): Response<UserResponse> {
        return service.logoutAll(token)
    }

    override suspend fun createUser(createUserRequest: CreateUserRequest?): Response<UserResponse> {
        return service.createUser(createUserRequest)
    }
}