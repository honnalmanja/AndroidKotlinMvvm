package com.honnalmanja.androidkotlinmvvm.domain

import com.honnalmanja.androidkotlinmvvm.data.model.app.UserLiveData
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.CreateUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.LoginUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.User
import com.honnalmanja.androidkotlinmvvm.domain.repository.UserRepository
import kotlin.random.Random

class FakeUserRepository : UserRepository{
    override suspend fun loginUser(loginUserRequest: LoginUserRequest): UserLiveData {
        val user = User("1","Manju", loginUserRequest.emailAddress, 10, "https://siteaname.domain/images/manju.jpg")
        val successData = UserLiveData(200, "Success", user, null, "FakeToken")
        val failureData = UserLiveData(404, "Email or Password wrong", null, null, null)
        val networkErrorData = UserLiveData(500, "Network Error, Try again", null, null,null)
        return successData
    }

    override suspend fun createUser(createUserRequest: CreateUserRequest): UserLiveData {
        val user = User("1",createUserRequest.userName, createUserRequest.userEmail, 10, "https://siteaname.domain/images/manju.jpg")
        val successData = UserLiveData(200, "Success", user, null, "FakeToken")
        val failureData = UserLiveData(404, "Email or Password wrong", null, null, null)
        val networkErrorData = UserLiveData(500, "Network Error, Try again", null, null,null)
        return successData
    }

    override suspend fun saveUserAndToken(user: User?, token: String?): Boolean? {
        return Random.nextBoolean()
    }

    override suspend fun userToken(): String? {
        return "FakeUserToken"
    }
}