package com.honnalmanja.androidkotlinmvvm.model.remote

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("_id")
    var userID: String,

    @SerializedName("name")
    var userName: String,

    @SerializedName("email")
    var userEmail: String,

    @SerializedName("age")
    var userAge: Int,

    @SerializedName("avatar")
    var avatarURL: String
)