package com.honnalmanja.androidkotlinmvvm.data.model.remote.user

import com.google.gson.annotations.SerializedName

data class CreateUserRequest(

    @SerializedName("email")
    var userEmail: String?,

    @SerializedName("password")
    var userPassword: String?,

    @SerializedName("name")
    var userName: String?,

    @SerializedName("age")
    var userAge: Int = 0,
) {

    override fun toString(): String {
        return "CreateUserRequest(userEmail='$userEmail', userPassword='$userPassword', userName='$userName', userAge=$userAge)"
    }
}