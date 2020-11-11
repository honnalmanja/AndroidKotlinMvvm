package com.honnalmanja.androidkotlinmvvm.domain.repository


import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.CreateUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.LoginUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.app.UserLiveData
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.User

interface UserRepository {

    suspend fun loginUser(loginUserRequest: LoginUserRequest) : UserLiveData

    suspend fun createUser(createUserRequest: CreateUserRequest) : UserLiveData

    suspend fun saveUserAndToken(user: User?, token: String?): Boolean?

    suspend fun userToken() : String?

}