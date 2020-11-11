package com.honnalmanja.androidkotlinmvvm.data.repository.user

import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.CreateUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.LoginUserRequest
import com.honnalmanja.androidkotlinmvvm.domain.repository.UserRepository
import com.honnalmanja.androidkotlinmvvm.data.model.app.UserLiveData
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.User
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.UserResponse
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

    override suspend fun saveUserAndToken(user: User?, token: String?): Boolean {
        return userPreferenceDataSource.saveUserAndToken(user, token)
    }

    override suspend fun userToken(): String? {
        return userPreferenceDataSource.getToken()
    }

    private suspend fun postLoginUser(loginUserRequest: LoginUserRequest) : UserLiveData {
        lateinit var userLiveData: UserLiveData
        try {
            val response = userRemoteDataSource.loginUser(loginUserRequest)
            userLiveData = if(response.code() == 202) {
                val userResponse: UserResponse? = response.body()
                UserLiveData(
                    200, response.message(),
                    userResponse?.user, null,
                    userResponse?.token
                )
            } else {
                 UserLiveData(
                    response.code(), response.message(),
                    null, null, null
                )
            }
        } catch (exception: Exception) {
            LogUtils.logE(_TAG, "postLoginUser $exception")
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

            userLiveData = if(response.code() == 201) {
                LogUtils.logD(_TAG, "Inside IF")
                UserLiveData(
                    200, response.message(),
                    response.body()?.user,
                    null,
                    response.body()?.token
                )
            } else {
                LogUtils.logD(_TAG, "Inside Else")
                UserLiveData(
                    response.code(), response.message(),
                    null, null, null
                )
            }

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