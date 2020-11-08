package com.honnalmanja.androidkotlinmvvm.data.repository.user.source

import com.honnalmanja.androidkotlinmvvm.data.model.remote.User

interface UserPreferenceDataSource {

    suspend fun getToken() : String?

    suspend fun saveUserAndToken(user: User?, token: String?): Boolean

}