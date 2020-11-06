package com.honnalmanja.androidkotlinmvvm.data.repository.user

import com.honnalmanja.androidkotlinmvvm.data.model.CreateUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.LoginUserRequest
import com.honnalmanja.androidkotlinmvvm.domain.repository.UserRepository
import com.honnalmanja.androidkotlinmvvm.data.UserLiveData
import com.honnalmanja.androidkotlinmvvm.data.repository.user.source.UserPreferenceDataSource
import com.honnalmanja.androidkotlinmvvm.data.repository.user.source.UserRemoteDataSource
import com.honnalmanja.androidkotlinmvvm.utils.LogUtils

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userPreferenceDataSource: UserPreferenceDataSource
) : UserRepository {

    private val _TAG: String = "UserRepositoryImpl"

    override suspend fun loginUser(loginUserRequest: LoginUserRequest): UserLiveData {
        return postLoginUser(loginUserRequest)
    }

    override suspend fun createUser(createUserRequest: CreateUserRequest): UserLiveData {
        return postUserDetails(createUserRequest)
    }

    override suspend fun userToken(): String? {
        return userPreferenceDataSource.getToken()
    }

    private suspend fun postLoginUser(loginUserRequest: LoginUserRequest) : UserLiveData {
        lateinit var userLiveData: UserLiveData
        try {
            val response = userRemoteDataSource.loginUser(loginUserRequest)

            userLiveData = if(response?.code() == (200 or 201 or 202)) {
                UserLiveData(
                    200, response.message(),
                    response.body()?.user, null,
                    response.body()?.token
                )
            } else {
                UserLiveData(
                    response?.code(), response?.message(),
                    null, null, null
                )
            }
            return userLiveData
        } catch (exception: Exception) {
            LogUtils.logE(_TAG, ""+exception)
            userLiveData = UserLiveData(
                500, "Unknown Exception",
                null, null,
                null
            )
        }
        return userLiveData
    }

    private suspend fun postUserDetails(createUserRequest: CreateUserRequest) : UserLiveData {
        lateinit var userLiveData: UserLiveData
        try {
            val response = userRemoteDataSource.createUser(createUserRequest)

            userLiveData = if(response?.code() == (200 or 201 or 202)) {
                UserLiveData(
                    200, response.message(),
                    response.body()?.user, null,
                    response.body()?.token
                )
            } else {
                UserLiveData(
                    response?.code(), response?.message(),
                    null, null, null
                )
            }
            return userLiveData
        } catch (exception: Exception) {
            LogUtils.logE(_TAG, ""+exception)
            userLiveData = UserLiveData(
                500, "Unknown Exception",
                null, null,
                null
            )
        }
        return userLiveData
    }
}