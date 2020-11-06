package com.honnalmanja.androidkotlinmvvm.data.model

import com.google.gson.annotations.SerializedName

data class LoginUserRequest(

    @SerializedName("email")
    var emailAddress: String,

    @SerializedName("password")
    var userPassword: String
) {
    override fun toString(): String {
        return "LoginUserRequest(emailAddress='$emailAddress', userPassword='$userPassword')"
    }
}