package com.honnalmanja.androidkotlinmvvm.data.repository.user.impl

import com.honnalmanja.androidkotlinmvvm.data.api.TMServices
import com.honnalmanja.androidkotlinmvvm.data.model.CreateUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.LoginUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.UserResponse
import com.honnalmanja.androidkotlinmvvm.data.repository.user.source.UserRemoteDataSource
import retrofit2.Response

class UserRemoteDataSourceImpl(
    private val service: TMServices
): UserRemoteDataSource {

    private val _TAG: String = "UserRemoteDataSourceImpl"

    override suspend fun loginUser(loginRequest: LoginUserRequest): Response<UserResponse?>? {
        return service.loginUser(loginRequest)
    }

    override suspend fun logoutUser(token: String?): Response<UserResponse?>? {
        return service.logoutUser(token)
    }

    override suspend fun logoutAll(token: String?): Response<UserResponse?>? {
        return service.logoutAll(token)
    }

    override suspend fun createUser(createUserRequest: CreateUserRequest?): Response<UserResponse?>? {
        return service.createUser(createUserRequest)
    }
}