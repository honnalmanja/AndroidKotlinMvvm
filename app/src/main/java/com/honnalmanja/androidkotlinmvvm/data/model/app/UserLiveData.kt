package com.honnalmanja.androidkotlinmvvm.data.model.app

import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.User

class UserLiveData(
    var statusCode: Int?,
    var message: String?,
    var user: User?,
    var userList: List<User>?,
    var token: String?
) {
    override fun toString(): String {
        return "UserLiveData(statusCode=$statusCode, message=$message, user=$user, " +
                "userList=$userList, token=$token" +
                ")"
    }
}