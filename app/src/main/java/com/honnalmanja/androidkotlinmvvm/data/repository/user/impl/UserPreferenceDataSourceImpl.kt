package com.honnalmanja.androidkotlinmvvm.data.repository.user.impl

import android.util.Log
import com.honnalmanja.androidkotlinmvvm.data.local.AppPreference
import com.honnalmanja.androidkotlinmvvm.data.model.remote.User
import com.honnalmanja.androidkotlinmvvm.data.repository.user.source.UserPreferenceDataSource

class UserPreferenceDataSourceImpl(
    private val appPreference: AppPreference
) : UserPreferenceDataSource {

    override suspend fun getToken(): String? {
        return appPreference.getUserToken()
    }

    override suspend fun saveUserAndToken(user: User?, token: String?): Boolean {
        return appPreference.saveUserAndToken(user, token)
    }
}