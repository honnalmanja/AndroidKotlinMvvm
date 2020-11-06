package com.honnalmanja.androidkotlinmvvm.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("token")
    var token: String,

    @SerializedName("user")
    var user: User
)