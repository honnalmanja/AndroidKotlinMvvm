package com.honnalmanja.androidkotlinmvvm.data.repository.user.source

interface UserPreferenceDataSource {

    suspend fun getToken() : String?

}