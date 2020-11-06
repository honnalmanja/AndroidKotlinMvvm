package com.honnalmanja.androidkotlinmvvm.data.model

import com.google.gson.annotations.SerializedName

data class User(
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
) {
    override fun toString(): String {
        return "User(userID='$userID', userName='$userName', userEmail='$userEmail', userAge=$userAge, avatarURL='$avatarURL')"
    }
}