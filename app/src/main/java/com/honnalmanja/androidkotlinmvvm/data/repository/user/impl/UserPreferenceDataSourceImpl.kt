package com.honnalmanja.androidkotlinmvvm.data.repository.user.impl

import com.honnalmanja.androidkotlinmvvm.data.local.AppPreference
import com.honnalmanja.androidkotlinmvvm.data.repository.user.source.UserPreferenceDataSource

class UserPreferenceDataSourceImpl(
    private val appPreference: AppPreference
) : UserPreferenceDataSource {

    override suspend fun getToken(): String? {
        return appPreference.getUserToken()
    }
}